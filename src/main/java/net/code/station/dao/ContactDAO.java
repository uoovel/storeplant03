package net.code.station.dao;

import java.util.List;

import net.code.station.model.Contact;

public interface ContactDAO {
	public int save(Contact contact);
	
	public int update(Contact contact);
	
	public Contact get(Integer id);
	
	public int delete(Integer id);
	
	public List<Contact> list();
	
	public Contact getByUserId(Integer userId);

}
