package net.code.station.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.code.station.dao.AMeetriNaitDAO;
import net.code.station.dao.ArvestiDAO;
import net.code.station.dao.JaamaVoimsusDAO;
import net.code.station.dao.PerioodDAO;
import net.code.station.dao.TellimusDAO;
import net.code.station.model.AMeetriNait;
import net.code.station.model.Arvesti;
import net.code.station.model.Contact;
import net.code.station.model.JaamaVoimsus;
import net.code.station.model.KliendiArvesti;
import net.code.station.model.Pakkumine;
import net.code.station.model.Periood;
import net.code.station.model.Reegel;
import net.code.station.model.Tellimus;

@Controller
public class JaamaVoimsusController {
	@Autowired
	private JaamaVoimsusDAO jaamavoimsusDAO;
	@Autowired
	private TellimusDAO tellimusDAO;
	@Autowired
	private ArvestiDAO arvestiDAO;
	@Autowired
	private PerioodDAO perioodDAO;

	@RequestMapping(value = "/valiperioodvoimsuseleform", method = RequestMethod.GET)
	public ModelAndView perioodiVorm(ModelAndView model) {
				
		List<Periood> listPeriood = perioodDAO.list();		
		model.addObject("listPeriood", listPeriood);
		
		JaamaVoimsus jaamaVoimsus = new JaamaVoimsus();
		model.addObject("jaamaVoimsus", jaamaVoimsus);
		model.setViewName("voimsus/perioodVoimsuseleForm");
		return model;
	}
	
//	@RequestMapping(value = "/valiperioodvoimsusele", method = RequestMethod.GET)
//	public ModelAndView valiPerioodTellimuselel(ModelAndView model, 
//			@ModelAttribute JaamaVoimsus jaamaVoimsus) {		
//		
//		Integer perioodid = jaamaVoimsus.getPerioodid();
//		Periood periood = perioodDAO.get(perioodid);	
//		model.addObject("periood", periood);
//		model.setViewName("voimsus/jaamaVoimsused");
//		
//		return model;
//	}
	
	@RequestMapping(value = "/valiperioodvoimsusele")
	public ModelAndView listJaamaVoimsus(ModelAndView model,
			@ModelAttribute JaamaVoimsus jaamaVoimsus) {
		Integer perioodid = jaamaVoimsus.getPerioodid();
		Periood periood = perioodDAO.get(perioodid);
		List<JaamaVoimsus> listJaamaVoimsus = jaamavoimsusDAO.listByPeriood(perioodid);
		model.addObject("listJaamaVoimsus", listJaamaVoimsus);
		model.addObject("perioodid", perioodid);
		model.addObject("periood", periood);
		model.setViewName("voimsus/jaamaVoimsused");		
		return model;
	}
	
