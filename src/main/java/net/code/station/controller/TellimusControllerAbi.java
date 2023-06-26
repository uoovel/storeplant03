package net.code.station.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import net.code.station.dao.ArvestiDAO;
import net.code.station.dao.BilansihaldurDAO;
import net.code.station.dao.ContactDAO;
import net.code.station.dao.JaamaVoimsusDAO;
import net.code.station.dao.KliendiArvestiDAO;
import net.code.station.dao.PakkumineDAO;
import net.code.station.dao.PerioodDAO;
import net.code.station.dao.ReegelDAO;
import net.code.station.dao.StaatusDAO;
import net.code.station.dao.TellimusDAO;
import net.code.station.dao.UserDAO;
import net.code.station.model.Arvesti;
import net.code.station.model.Bilansihaldur;
import net.code.station.model.Contact;
import net.code.station.model.KliendiArvesti;
import net.code.station.model.Pakkumine;
import net.code.station.model.Periood;
import net.code.station.model.Reegel;
import net.code.station.model.Staatus;
import net.code.station.model.Tellimus;
import net.code.station.viewmodel.TellimusViewL;


public class TellimusControllerAbi {
	
	
	
	public TellimusControllerAbi() {
		
	}
	
	public List<Periood> filtreeriPerioodid(String userName,
			PerioodDAO perioodDAO, UserDAO userDAO,
			ContactDAO klientDAO, TellimusDAO tellimusDAO) {
		//Filtreerida perioodid algus
		List<Periood> listPeriood = perioodDAO.list();
		List<Periood> filtListPeriood = new ArrayList<Periood>();
		Integer userid = userDAO.getUserIdByUName(userName);
		Contact klient = klientDAO.getByUserId(userid);
		Integer klientid = klient.getId();	
 		List<Integer> perioodIdTellimusOnList = tellimusDAO.listPerioodIdTellimusOn(klientid);
		for(int i = 0; i<listPeriood.size(); i++) {
			Periood periood = listPeriood.get(i);			
			Integer perioodid = periood.getId();
			Boolean tulebKaasa = true;
			for(int j = 0; j < perioodIdTellimusOnList.size(); j++) {
				Integer perioodidFilter = perioodIdTellimusOnList.get(j);
				if(perioodid==perioodidFilter) {
					tulebKaasa = false;					
				}				
			}
			if(tulebKaasa==true) {
				filtListPeriood.add(periood);
			}			
		}//Filtreerida perioodid lopp
		return filtListPeriood;
	}
	
	public Model modifyModel2(Model model, String userName, Integer perioodid,
			UserDAO userDAO, ContactDAO klientDAO, PerioodDAO perioodDAO,
			PakkumineDAO pakkumineDAO,	JaamaVoimsusDAO jaamavoimsusDAO, 
			TellimusDAO tellimusDAO, ArvestiDAO arvestiDAO, 
			KliendiArvestiDAO kliendiarvestiDAO, ReegelDAO reegelDAO,
			BilansihaldurDAO bilansihaldurDAO, HttpServletResponse response)
					throws IOException{		
		Integer userid = userDAO.getUserIdByUName(userName);
		Contact klient = klientDAO.getByUserId(userid);
		Integer klientid = klient.getId();	
		Periood periood = perioodDAO.get(perioodid);		
		boolean reegelEdasi = kontrolliVoimsust(klientid, perioodid, 
				jaamavoimsusDAO, tellimusDAO,
				arvestiDAO, kliendiarvestiDAO);					
		if(reegelEdasi == false) {//reageeri, kui voimsust vähe	
			String teade = koostaTeade(kliendiarvestiDAO, arvestiDAO, reegelDAO,
					bilansihaldurDAO, klientid, periood);			
			response.sendRedirect(
					"/Station01/userWelcomeSec?user="+userName+"&message="+teade);
		}		
		if(reegelEdasi == true) {//lisa atribuudid korrektsele  
			model = lisaAtribuudidKorrektsele(model, klient, periood, klientid,
					kliendiarvestiDAO,	arvestiDAO, userName, pakkumineDAO);			
		}
		return model;			
	}

	private String koostaTeade(KliendiArvestiDAO kliendiarvestiDAO, ArvestiDAO arvestiDAO, ReegelDAO reegelDAO,
			BilansihaldurDAO bilansihaldurDAO, Integer klientid, Periood periood) {
		KliendiArvesti kliendiArvesti = kliendiarvestiDAO.getArvestiId(klientid);
		Integer arvestiid = kliendiArvesti.getArvestiid();
		Arvesti arvesti = arvestiDAO.get(arvestiid);
		String teade = "teade koostamata";
		Reegel reegel = reegelDAO.getActiveRule();
		Integer reegelid = reegel.getId();
		if(reegelid == 1) {								
			Integer bilansihaldurid = arvesti.getBilansihaldurid();
			Bilansihaldur bilansihaldur = bilansihaldurDAO.get(bilansihaldurid);
			teade = "Soovitud perioodiks tellimust esitada ei saa: " 
			+ periood.getPerNimetus()
			+ " Nimetatud perioodil on Teie elektri pakkujaks: "
			+ bilansihaldur.getNimetus();											
		}		
		return teade;
	}

