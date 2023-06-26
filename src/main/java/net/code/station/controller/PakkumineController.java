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

import net.code.station.dao.PakkumineDAO;
import net.code.station.dao.PerioodDAO;
import net.code.station.model.Pakkumine;
import net.code.station.model.Periood;
import net.code.station.viewmodel.PakkumineView;

@Controller
public class PakkumineController {
	@Autowired
	private PakkumineDAO pakkumineDAO;
	@Autowired
	private PerioodDAO perioodDAO;
	
	@RequestMapping(value = "/pakkumised")
	public ModelAndView listPakkumine(ModelAndView model) {  //home
		
		//System.out.println("MainController");
		//return "index";
		List<Pakkumine> listPakkumine = pakkumineDAO.list();
		List<PakkumineView> listPakkumineView = new ArrayList<>();
		for(int i = 0; i < listPakkumine.size(); i++) {
			Pakkumine pakkumine = listPakkumine.get(i);
			Integer id = pakkumine.getId();
			String nimetus = pakkumine.getNimetus();
			Integer hind = pakkumine.getHind();
			String kirjeldus = pakkumine.getKirjeldus();
			Integer perioodid = pakkumine.getPerioodid();
			Periood periood = perioodDAO.get(perioodid);
			PakkumineView pakkumineView = new PakkumineView(id, nimetus, hind, kirjeldus, periood);
			listPakkumineView.add(pakkumineView);
		}	
		
		model.addObject("listPakkumineView", listPakkumineView);
		model.setViewName("pakkumised");
		return model;
	}
	@RequestMapping(value = "/newpakkumine", method = RequestMethod.GET)
	public ModelAndView newPakkumine(ModelAndView model) {  //home
				
		Pakkumine newPakkumine = new Pakkumine();
		model.addObject("pakkumine", newPakkumine);
		List<Periood> listPeriood = perioodDAO.list();
		model.addObject("listPeriood", listPeriood);
		model.setViewName("pakkumineForm");
		return model;
	}
	@RequestMapping(value = "/savepakkumine", method = RequestMethod.POST)
	public ModelAndView savePakkumine(@ModelAttribute Pakkumine pakkumine) {
		//System.out.println("SaveController>pakkumine" + pakkumine);
		if (pakkumine.getId() == null) {
			pakkumineDAO.save(pakkumine);
		} else {
			
			pakkumineDAO.update(pakkumine);
		}		
		return new ModelAndView("redirect:/pakkumised");
	}
	@RequestMapping(value = "/editpakkumine", method = RequestMethod.GET)
	public ModelAndView editPakkumine(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Pakkumine pakkumine = pakkumineDAO.get(id);
		//System.out.println("editPakkumine>pakkumine: " + pakkumine);
		ModelAndView model = new ModelAndView("personal/ppakkumineEdit");
		model.addObject("pakkumine", pakkumine);	
		Integer perioodid = pakkumine.getPerioodid();
		Periood periood = perioodDAO.get(perioodid);
		model.addObject("periood", periood);
		return model;
	}
	@RequestMapping(value = "/deletepakkumine", method = RequestMethod.GET)
	public ModelAndView deletePakkumine(@RequestParam Integer id) {
		pakkumineDAO.delete(id);		
		return new ModelAndView("redirect:/pakkumised");
	}
}
