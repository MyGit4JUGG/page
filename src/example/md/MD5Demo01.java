package example.md;

import java.security.MessageDigest;
import java.util.Arrays;

public class MD5Demo01 {

	public static void main(String[] args) 
	throws Exception{
		String pwd="123456";
		//��ȡһ��md5ժҪ����
		MessageDigest md=
		MessageDigest.getInstance("MD5");//SHA
		//��ȡ�ַ�����md5ժҪ��Ϣ
		byte[] buf=md.digest(pwd.getBytes());//128bit
		//System.out.println(buf.length);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<buf.length;i++){
			String s=Integer.toHexString(buf[i]&0xFF);
			if(s.length()==1){
				s="0"+s;
			}
			sb.append(s);
		}
		System.out.println(sb.toString());
	}
}
