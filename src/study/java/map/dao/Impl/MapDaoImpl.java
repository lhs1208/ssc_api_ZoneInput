package study.java.map.dao.Impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import study.java.helper.HttpHelper;
import study.java.helper.JsonHelper;
import study.java.map.dao.MapDao;
import study.java.model.MapItem;

public class MapDaoImpl implements MapDao {

	private Connection conn;

	public MapDaoImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public List<MapItem> getMapSerarchList(String keyword) {
		List<MapItem> list = null;

		String url = "http://api.socar.kr/reserve/oneway_zone_list?type=start&_=1471854840070";

		InputStream is = HttpHelper.getInstance().getWebData(url, "utf-8");

		if (is == null) {
			System.out.println("데이터 다운로드 실패");
			return null;
		}

		list = new ArrayList<MapItem>();

		JSONObject json = JsonHelper.getInstance().getJSONObject(is, "utf-8");

		JSONArray result = json.getJSONArray("result");

		String sql = "INSERT INTO zone (zone_region1_short,zone_name,zone_addr" + ",zone_lat,zone_lng) VALUES (?,?,?,?,?)";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		for (int i = 0; i < result.length(); i++) {

			JSONObject obj = result.getJSONObject(i);

			String zone_region1_short = obj.getString("zone_region1_short");
			String zone_name = obj.getString("zone_name");
			String zone_addr = obj.getString("zone_addr");
			String zone_lat = obj.getString("zone_lat");
			String zone_lng = obj.getString("zone_lng");
			try {
				pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				pstmt.setString(1, zone_region1_short);
				pstmt.setString(2, zone_name);
				pstmt.setString(3, zone_addr);
				pstmt.setString(4, zone_lat);
				pstmt.setString(5, zone_lng);

				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				rs.next();

			} catch (SQLException e) {
				System.out.println("fail" + e.getMessage());
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}

			list.add(new MapItem(0, zone_region1_short, zone_name, zone_addr, zone_lat, zone_lng));

		}

		return list;
	}

	@Override
	public int insert(MapItem params) {
	    // TODO Auto-generated method stub
	    return 0;
	}

	@Override
	public int delete(int params) {
	    // TODO Auto-generated method stub
	    return 0;
	}

	@Override
	public int update(MapItem params) {
	    // TODO Auto-generated method stub
	    return 0;
	}

	@Override
	public MapItem selectOne(int params) {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public List<MapItem> select() {
	    // TODO Auto-generated method stub
	    return null;
	}

}
