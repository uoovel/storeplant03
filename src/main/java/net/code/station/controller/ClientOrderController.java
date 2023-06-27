package net.code.station.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.code.station.dao.MeterDAO;
import net.code.station.dao.BilansihaldurDAO;
import net.code.station.dao.ContactDAO;
import net.code.station.dao.JaamaVoimsusDAO;
import net.code.station.dao.ClientMeterDAO;
import net.code.station.dao.OfferDAO;
import net.code.station.dao.PerioodDAO;
import net.code.station.dao.ReegelDAO;
import net.code.station.dao.StaatusDAO;
import net.code.station.dao.ClientOrderDAO;
import net.code.station.dao.UserDAO;
import net.code.station.model.Meter;
import net.code.station.model.Bilansihaldur;
import net.code.station.model.Contact;
import net.code.station.model.ClientMeter;
import net.code.station.model.Offer;
import net.code.station.model.Periood;
import net.code.station.model.Reegel;
import net.code.station.model.Staatus;
import net.code.station.model.ClientOrder;
import net.code.station.model.User;
import net.code.station.viewmodel.ClientOrderViewL;

@Controller
public class ClientOrderController {
	@Autowired
	private ClientOrderDAO tellimusDAO;
	@Autowired
	private OfferDAO pakkumineDAO;
	@Autowired
	private ContactDAO klientDAO;
	@Autowired
	private PerioodDAO perioodDAO;
	@Autowired
	private StaatusDAO staatusDAO;
	@Autowired
	private JaamaVoimsusDAO jaamavoimsusDAO;
	@Autowired
	private ClientMeterDAO kliendiarvestiDAO;
	@Autowired
	private MeterDAO arvestiDAO;
	@Autowired
	private ReegelDAO reegelDAO;
	@Autowired
	private BilansihaldurDAO bilansihaldurDAO;
	@Autowired
	private UserDAO userDAO;
	private ClientOrderControllerAux tellimusControllerAux = new ClientOrderControllerAux();

	@RequestMapping(value="/tellimusedLeft", method = RequestMethod.GET)
	@PreAuthorize("#request.getParameter('user') == authentication.name ||"
			+ "authentication.name=='personal01'")
	public ModelAndView tellimusedLeft(ModelAndView model, 
			HttpServletRequest request, Authentication authentication) {		
		String userName = authentication.getName();
		Integer klientid = 0;
		if(!userName.equals("personal01")) {
			Integer userid = userDAO.getUserIdByUName(userName);		
		    Contact klientA = klientDAO.getByUserId(userid);
		    klientid = klientA.getId();
		    model.setViewName("tellimusedLeft");
		}
		if(userName.equals("personal01")) {
			klientid = Integer.parseInt(request.getParameter("id"));
			model.setViewName("personal/ptellimused");
		}	
		Integer rollid = Integer.parseInt(request.getParameter("rollid"));	
		List<ClientOrderViewL> listClientOrderViewL = tellimusDAO.listLeft(klientid);
		model.addObject("rollid", rollid);
		model.addObject("userName", userName);
		Integer ajalugu = Integer.parseInt(request.getParameter("ajalugu"));
		if(ajalugu == 1) {
			model.setViewName("tellimusHistory");
			model.addObject("listClientOrderViewL", listClientOrderViewL);
		}
		if(ajalugu != 1) {
			List<ClientOrderViewL> listClientOrderViewLFilter = 
					tellimusControllerAux
					.filtreeriClientOrderViewL(listClientOrderViewL);
			model.addObject("listClientOrderViewL", listClientOrderViewLFilter);
		}					
		return model;
	}

	
	//@RequestMapping(value = "/newtellimus", method = RequestMethod.GET)
	@GetMapping("/perioodtellimuseleForm")
	public String newClientOrder(Model model, Authentication authentication) {
		String userName = authentication.getName();
		model.addAttribute("userName", userName);		
		List<Periood> listPeriood = perioodDAO.list();		
		//Filtreerida listist need perioodid, kus tellimus on esitatud
		List<Periood> filtListPeriood = new ArrayList<Periood>();
		ClientOrderControllerAux tellimusControllerAux = new ClientOrderControllerAux();
 		filtListPeriood = tellimusControllerAux.filtreeriPerioodid(
 				userName, perioodDAO, userDAO, klientDAO, tellimusDAO);		
		//lisatakse filtreeritud list, et ei saaks topelt valida;
		model.addAttribute("filtListPeriood", filtListPeriood);
		List<Offer> listOffer = pakkumineDAO.list();
		model.addAttribute("listOffer", listOffer);		
		List<Contact> listKlient = klientDAO.list();
		model.addAttribute("listKlient", listKlient);
		Periood periood = new Periood();
		model.addAttribute("periood", periood);	
		ClientOrder tellimus = new ClientOrder(
				null, null, null, null, null, null, null, null, userName);		
		model.addAttribute("tellimus", tellimus);	
		return "perioodtellimuseleForm";
	}
	
