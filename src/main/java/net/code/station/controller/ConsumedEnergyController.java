package net.code.station.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.standard.DateTimeContext;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.code.station.dao.MeterStateDAO;
import net.code.station.dao.ContactDAO;
import net.code.station.dao.HindDAO;
import net.code.station.dao.ClientMeterDAO;
import net.code.station.dao.OfferDAO;
import net.code.station.dao.PerioodDAO;
import net.code.station.dao.ConsumedEnergyDAO;
import net.code.station.dao.ClientOrderDAO;
import net.code.station.dao.UserDAO;
import net.code.station.model.MeterState;
import net.code.station.model.Contact;
import net.code.station.model.Hind;
import net.code.station.model.ClientMeter;
import net.code.station.model.Offer;
import net.code.station.model.Periood;
import net.code.station.model.ConsumedEnergy;
import net.code.station.model.ClientOrder;


@Controller
public class ConsumedEnergyController {
	
	@Autowired
	private MeterStateDAO arvestinaitDAO;
	@Autowired
	private ClientMeterDAO kliendiarvestiDAO;
	@Autowired
	private ContactDAO klientDAO;
	@Autowired
	private ConsumedEnergyDAO tarbitudenergiaDAO;
	@Autowired
	private PerioodDAO perioodDAO;
	@Autowired
	private HindDAO hindDAO;
	@Autowired
	private ClientOrderDAO tellimusDAO;
	@Autowired
	private OfferDAO pakkumineDAO;
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value = "/energiaarvutuslaud", method = RequestMethod.GET)
	public ModelAndView energiaArvutusLaud(ModelAndView model, HttpServletRequest request) {
		Integer klientid = Integer.parseInt(request.getParameter("id"));
		ClientMeter arv = kliendiarvestiDAO.getMeterId(klientid);
		Integer arvestiid = arv.getMeterid();
		ConsumedEnergy tarbitudenergia = new ConsumedEnergy(arvestiid, null, null, null, null, null, null, null);
		model.addObject("tarbitudenergia", tarbitudenergia);
		List<Periood> listPeriood = perioodDAO.list();
		model.addObject("listPeriood", listPeriood);
		model.setViewName("personal/energiaarvutusForm");
		///////		
		return model;
	}
	@RequestMapping(value = "/arvutaenergiaraw", method = RequestMethod.POST)
	public ModelAndView arvutaenergiaRaw(
			@ModelAttribute ConsumedEnergy tarbitudEnergia) {		
		//Sisendmuutujate vastuvõtt
		Integer arvestiid = tarbitudEnergia.getArvestiid();	
		Integer perioodid = tarbitudEnergia.getPerioodid();
		//Vanade arvutustulemuste kustutamine
		tarbitudenergiaDAO.deleteKirjed(arvestiid, perioodid);	
		//Hinna päring tellimuse kaudu pakkumisest
		ClientMeter arv = kliendiarvestiDAO.getArvestiByArvestiId(arvestiid);
		Integer klientid = arv.getKlientid();
		ClientOrder tellimus = tellimusDAO.getByKlient(klientid, perioodid);
		Integer pakkumineid = tellimus.getOfferid();
		Offer pakkumine = pakkumineDAO.get(pakkumineid);	
		Integer hinnaVaartus = pakkumine.getHind();
		//Loendi päringu atribuutide loomine
		Periood periood = perioodDAO.get(perioodid);
		Timestamp algus = periood.getAlates();
		Timestamp lopp = periood.getKuni();
		String perNimetus = periood.getPerNimetus();
		//Arvestinäitude loendi päring
		List<MeterState> listMeterState = arvestinaitDAO
				.listForPeriod(arvestiid, algus, lopp);
		//tarbitud energia arvutamine igale loendi kirjele
		energiaKirjele(listMeterState, hinnaVaartus, perioodid,
				arvestiid, perNimetus);		
		return new ModelAndView("redirect:/kliendid");
	}
	
	private void energiaKirjele(List<MeterState> listMeterState, 
			Integer hinnaVaartus, Integer perioodid,
			Integer arvestiid, String perNimetus) {
		for(int i = 0; i < (listMeterState.size()-1); i++) {
			MeterState arvestiNait0 = listMeterState.get(i);
			MeterState arvestiNait1 = listMeterState.get(i+1);
			Integer energia0 = arvestiNait0.getEnergia();
			Integer energia1 = arvestiNait1.getEnergia();
			
			Timestamp alates = arvestiNait0.getAeg();				
			Timestamp kuni = arvestiNait1.getAeg();
			Integer tarbEneTunnis = energia1 - energia0;			
			Integer summa = tarbEneTunnis * hinnaVaartus;
			//Tarbitud energia objekti loomine
			ConsumedEnergy tarbitudEnergiaM = new ConsumedEnergy(
					arvestiid, alates, kuni, tarbEneTunnis,
					hinnaVaartus, summa, perNimetus, perioodid);			
			//Objekti salvestamine			
			tarbitudenergiaDAO.save(tarbitudEnergiaM);
		}
		
	}
	//@RequestMapping(value = "/vaataenarvlaud", method = RequestMethod.GET)
	@GetMapping("/perioodenergialeForm")
	public String vaataEnArvLaud(Model model,
			HttpServletRequest request, 
			Authentication authentication) {
		String userName = authentication.getName();
		Integer klientid = 0;
		if(!userName.equals("personal01")) {
			Integer userid = userDAO.getUserIdByUName(userName);
			Contact klient = klientDAO.getByUserId(userid);		
			klientid = klient.getId();
		}
		if(userName.equals("personal01")) {
			klientid = Integer.parseInt(request.getParameter("id"));
		}
		
		
		
		ClientMeter arv = kliendiarvestiDAO.getMeterId(klientid);
		Integer arvestiid = arv.getMeterid();
		ConsumedEnergy tarbitudenergia = new ConsumedEnergy(arvestiid, null, null, null, null, null, null, null);
		model.addAttribute("tarbitudEnergia", tarbitudenergia);
		List<Periood> listPeriood = perioodDAO.list();
		model.addAttribute("listPeriood", listPeriood);
		model.addAttribute("userName", userName);
		//model.setViewName("personal/vaataenarvForm");
		///////		
		//return "personal/vaataenarvForm";
		return "perioodenergialeForm";
	}
	
	//@RequestMapping(value = "/vaatatarbitudenergiaid", method = RequestMethod.GET)
	@PostMapping("/perioodenergialeForm")
	public String vaataConsumedEnergyid(@Valid @ModelAttribute("tarbitudEnergia") 
			ConsumedEnergy tarbitudEnergia, Errors errors, Model model, 
			HttpServletRequest request,	Authentication authentication) {
		Integer arvestiid = tarbitudEnergia.getArvestiid();
		ClientMeter kliendiArvesti = kliendiarvestiDAO
				.getArvestiByArvestiId(arvestiid);
		Integer klientid = kliendiArvesti.getKlientid();
		String userName = authentication.getName();
		model.addAttribute("userName", userName);
		if(!errors.hasErrors()) {
			Integer klientidAut = 0;
			if(!userName.equals("personal01")) {//Autoriseerituse kontroll
				Integer userid = userDAO.getUserIdByUName(userName);
				Contact klientAut = klientDAO.getByUserId(userid);
				klientidAut = klientAut.getId();		
				if(!klientidAut.equals(klientid)) {
					String teade = "Küsitud andmetele juurdepääs keelatud";
					model.addAttribute("klient", klientAut);		
					model.addAttribute("teade", teade);					
					return "welcome";
				}		
			}	//Autoriseerituse kontroll
			if(klientidAut.equals(klientid) || userName.equals("personal01")) {	
				Integer perioodid = tarbitudEnergia.getPerioodid();
				model = valmistaModelEnergiaLoend(
						model, perioodid, arvestiid);								
				return "tarbitudenergiad";
			}
		}
		List<Periood> listPeriood = perioodDAO.list();
		model.addAttribute("listPeriood", listPeriood);
		return "perioodenergialeForm";
	}
	private Model valmistaModelEnergiaLoend(Model model,
			Integer perioodid, Integer arvestiid) {		
		Integer kokkuSumma = 0; //muutujate algväärtustamine
		Timestamp perioodiAlgus = null;
		Timestamp perioodiLopp = null;
		//Tarbitud energiate loendi päring andmebaasist
		List<ConsumedEnergy> listConsumedEnergy = 
				tarbitudenergiaDAO.list(arvestiid, perioodid);
		for(int i = 0; i < (listConsumedEnergy.size()); i++) {	
			ConsumedEnergy tarbitudEnergiaF = listConsumedEnergy.get(i);
			Integer summa = tarbitudEnergiaF.getSumma();
			//osaenergia lisamine summale
			kokkuSumma = kokkuSumma + summa;
			if(i == 0) { 
				perioodiAlgus = tarbitudEnergiaF.getAlates();
			}//perioodi alguse määramine
			if(i == listConsumedEnergy.size()-1) {
				perioodiLopp = tarbitudEnergiaF.getKuni();
			}//perioodi lõpu määramine			
		}
		Double kokkuDouble = (kokkuSumma.doubleValue())/100; //eurodesse
		model.addAttribute("perioodiAlgus", perioodiAlgus);
		model.addAttribute("perioodiLopp", perioodiLopp);		
		model.addAttribute("kokkuSumma", kokkuDouble);
		model.addAttribute("listConsumedEnergy", listConsumedEnergy);
		return model;
	}
}
