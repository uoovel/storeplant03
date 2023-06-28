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

import net.code.station.dao.MeterDAO;
import net.code.station.dao.EnergyProviderDAO;
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
import net.code.station.model.EnergyProvider;
import net.code.station.model.Contact;
import net.code.station.model.ClientMeter;
import net.code.station.model.Offer;
import net.code.station.model.Periood;
import net.code.station.model.Reegel;
import net.code.station.model.Staatus;
import net.code.station.model.ClientOrder;
import net.code.station.viewmodel.ClientOrderViewL;


public class ClientOrderControllerAux {
	
	
	
	public ClientOrderControllerAux() {
		
	}
	
	public List<Periood> filtreeriPerioodid(String userName,
			PerioodDAO perioodDAO, UserDAO userDAO,
			ContactDAO klientDAO, ClientOrderDAO tellimusDAO) {
		//Filtreerida perioodid algus
		List<Periood> listPeriood = perioodDAO.list();
		List<Periood> filtListPeriood = new ArrayList<Periood>();
		Integer userid = userDAO.getUserIdByUName(userName);
		Contact klient = klientDAO.getByUserId(userid);
		Integer klientid = klient.getId();	
 		List<Integer> perioodIdClientOrderOnList = tellimusDAO.listPerioodIdClientOrderOn(klientid);
		for(int i = 0; i<listPeriood.size(); i++) {
			Periood periood = listPeriood.get(i);			
			Integer perioodid = periood.getId();
			Boolean tulebKaasa = true;
			for(int j = 0; j < perioodIdClientOrderOnList.size(); j++) {
				Integer perioodidFilter = perioodIdClientOrderOnList.get(j);
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
			OfferDAO pakkumineDAO,	JaamaVoimsusDAO jaamavoimsusDAO, 
			ClientOrderDAO tellimusDAO, MeterDAO arvestiDAO, 
			ClientMeterDAO kliendiarvestiDAO, ReegelDAO reegelDAO,
			EnergyProviderDAO bilansihaldurDAO, HttpServletResponse response)
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

	private String koostaTeade(ClientMeterDAO kliendiarvestiDAO, MeterDAO arvestiDAO, ReegelDAO reegelDAO,
			EnergyProviderDAO bilansihaldurDAO, Integer klientid, Periood periood) {
		ClientMeter kliendiArvesti = kliendiarvestiDAO.getMeterId(klientid);
		Integer arvestiid = kliendiArvesti.getMeterid();
		Meter arvesti = arvestiDAO.get(arvestiid);
		String teade = "teade koostamata";
		Reegel reegel = reegelDAO.getActiveRule();
		Integer reegelid = reegel.getId();
		if(reegelid == 1) {								
			Integer bilansihaldurid = arvesti.getEnergyProviderid();
			EnergyProvider bilansihaldur = bilansihaldurDAO.get(bilansihaldurid);
			teade = "Soovitud perioodiks tellimust esitada ei saa: " 
			+ periood.getPerNimetus()
			+ " Nimetatud perioodil on Teie elektri pakkujaks: "
			+ bilansihaldur.getNimetus();											
		}		
		return teade;
	}

	private boolean kontrolliVoimsust(Integer klientid, Integer perioodid, 
			JaamaVoimsusDAO jaamavoimsusDAO, ClientOrderDAO tellimusDAO,
			MeterDAO arvestiDAO, ClientMeterDAO kliendiarvestiDAO ) {
		//***********Kontrolli voimsuse olemasolu ja reeglit
		
				//Kysi pakkumise maksimum selleks perioodiks, millele soovitakse tellimust teha
				//tabelist jaamavoimsus, veerg pakkumine
				Integer maxOffer = jaamavoimsusDAO.getmaxOffer(perioodid);
				//Koigepealt tellimuste list perioodil
				//Seejarel iga elemendi arvesti järgi summeerides saada tellimuste võimsus
				Integer tellimuseVoimsus = 0;
				//List<ClientOrder> listClientOrder = tellimusDAO.listByKlientPeriood(klientid, perioodid);
				List<ClientOrder> listClientOrder = tellimusDAO.listByPeriood(perioodid);
				for (int i = 0; i < listClientOrder.size(); i++) {
					ClientOrder tellimus1 = listClientOrder.get(i);
					Integer arvestiid = tellimus1.getMeterid();
					
				    Meter arvesti = arvestiDAO.get(arvestiid);
				    Integer peavoimsus = arvesti.getPeavoimsus();
				    tellimuseVoimsus = tellimuseVoimsus + peavoimsus;
				}
				//Lisada ka tema enda voimsus
				
				ClientMeter kliendiArvesti = kliendiarvestiDAO.getMeterId(klientid);
				Integer arvestiid = kliendiArvesti.getMeterid();
				Meter arvesti = arvestiDAO.get(arvestiid);
				Integer peavoimsus = arvesti.getPeavoimsus();
				tellimuseVoimsus = tellimuseVoimsus + peavoimsus;
				//Integer tellimusteArv = tellimusDAO.getmaxClientOrder(perioodid);
				Double maxClientOrder = tellimuseVoimsus*0.7; //Yheaegsustegur
				//System.out.println("Controller>valiperioodtelimusele> maxOffer: " + maxOffer);
				//System.out.println("Controller>valiperioodtelimusele> maxClientOrder: " + maxClientOrder);
				//System.out.println("ClientOrderControllerAbi>kontrolliVoimsust>maxOffer: " + maxOffer);
				//maxClientOrder = 100.0;
				boolean reegelEdasi = true;
						
				if(maxOffer < maxClientOrder) {
					reegelEdasi = false;
				}	
				//****************	
		return reegelEdasi;
	}

	private Model lisaAtribuudidKorrektsele(Model model,
			 Contact klient, Periood periood, Integer klientid,
			ClientMeterDAO kliendiarvestiDAO,
			MeterDAO arvestiDAO, String userName, OfferDAO pakkumineDAO) {
		//List<Contact> listKlient = klientDAO.list();
		Integer perioodid = periood.getId();
		System.out.println("ClientOrderControllerAux>lisaAtribuudidKorrektsele100: " + perioodid);
		List<Offer> listOffer = pakkumineDAO.listByPeriood(perioodid);
		
		model.addAttribute("klient", klient);	   
		model.addAttribute("listOffer", listOffer);		
		model.addAttribute("periood", periood);
		List<ClientMeter> listClientMeter = kliendiarvestiDAO.getListByKlientId(klientid);
		List<Meter> listMeter = new ArrayList<Meter>();
		for (int i = 0; i < listClientMeter.size(); i++) {
			ClientMeter kliendiArvesti = listClientMeter.get(i);
	        Integer arvestiid = kliendiArvesti.getMeterid();
			Meter arvesti = arvestiDAO.get(arvestiid);
			listMeter.add(arvesti);
		}
		model.addAttribute("listMeter", listMeter);
		model.addAttribute("userName", userName);
		return model;
	}

	public List<ClientOrderViewL> filtreeriClientOrderViewL(List<ClientOrderViewL> listClientOrderViewL) {
		//filtreeri
		List<ClientOrderViewL> listClientOrderViewLFilter = new ArrayList<>();
		for(int i = 0; i < listClientOrderViewL.size(); i++) {
			//Võrrelda perioodi lõpu kuupäeva ja praegust kuupäeva
			ClientOrderViewL tellimusViewL = listClientOrderViewL.get(i);
			Timestamp alates = tellimusViewL.getAlates();
			long perioodiAlgus = alates.getTime();
			//long praeguneKuupaev = (Timestamp.valueOf("2022-04-28 00:00:00")).getTime();
			//stackabuse.com/how-to-get-current-date-and-time-in-java/
			LocalDateTime dtime = LocalDateTime.now();
			long praeguneKuupaev = (Timestamp.valueOf(dtime)).getTime();						
			if(praeguneKuupaev<perioodiAlgus) {		
				listClientOrderViewLFilter.add(tellimusViewL);
			}
		}//filtreeri
		return listClientOrderViewLFilter;
	}	

}
