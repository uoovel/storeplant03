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

import net.code.station.dao.ArvestiNaitDAO;
import net.code.station.dao.ContactDAO;
import net.code.station.dao.HindDAO;
import net.code.station.dao.KliendiArvestiDAO;
import net.code.station.dao.PakkumineDAO;
import net.code.station.dao.PerioodDAO;
import net.code.station.dao.TarbitudEnergiaDAO;
import net.code.station.dao.TellimusDAO;
import net.code.station.dao.UserDAO;
import net.code.station.model.ArvestiNait;
import net.code.station.model.Contact;
import net.code.station.model.Hind;
import net.code.station.model.KliendiArvesti;
import net.code.station.model.Pakkumine;
import net.code.station.model.Periood;
import net.code.station.model.TarbitudEnergia;
import net.code.station.model.Tellimus;


@Controller
public class TarbitudEnergiaController {
	
	@Autowired
	private ArvestiNaitDAO arvestinaitDAO;
	@Autowired
	private KliendiArvestiDAO kliendiarvestiDAO;
	@Autowired
	private ContactDAO klientDAO;
	@Autowired
	private TarbitudEnergiaDAO tarbitudenergiaDAO;
	@Autowired
	private PerioodDAO perioodDAO;
	@Autowired
	private HindDAO hindDAO;
	@Autowired
	private TellimusDAO tellimusDAO;
	@Autowired
	private PakkumineDAO pakkumineDAO;
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value = "/energiaarvutuslaud", method = RequestMethod.GET)
	public ModelAndView energiaArvutusLaud(ModelAndView model, HttpServletRequest request) {
		Integer klientid = Integer.parseInt(request.getParameter("id"));
		KliendiArvesti arv = kliendiarvestiDAO.getArvestiId(klientid);
		Integer arvestiid = arv.getArvestiid();
		TarbitudEnergia tarbitudenergia = new TarbitudEnergia(arvestiid, null, null, null, null, null, null, null);
		model.addObject("tarbitudenergia", tarbitudenergia);
		List<Periood> listPeriood = perioodDAO.list();
		model.addObject("listPeriood", listPeriood);
		model.setViewName("personal/energiaarvutusForm");
		///////		
		return model;
	}
	@RequestMapping(value = "/arvutaenergiaraw", method = RequestMethod.POST)
	public ModelAndView arvutaenergiaRaw(
			@ModelAttribute TarbitudEnergia tarbitudEnergia) {		
		//Sisendmuutujate vastuvõtt
		Integer arvestiid = tarbitudEnergia.getArvestiid();	
		Integer perioodid = tarbitudEnergia.getPerioodid();
		//Vanade arvutustulemuste kustutamine
		tarbitudenergiaDAO.deleteKirjed(arvestiid, perioodid);	
		//Hinna päring tellimuse kaudu pakkumisest
		KliendiArvesti arv = kliendiarvestiDAO.getArvestiByArvestiId(arvestiid);
		Integer klientid = arv.getKlientid();
		Tellimus tellimus = tellimusDAO.getByKlient(klientid, perioodid);
		Integer pakkumineid = tellimus.getPakkumineid();
		Pakkumine pakkumine = pakkumineDAO.get(pakkumineid);	
		Integer hinnaVaartus = pakkumine.getHind();
		//Loendi päringu atribuutide loomine
		Periood periood = perioodDAO.get(perioodid);
		Timestamp algus = periood.getAlates();
		Timestamp lopp = periood.getKuni();
		String perNimetus = periood.getPerNimetus();
		//Arvestinäitude loendi päring
		List<ArvestiNait> listArvestiNait = arvestinaitDAO
				.listForPeriod(arvestiid, algus, lopp);
		//tarbitud energia arvutamine igale loendi kirjele
		energiaKirjele(listArvestiNait, hinnaVaartus, perioodid,
				arvestiid, perNimetus);		
		return new ModelAndView("redirect:/kliendid");
	}
	
	private void energiaKirjele(List<ArvestiNait> listArvestiNait, 
			Integer hinnaVaartus, Integer perioodid,
			Integer arvestiid, String perNimetus) {
		for(int i = 0; i < (listArvestiNait.size()-1); i++) {
			ArvestiNait arvestiNait0 = listArvestiNait.get(i);
			ArvestiNait arvestiNait1 = listArvestiNait.get(i+1);
			Integer energia0 = arvestiNait0.getEnergia();
			Integer energia1 = arvestiNait1.getEnergia();
			
			Timestamp alates = arvestiNait0.getAeg();				
			Timestamp kuni = arvestiNait1.getAeg();
			Integer tarbEneTunnis = energia1 - energia0;			
			Integer summa = tarbEneTunnis * hinnaVaartus;
			//Tarbitud energia objekti loomine
			TarbitudEnergia tarbitudEnergiaM = new TarbitudEnergia(
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
		
		
		
		KliendiArvesti arv = kliendiarvestiDAO.getArvestiId(klientid);
		Integer arvestiid = arv.getArvestiid();
		TarbitudEnergia tarbitudenergia = new TarbitudEnergia(arvestiid, null, null, null, null, null, null, null);
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
	public String vaataTarbitudEnergiaid(@Valid @ModelAttribute("tarbitudEnergia") 
			TarbitudEnergia tarbitudEnergia, Errors errors, Model model, 
			HttpServletRequest request,	Authentication authentication) {
		Integer arvestiid = tarbitudEnergia.getArvestiid();
		KliendiArvesti kliendiArvesti = kliendiarvestiDAO
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
		List<TarbitudEnergia> listTarbitudEnergia = 
				tarbitudenergiaDAO.list(arvestiid, perioodid);
		for(int i = 0; i < (listTarbitudEnergia.size()); i++) {	
			TarbitudEnergia tarbitudEnergiaF = listTarbitudEnergia.get(i);
			Integer summa = tarbitudEnergiaF.getSumma();
			//osaenergia lisamine summale
			kokkuSumma = kokkuSumma + summa;
			if(i == 0) { 
				perioodiAlgus = tarbitudEnergiaF.getAlates();
			}//perioodi alguse määramine
			if(i == listTarbitudEnergia.size()-1) {
				perioodiLopp = tarbitudEnergiaF.getKuni();
			}//perioodi lõpu määramine			
		}
		Double kokkuDouble = (kokkuSumma.doubleValue())/100; //eurodesse
		model.addAttribute("perioodiAlgus", perioodiAlgus);
		model.addAttribute("perioodiLopp", perioodiLopp);		
		model.addAttribute("kokkuSumma", kokkuDouble);
		model.addAttribute("listTarbitudEnergia", listTarbitudEnergia);
		return model;
	}
}
