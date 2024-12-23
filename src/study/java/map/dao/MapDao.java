package study.java.map.dao;

import java.util.List;

import study.java.model.MapItem;

public interface MapDao {
	
	public List<MapItem> getMapSerarchList(String keyword);
	
	public int insert(MapItem params);
	
	public int delete(int params);
	
	public int update(MapItem params);
	
	public MapItem selectOne(int params);
	
	public List<MapItem> select();
	
	
}
