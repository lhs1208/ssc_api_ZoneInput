package study.java.model;

public class MapItem {
	
	private int num = 0;
	private String zone_region1_short;
	private String zone_name = null;
	private String zone_addr = null; 
	private String zone_lat = null; 
	private String zone_lng = null;
	public MapItem(int num, String zone_region1_short, String zone_name, String zone_addr, String zone_lat,
		String zone_lng) {
	    super();
	    this.num = num;
	    this.zone_region1_short = zone_region1_short;
	    this.zone_name = zone_name;
	    this.zone_addr = zone_addr;
	    this.zone_lat = zone_lat;
	    this.zone_lng = zone_lng;
	}
	public int getNum() {
	    return num;
	}
	public void setNum(int num) {
	    this.num = num;
	}
	public String getZone_region1_short() {
	    return zone_region1_short;
	}
	public void setZone_region1_short(String zone_region1_short) {
	    this.zone_region1_short = zone_region1_short;
	}
	public String getZone_name() {
	    return zone_name;
	}
	public void setZone_name(String zone_name) {
	    this.zone_name = zone_name;
	}
	public String getZone_addr() {
	    return zone_addr;
	}
	public void setZone_addr(String zone_addr) {
	    this.zone_addr = zone_addr;
	}
	public String getZone_lat() {
	    return zone_lat;
	}
	public void setZone_lat(String zone_lat) {
	    this.zone_lat = zone_lat;
	}
	public String getZone_lng() {
	    return zone_lng;
	}
	public void setZone_lng(String zone_lng) {
	    this.zone_lng = zone_lng;
	}
	@Override
	public String toString() {
	    return "MapItem [num=" + num + ", zone_region1_short=" + zone_region1_short + ", zone_name=" + zone_name
		    + ", zone_addr=" + zone_addr + ", zone_lat=" + zone_lat + ", zone_lng=" + zone_lng + "]";
	}

	
}