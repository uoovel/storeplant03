package net.code.station.controller;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.code.station.dao.AMeetriNaitDAO;
import net.code.station.dao.StaatusDAO;
import net.code.station.model.AMeetriNait;
import net.code.station.model.Contact;

import net.code.station.model.Periood;
import net.code.station.model.Staatus;
import net.code.station.model.ClientOrder;
import net.code.station.viewmodel.AjaparingView;


@Controller
public class MoodikudController {
	@Autowired
	private AMeetriNaitDAO ameetrinaitDAO;
	
	@RequestMapping(value = "/ameetriNaidud")
	public ModelAndView listAMeetriNait(ModelAndView model) {
		List<AMeetriNait> listAmeetriNait = ameetrinaitDAO.list();
		model.addObject("listAmeetriNait", listAmeetriNait);
		
		
		//AMeetriNait ameetriNait = ameetrinaitDAO.getByAjahetk(aeg);
		Integer maxid = ameetrinaitDAO.getMaxId();
	    AMeetriNait ameetriNait = ameetrinaitDAO.getLast(maxid);
		//Integer ameetriNait = 50;
		model.addObject("ameetriNait", ameetriNait);
		model.setViewName("moodikud/ameetriNaidud");		
		return model;
	}
	
	@RequestMapping(value = "/ajalugu")
	public ModelAndView ajalooParing(ModelAndView model) {
		
		AjaparingView ajaparingView = new AjaparingView();
		model.addObject("ajaparingView", ajaparingView);
		model.setViewName("moodikud/ajalugu");
		
		return model;
	}
	
	@RequestMapping(value = "/valiajaloodetailid", method = RequestMethod.GET)
	public ModelAndView valiDetailid(ModelAndView model, @ModelAttribute AjaparingView ajaparingView) {
		
		Timestamp aeg = ajaparingView.getAeg();		
		//järg
		//System.out.println("MoodikudController>valiajaloodetailid>ameetriNait: " + ameetriNait);
		AMeetriNait ameetriNait = ameetrinaitDAO.getByAjahetk(aeg);
		model.addObject("ameetriNait", ameetriNait);
		model.setViewName("moodikud/kuvaparing");
		return model;
	}
	
	
	
	
}
