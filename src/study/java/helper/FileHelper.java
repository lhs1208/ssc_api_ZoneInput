package study.java.helper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class FileHelper {
	private static FileHelper current = new FileHelper();
	public static FileHelper getInstance(){
		return current;
	}
	public static void freeInstance(){
		current = null;
	}
	private FileHelper(){
		super();
	}
	public boolean write(String filePath, byte[] data){
		boolean result = false;
		OutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
				out.write(data);
				System.out.println("[INFO]파일 저장 성공>>"+filePath);
				result  = true;
		} catch (FileNotFoundException e) {
			System.out.println("[ERROR] 지정된 경로를 찾을 수 없음>>"+filePath);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("[ERROR] 파일 저장 실패>>"+filePath);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("[ERROR] 알 수 없는 에러>>"+filePath);
			e.printStackTrace();
		}
		finally {
			if(out != null)
				try {
					out.close();
				} catch (IOException e) {
					System.out.print("[Error] 파일을 닫을 수 없음>>"+filePath);
					e.printStackTrace();
				}
		}
		
		return result;
	}
	public byte[] read(String filePath) {
		byte[] data = null;
		InputStream in =null;
		try {
			in = new FileInputStream(filePath);
			//열고있는파이트 크기
			data = new byte[in.available()];
			in.read(data);
			System.out.println("[INFO] 파일 읽기 성공>>"+filePath);
		} catch (FileNotFoundException e) {
			System.out.println("[ERROR] 지정된 경로를 찾을 수 없습니다.>>"+filePath);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("[ERROR] 파일을 읽을 수 없습니다.>>"+filePath);
			e.printStackTrace();
		} finally {
			if(in != null)
				try {
					in.close();
					System.out.println("닫기 성공>>");
				} catch (IOException e) {
					System.out.print("[ERROR] 파일을 닫을 수 없습니다.>>"+filePath);
					e.printStackTrace();
				}
		} //end try~catch~finally
		

		return data;
	}
	public boolean writeString(String filePath, String content, String encType){
		boolean result = false;
		byte[] data = null;
		try {
			data = content.getBytes(encType);
		} catch (UnsupportedEncodingException e) {
			System.out.println("[ERROR]인코딩 지정 에러>>"+filePath);e.printStackTrace();
		} catch (Exception e) {
			System.out.println("[ERROR]알수없는 에러>>"+filePath);
			e.printStackTrace();
		}
		
		result = this.write(filePath, data);
		return result;
		
	}
	public String readString(String filePath, String encType) {
		String content = null;
		byte[] data = this.read(filePath);
		try {
			content = new String(data,encType);
			content = content.trim();
		} catch (UnsupportedEncodingException e) {
			System.out.println("[ERROR]인코딩 지정 에러>>");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("[ERROR]알수없는 에러>>"+filePath);
			e.printStackTrace();
		}
		return content;
	}
	
}
