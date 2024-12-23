import java.sql.Connection;
import java.util.List;

import study.java.helper.DBHelper;
import study.java.map.dao.MapDao;
import study.java.map.dao.Impl.MapDaoImpl;
import study.java.model.MapItem;

public class Main01 {
	public static void main(String[] args) {
		
		Connection conn = DBHelper.getInstance().open();
		if (conn == null) {
			System.out.println("실패");
			return;
		}
//		
		MapDao dao = new MapDaoImpl(conn);
		List<MapItem> list =  dao.getMapSerarchList("");
	
		if(list != null){
			System.out.println("가져오기 성공");
		} else {
			System.out.println("가져오기 실패");	
		}
			
		DBHelper.getInstance().close();
//		
//		
	}
}