	private boolean kontrolliVoimsust(Integer klientid, Integer perioodid, 
			JaamaVoimsusDAO jaamavoimsusDAO, TellimusDAO tellimusDAO,
			ArvestiDAO arvestiDAO, KliendiArvestiDAO kliendiarvestiDAO ) {
		//***********Kontrolli voimsuse olemasolu ja reeglit
		
				//Kysi pakkumise maksimum selleks perioodiks, millele soovitakse tellimust teha
				//tabelist jaamavoimsus, veerg pakkumine
				Integer maxPakkumine = jaamavoimsusDAO.getmaxPakkumine(perioodid);
				//Koigepealt tellimuste list perioodil
				//Seejarel iga elemendi arvesti järgi summeerides saada tellimuste võimsus
				Integer tellimuseVoimsus = 0;
				//List<Tellimus> listTellimus = tellimusDAO.listByKlientPeriood(klientid, perioodid);
				List<Tellimus> listTellimus = tellimusDAO.listByPeriood(perioodid);
				for (int i = 0; i < listTellimus.size(); i++) {
					Tellimus tellimus1 = listTellimus.get(i);
					Integer arvestiid = tellimus1.getArvestiid();
					
				    Arvesti arvesti = arvestiDAO.get(arvestiid);
				    Integer peavoimsus = arvesti.getPeavoimsus();
				    tellimuseVoimsus = tellimuseVoimsus + peavoimsus;
				}
				//Lisada ka tema enda voimsus
				
				KliendiArvesti kliendiArvesti = kliendiarvestiDAO.getArvestiId(klientid);
				Integer arvestiid = kliendiArvesti.getArvestiid();
				Arvesti arvesti = arvestiDAO.get(arvestiid);
				Integer peavoimsus = arvesti.getPeavoimsus();
				tellimuseVoimsus = tellimuseVoimsus + peavoimsus;
				//Integer tellimusteArv = tellimusDAO.getmaxTellimus(perioodid);
				Double maxTellimus = tellimuseVoimsus*0.7; //Yheaegsustegur
				//System.out.println("Controller>valiperioodtelimusele> maxPakkumine: " + maxPakkumine);
				//System.out.println("Controller>valiperioodtelimusele> maxTellimus: " + maxTellimus);
				//System.out.println("TellimusControllerAbi>kontrolliVoimsust>maxPakkumine: " + maxPakkumine);
				//maxTellimus = 100.0;
				boolean reegelEdasi = true;
						
				if(maxPakkumine < maxTellimus) {
					reegelEdasi = false;
				}	
				//****************	
		return reegelEdasi;
	}

	private Model lisaAtribuudidKorrektsele(Model model,
			 Contact klient, Periood periood, Integer klientid,
			KliendiArvestiDAO kliendiarvestiDAO,
			ArvestiDAO arvestiDAO, String userName, PakkumineDAO pakkumineDAO) {
		//List<Contact> listKlient = klientDAO.list();
		Integer perioodid = periood.getId();
		List<Pakkumine> listPakkumine = pakkumineDAO.listByPeriood(perioodid);
		model.addAttribute("klient", klient);	   
		model.addAttribute("listPakkumine", listPakkumine);		
		model.addAttribute("periood", periood);
		List<KliendiArvesti> listKliendiArvesti = kliendiarvestiDAO.getListByKlientId(klientid);
		List<Arvesti> listArvesti = new ArrayList<Arvesti>();
		for (int i = 0; i < listKliendiArvesti.size(); i++) {
			KliendiArvesti kliendiArvesti = listKliendiArvesti.get(i);
	        Integer arvestiid = kliendiArvesti.getArvestiid();
			Arvesti arvesti = arvestiDAO.get(arvestiid);
			listArvesti.add(arvesti);
		}
		model.addAttribute("listArvesti", listArvesti);
		model.addAttribute("userName", userName);
		return model;
	}

	public List<TellimusViewL> filtreeriTellimusViewL(List<TellimusViewL> listTellimusViewL) {
		//filtreeri
		List<TellimusViewL> listTellimusViewLFilter = new ArrayList<>();
		for(int i = 0; i < listTellimusViewL.size(); i++) {
			//Võrrelda perioodi lõpu kuupäeva ja praegust kuupäeva
			TellimusViewL tellimusViewL = listTellimusViewL.get(i);
			Timestamp alates = tellimusViewL.getAlates();
			long perioodiAlgus = alates.getTime();
			//long praeguneKuupaev = (Timestamp.valueOf("2022-04-28 00:00:00")).getTime();
			//stackabuse.com/how-to-get-current-date-and-time-in-java/
			LocalDateTime dtime = LocalDateTime.now();
			long praeguneKuupaev = (Timestamp.valueOf(dtime)).getTime();						
			if(praeguneKuupaev<perioodiAlgus) {		
				listTellimusViewLFilter.add(tellimusViewL);
			}
		}//filtreeri
		return listTellimusViewLFilter;
	}	

}
