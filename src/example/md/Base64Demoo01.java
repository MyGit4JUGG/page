package example.md;
import org.apache.commons.codec.binary.Base64;
public class Base64Demoo01 {
	public static void main(String[] args) {
		String s="123456";
		//byte buf[]=Base64.encodeBase64(s.getBytes());
		//对内容进行加密
		String result=
		Base64.encodeBase64String(s.getBytes());
		System.out.println(result);//MTIzNDU2
		//对内容进行解密
		byte buf[]=Base64.decodeBase64(result);
		System.out.println(new String(buf));
	}
}
