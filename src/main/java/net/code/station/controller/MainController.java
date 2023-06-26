package net.code.station.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.code.station.config.security.LoginCredentials;
import net.code.station.dao.ContactDAO;
import net.code.station.dao.KasutajaRollisDAO;
import net.code.station.dao.UserDAO;
import net.code.station.model.Contact;
import net.code.station.model.KasutajaRollis;
import net.code.station.model.User;

@Controller
public class MainController {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private KasutajaRollisDAO kasutajarollisDAO;
	
	//String user = "user";
	
	@RequestMapping(value = "/")
	public ModelAndView indexPage(ModelAndView model) {  //home
		
		//System.out.println("MainController");
		//return "index";
		//List<Contact> listContact = contactDAO.list();
		//model.addObject("listContact", listContact);
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value = "/kliendid")
	public ModelAndView listContact(ModelAndView model) {  //home
		
		//System.out.println("MainController");
		//return "index";
		List<Contact> listContact = contactDAO.list();
		model.addObject("listContact", listContact);
		model.setViewName("personal/kliendid");
		return model;
	}
	
	@RequestMapping(value = "/loginn", method = RequestMethod.GET)
	public ModelAndView loginn(ModelAndView model) {  //home
				
		User newUser = new User();
		model.addObject("user", newUser);
		model.setViewName("loginForm");
		return model;
	}
	@RequestMapping(value = "/loginnSec", method = RequestMethod.GET)
	public ModelAndView loginnSec(ModelAndView model, HttpServletRequest request) {  //home
		String teade = request.getParameter("teade");		
		LoginCredentials loginCredentials = new LoginCredentials();
		model.addObject("loginCredentials", loginCredentials);
		model.addObject("teade", teade);
		model.setViewName("loginFormSec");
		return model;
	}	
	@RequestMapping(value = "/userWelcomeSec", method = RequestMethod.GET)
	@PreAuthorize("#request.getParameter('user') == authentication.name"
			+ "|| authentication.name == 'personal01'")
	public ModelAndView loginUserSec(
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//System.out.println("MainController>loginUserSec");
		String userName = request.getParameter("user");
		String teade = request.getParameter("message");
		Integer userId = userDAO.getUserIdByUName(userName); 
		//System.out.println("userName: " + userName);
		Contact klient = new Contact();
		klient = contactDAO.getByUserId(userId);
		ModelAndView model = new ModelAndView();
		model.addObject("klient", klient);
		model.addObject("userName", userName);
		model.addObject("teade", teade);
		if(userName.equals("personal01")) {
			response.sendRedirect("/Station01/personalWelcomeSec");
		}
		model.setViewName("welcome");
		return model;			
	}
	@RequestMapping(value = "/personalWelcomeSec", method = RequestMethod.GET)
	public ModelAndView loginPersonalSec() {
		//System.out.println("MainController>loginPersonalSec");
		ModelAndView model = new ModelAndView();	
		model.setViewName("personal/personalwelcome"); //järg
		return model;			
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
				
		Contact newContact = new Contact();
		model.addObject("contact", newContact);
		model.setViewName("contactForm");
		return model;
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Contact contact) {
		if (contact.getId() == null) {
			contactDAO.save(contact);
		} else {
			contactDAO.update(contact);
		}	
		return new ModelAndView("redirect:/personalWelcomeSec");
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Contact contact = contactDAO.get(id);
		ModelAndView model = new ModelAndView("contactForm");
		model.addObject("contact", contact);		
		return model;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteContact(@RequestParam Integer id) {
		contactDAO.delete(id);		
		return new ModelAndView("redirect:/personalWelcomeSec");
	}
	
	//@RequestMapping(value = "/api/logout", method = RequestMethod.POST)
	///public ModelAndView logout(@ModelAttribute User user) {
		
	//	ModelAndView model = new ModelAndView("index");
	//	return model;
	//}

}
