package study.java.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class HttpHelper {
	private static HttpHelper current = null;
	
	public static HttpHelper getInstance() {
		
		if(current == null) {
			current = new HttpHelper();
		}
		return current;
	}
	public static void freeinstance() {
		current = null;
	}
	private HttpHelper(){
		super();
	}
	public InputStream getWebData(String url, String encType) {

		//접속 대기 제한시간
		int timeout = 30000;
		
		//통신객체
		HttpClient client = null;
		//기본 환경설정 객체
		HttpParams params = new BasicHttpParams();
		//프로토콜 버전
		params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, 
				HttpVersion.HTTP_1_1);
		//요청 제한 시간
		HttpConnectionParams.setConnectionTimeout(params, timeout);
		//응답 제한 시간
		HttpConnectionParams.setSoTimeout(params, timeout);
		//통신에 사용할 인코딩 값
		HttpProtocolParams.setContentCharset(params, encType);
		//접속 기능을 위한 객체 생성
		client = new DefaultHttpClient(params);
		
		/***** 3) 접속하기 *****/
		//저장될 객체
		InputStream is = null;
		//통신에 필요한 요청 정보
		HttpGet httpget = new HttpGet(url);
		
		
		//브라우저로 접속해서 가져온다?
		httpget.setHeader("UserAgent", "Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko");
		
		
		try {
			HttpResponse response = client.execute(httpget);
			
			//웹 서버 응답 결과 코드
			//404 : page Not Found
			//500 : Server Error    //200 : OK
			int resultCode = response.getStatusLine().getStatusCode();
			
			//서버 응답이 정상일 경우에만 처리
			if(resultCode == HttpURLConnection.HTTP_OK) {
				//수신된 응답에서 실 데이터 추출
				HttpEntity entity = response.getEntity();
				BufferedHttpEntity buffer = new BufferedHttpEntity(entity);
			
				//추출한 데이터를 inputStream으로 변환
				is = buffer.getContent();
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//통신 해제
			client.getConnectionManager().shutdown();
		}

		return is;
	}
	
	
}
