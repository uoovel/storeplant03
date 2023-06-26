package net.code.station.dao;

import java.sql.Timestamp;
import java.util.List;

import net.code.station.model.AMeetriNait;

public interface AMeetriNaitDAO {
	public List<AMeetriNait> list();

	public AMeetriNait getByAjahetk(Timestamp aeg);

	public AMeetriNait getLast(Integer maxid);

	public Integer getMaxId();
}