	//@RequestMapping(value = "/valiperioodtellimusele", method = RequestMethod.POST)
	@PostMapping("/perioodtellimuseleForm")
	@PreAuthorize("#tellimus.getUserName() == authentication.name")
	public String valiPerioodClientOrderele(@Valid @ModelAttribute("tellimus")
			ClientOrder tellimus, Errors errors, Model model,
			Authentication authentication, HttpServletResponse response
			) throws IOException {		
		String userName = authentication.getName();
		boolean selectError = false;
 		if(errors.hasErrors()) {
 			selectError = true;
		} 		
 		if(selectError==false) {
 			Integer perioodid = tellimus.getPerioodid();
		    response.sendRedirect(
					"/Station01/tellimusForm2?user="+userName+"&perioodid="+perioodid);
		}
 		//errors == true 			
 		List<Periood> filtListPeriood = new ArrayList<Periood>(); 
 		ClientOrderControllerAux tellimusControllerAux = new ClientOrderControllerAux();
 		filtListPeriood = tellimusControllerAux.filtreeriPerioodid(
 				userName, perioodDAO, userDAO,
 				klientDAO, tellimusDAO);
 		model.addAttribute("filtListPeriood", filtListPeriood);
 		model.addAttribute("userName", userName);
 		return "perioodtellimuseleForm";
	}	

	@GetMapping("/tellimusForm2")
	public String kuvaClientOrderForm(Model model,	Authentication authentication,
			HttpServletRequest request,	
			HttpServletResponse response) throws IOException {
		String userName = authentication.getName();
		Integer userid = userDAO.getUserIdByUName(userName);
		Contact klient = klientDAO.getByUserId(userid);
		Integer klientid = klient.getId();
		Integer perioodid = Integer.parseInt(request.getParameter("perioodid"));
		ClientOrderControllerAux tellimusControllerAux = new ClientOrderControllerAux();
		model = tellimusControllerAux.modifyModel2(model, userName, perioodid,
				userDAO, klientDAO,	perioodDAO, pakkumineDAO,
				jaamavoimsusDAO, tellimusDAO, arvestiDAO, kliendiarvestiDAO,
				reegelDAO, bilansihaldurDAO, response);
		ClientOrder tellimus = new ClientOrder(
				null, null, klientid, null, null, perioodid, 1, null, null);
		model.addAttribute("tellimus", tellimus);
		return "tellimusForm2";
	}	

	//@RequestMapping(value = "/savetellimus", method = RequestMethod.POST)
	@PostMapping("/tellimusForm2")
	public String saveClientOrder(@Valid @ModelAttribute("tellimus")
			ClientOrder tellimus, Errors errors, Model model,			
			HttpServletRequest request, Authentication authentication,
			HttpServletResponse response) throws IOException {
		String userName = authentication.getName();
		if(!errors.hasErrors()) {
			String teade = "ClientOrdere esitamine oli edukas";
			if (tellimus.getId() == null) {
				tellimus.setStaatusid(1);
				tellimusDAO.save(tellimus);			
			} else {
				tellimus.setStaatusid(7);
				tellimusDAO.update(tellimus);
				teade = "ClientOrdere muutmine oli edukas";
			}
			response.sendRedirect(
					"/Station01/userWelcomeSec?user="+userName+"&message="+teade);
		}
		Integer perioodid = tellimus.getPerioodid();//errors == true
        ClientOrderControllerAux tellimusControllerAux = new ClientOrderControllerAux();
		model = tellimusControllerAux.modifyModel2(model, userName, perioodid,
				userDAO, klientDAO,	perioodDAO, pakkumineDAO, jaamavoimsusDAO, 
				tellimusDAO, arvestiDAO, kliendiarvestiDAO,
				reegelDAO, bilansihaldurDAO, response);
		return "tellimusForm2";
	}
	
