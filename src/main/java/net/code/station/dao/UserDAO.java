package net.code.station.dao;

import java.util.List;

import net.code.station.model.User;

public interface UserDAO {
	public int save(User user);
	
	public int update(User user);
	
	public User get(Integer id);
	
	public int delete(Integer id);
	
	public List<User> list();

	public Integer getUserIdByUName(String userName);

}