	@RequestMapping(value = "/arvutaToovoimsus", method = RequestMethod.GET)
	public ModelAndView arvutaToovoimsus(ModelAndView model,
			HttpServletRequest request) {
		
		
		//Perioodi klientide max voimsus
		//Integer perioodid = 1;
		Integer perioodid = Integer.parseInt(request.getParameter("perioodid"));
		//System.out.println("arvutaToovoimsus>perioodid: " + perioodid);
		Integer tellimuseVoimsus = 0;//jarg
		List<Tellimus> listTellimus = tellimusDAO.listByPeriood(perioodid);
		for (int i = 0; i < listTellimus.size(); i++) {
			Tellimus tellimus1 = listTellimus.get(i);
			Integer arvestiid = tellimus1.getArvestiid();
			
		    Arvesti arvesti = arvestiDAO.get(arvestiid);
		    Integer peavoimsus = arvesti.getPeavoimsus();
		    tellimuseVoimsus = tellimuseVoimsus + peavoimsus;
		}
		
		//Integer tellimusteArv = tellimusDAO.getmaxTellimus(perioodid);
		Integer maxTellimusteSumma = tellimuseVoimsus; 
		
		Double perioodiMaxTooVoimsus = maxTellimusteSumma * 0.7;//Yheaegsustegur		
		Integer perioodiMaxPakutav = perioodDAO.getMaxPakutav(perioodid);
		List<JaamaVoimsus> listJaamaVoimsus = jaamavoimsusDAO.listByPeriood(perioodid);
		
		List<Double> listKoormustegurDefault = new ArrayList<Double>();
		listKoormustegurDefault.add(0.7);//1
		listKoormustegurDefault.add(0.7);//2
		listKoormustegurDefault.add(0.7);//3
		listKoormustegurDefault.add(0.7);//4
		listKoormustegurDefault.add(0.7);//5
		listKoormustegurDefault.add(0.7);//6
		listKoormustegurDefault.add(0.7);//7
		listKoormustegurDefault.add(0.9);//8
		listKoormustegurDefault.add(0.9);//9
		listKoormustegurDefault.add(1.0);//10
		listKoormustegurDefault.add(1.0);//11
		listKoormustegurDefault.add(1.0);//12
		listKoormustegurDefault.add(1.0);//13
		listKoormustegurDefault.add(1.0);//14
		listKoormustegurDefault.add(1.0);//15
		listKoormustegurDefault.add(1.0);//16
		listKoormustegurDefault.add(1.0);//17
		listKoormustegurDefault.add(1.0);//18
		listKoormustegurDefault.add(1.0);//19
		listKoormustegurDefault.add(1.0);//20
		listKoormustegurDefault.add(1.0);//21
		listKoormustegurDefault.add(1.0);//22
		listKoormustegurDefault.add(0.8);//23
		listKoormustegurDefault.add(0.8);//24
		//System.out.println("arvutaToovoimsus>listJaamaVoimsus: " + listJaamaVoimsus);
		if(listJaamaVoimsus.isEmpty()) {
			listJaamaVoimsus = jaamavoimsusDAO.listByPeriood(1);
			//System.out.println("arvutaToovoimsus>listJaamaVoimsus: " + listJaamaVoimsus);
			//fill default;
			for(int i = 0; i < listJaamaVoimsus.size(); i++) {
				
				JaamaVoimsus jaamaVoimsus10 = listJaamaVoimsus.get(i);
				Time aeg = jaamaVoimsus10.getAeg();
				Double koormusTegur = jaamaVoimsus10.getKoormusTegur();
				JaamaVoimsus jaamaVoimsus11 = new JaamaVoimsus(
						perioodid, aeg, null, null, null, null, koormusTegur);
				//System.out.println("arvutaToovoimsus>perioodid: " + perioodid);
				jaamavoimsusDAO.save(jaamaVoimsus11);
			}
		}
		
		//Arvuta töövõimsused;
		for(int i = 0; i < listJaamaVoimsus.size(); i++) {
			//lisab toovoimsuse
			JaamaVoimsus jaamaVoimsus2 = listJaamaVoimsus.get(i);
			Integer pakutav = jaamaVoimsus2.getPakutav();
			Double koormustegur = jaamaVoimsus2.getKoormusTegur();
			//if(koormustegur == null) {
			//	koormustegur = listKoormustegurDefault.get(i);
			//}
			Integer tooVoimsus = (int) (perioodiMaxTooVoimsus * koormustegur);
			Integer jaamavoimsusid = jaamaVoimsus2.getId();
			jaamaVoimsus2.setToo(tooVoimsus);
			//lisab pakutava võimsuse;
			Integer pakutavVoimsus = (int)(perioodiMaxPakutav * koormustegur);
			jaamaVoimsus2.setPakutav(pakutavVoimsus);
			
			jaamavoimsusDAO.update(jaamaVoimsus2);
		}
		
		Periood periood = perioodDAO.get(perioodid);
		
		model.addObject("listJaamaVoimsus", listJaamaVoimsus);
		model.addObject("periood", periood);
		model.setViewName("voimsus/jaamaVoimsused");
		return model;
	}
	
	
}