	@RequestMapping(value = "/psavetellimus", method = RequestMethod.POST)
	public ModelAndView saveClientOrderPersonal(ModelAndView model,
			@ModelAttribute ClientOrder tellimus) {
		if (tellimus.getId() == null) {
			tellimus.setStaatusid(1);
			tellimusDAO.save(tellimus);
		} else {
			tellimusDAO.update(tellimus);
		}

		model.setViewName("personal/personalwelcome");
		return model;
		
		//return new ModelAndView("redirect:/tellimused");
	}
	
	@RequestMapping(value = "/edittellimus", method = RequestMethod.GET)
	public ModelAndView editClientOrder(HttpServletRequest request,
			Authentication authentication) {
		ModelAndView model = new ModelAndView("tellimusEdit");
		Integer id = Integer.parseInt(request.getParameter("id"));		
		ClientOrder tellimus = tellimusDAO.get(id);
		Integer klientid = tellimus.getKlientid();
		Integer klientidAut = 0;
		//Autoriseerimisfilter
		String userName = authentication.getName();
		if(!userName.equals("personal01")) {
			Integer userid = userDAO.getUserIdByUName(userName);
			Contact klientAut = klientDAO.getByUserId(userid);
			klientidAut = klientAut.getId();			
			if(!klientid.equals(klientidAut)) {
				String teade = "Küsitud andmetele juurdepääs keelatud";
				model.addObject("klient", klientAut);		
				model.addObject("teade", teade);
				model.setViewName("/welcome");					
				model.addObject("userName", userName);
			}		
		}		
		//Autoriseerimisfilter
		
		if(klientid.equals(klientidAut) || userName.equals("personal01")) {
			Integer rollid = Integer.parseInt(request.getParameter("rollid"));
			//System.out.println("ClientOrderController:edittellimus: id: " + id);
			model.addObject("tellimus", tellimus);
			Integer pakkumineid = tellimus.getOfferid();		
			Offer pakkumine = pakkumineDAO.get(pakkumineid);
			
	        Contact klient = klientDAO.get(klientid);		
			model.addObject("pakkumine", pakkumine);
			model.addObject("klient", klient);
			Integer perioodid = tellimus.getPerioodid();
			Periood periood = perioodDAO.get(perioodid);
			model.addObject("periood", periood);
			
			List<Offer> listOffer = pakkumineDAO.listByPeriood(perioodid);
			model.addObject("listOffer", listOffer);
			Integer arvestiid = tellimus.getMeterid();
			Meter arvesti = arvestiDAO.get(arvestiid);
			model.addObject("arvesti", arvesti);
			Integer staatusid = tellimus.getStaatusid();
			Staatus staatus = staatusDAO.get(staatusid);
			model.addObject("staatus", staatus);
			model.addObject("userName", userName);
			
			if (rollid == 2 ) {
				
				List<Staatus> listStaatus = staatusDAO.list();
				model.addObject("listStaatus", listStaatus);
				model.setViewName("personal/ptellimusEdit");
			
			}
		}
		
		return model;
	}



	@RequestMapping(value = "/deletetellimus", method = RequestMethod.GET)
	public void deleteClientOrder(ModelAndView model,
			HttpServletRequest request,	HttpServletResponse response, 
			Authentication authentication) throws IOException {
		Integer tellimusid = Integer.parseInt(request.getParameter("id"));
		//ClientOrder tellimus = tellimusDAO.get(tellimusid);
		//Integer klientid = tellimus.getKlientid();
		tellimusDAO.delete(tellimusid);
		//Contact klient = klientDAO.get(klientid);
		//model.addObject("klient", klient);
		String teade = "ClientOrdere kustutamine oli edukas";
		model.addObject("teade", teade);
		//model.setViewName("/welcome");
		String userName = authentication.getName();
		response.sendRedirect("/Station01/userWelcomeSec?user="+userName+"&message="+teade);
		//return model;
	}
}


