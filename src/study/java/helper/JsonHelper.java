package study.java.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonHelper {
	private static JsonHelper current = null;
	
	public static JsonHelper getInstance() {
		
		if(current == null) {
			current = new JsonHelper();
		}
		return current;
	}
	public static void freeinstance() {
		current = null;
	}
	private JsonHelper(){
		super();
	}
	
	/***
	 * InPutStream -> JSONObject
	 * is -> InpuStream
	 * encType - InputStream   *.xml
	 * return JSONObject
	 */

	public JSONObject getJSONObject(InputStream is, String encType) {
		JSONObject json = null;
		
		String source = null;
		
		BufferedReader reader = null;
		try { 
			String line = null;
			StringBuilder sb = new StringBuilder();
			reader = new BufferedReader(new InputStreamReader(is, encType));
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			source =  sb.toString();
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} //end try ~ catch ~ finally
		
		try {
			json = new JSONObject(source);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
		
	}


}
