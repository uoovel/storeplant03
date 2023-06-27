package net.code.station.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.code.station.dao.OfferDAO;
import net.code.station.dao.PerioodDAO;
import net.code.station.model.Offer;
import net.code.station.model.Periood;
import net.code.station.viewmodel.OfferView;

@Controller
public class OfferController {
	@Autowired
	private OfferDAO pakkumineDAO;
	@Autowired
	private PerioodDAO perioodDAO;
	
	@RequestMapping(value = "/pakkumised")
	public ModelAndView listOffer(ModelAndView model) {  //home
		
		//System.out.println("MainController");
		//return "index";
		List<Offer> listOffer = pakkumineDAO.list();
		List<OfferView> listOfferView = new ArrayList<>();
		for(int i = 0; i < listOffer.size(); i++) {
			Offer pakkumine = listOffer.get(i);
			Integer id = pakkumine.getId();
			String nimetus = pakkumine.getNimetus();
			Integer hind = pakkumine.getHind();
			String kirjeldus = pakkumine.getKirjeldus();
			Integer perioodid = pakkumine.getPerioodid();
			Periood periood = perioodDAO.get(perioodid);
			OfferView pakkumineView = new OfferView(id, nimetus, hind, kirjeldus, periood);
			listOfferView.add(pakkumineView);
		}	
		
		model.addObject("listOfferView", listOfferView);
		model.setViewName("pakkumised");
		return model;
	}
	@RequestMapping(value = "/newpakkumine", method = RequestMethod.GET)
	public ModelAndView newOffer(ModelAndView model) {  //home
				
		Offer newOffer = new Offer();
		model.addObject("pakkumine", newOffer);
		List<Periood> listPeriood = perioodDAO.list();
		model.addObject("listPeriood", listPeriood);
		model.setViewName("pakkumineForm");
		return model;
	}
	@RequestMapping(value = "/savepakkumine", method = RequestMethod.POST)
	public ModelAndView saveOffer(@ModelAttribute Offer pakkumine) {
		//System.out.println("SaveController>pakkumine" + pakkumine);
		if (pakkumine.getId() == null) {
			pakkumineDAO.save(pakkumine);
		} else {
			
			pakkumineDAO.update(pakkumine);
		}		
		return new ModelAndView("redirect:/pakkumised");
	}
	@RequestMapping(value = "/editpakkumine", method = RequestMethod.GET)
	public ModelAndView editOffer(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Offer pakkumine = pakkumineDAO.get(id);
		//System.out.println("editOffer>pakkumine: " + pakkumine);
		ModelAndView model = new ModelAndView("personal/ppakkumineEdit");
		model.addObject("pakkumine", pakkumine);	
		Integer perioodid = pakkumine.getPerioodid();
		Periood periood = perioodDAO.get(perioodid);
		model.addObject("periood", periood);
		return model;
	}
	@RequestMapping(value = "/deletepakkumine", method = RequestMethod.GET)
	public ModelAndView deleteOffer(@RequestParam Integer id) {
		pakkumineDAO.delete(id);		
		return new ModelAndView("redirect:/pakkumised");
	}
}
