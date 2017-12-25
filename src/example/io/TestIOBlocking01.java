package example.io;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
public class TestIOBlocking01 {
	static void method01(){
		System.out.println("please input:");
		Scanner sc=new Scanner(System.in);
		int result=sc.nextInt();
		System.out.println(result);
		sc.close();
	}
	static void method02()throws Exception{
		BlockingQueue<String> q=
		//new ArrayBlockingQueue<>(3);
	    new LinkedBlockingQueue<>(3);
		//new LinkedBlockingDeque<>(3);
		q.put("A");
		q.put("B");
		q.put("C");
		System.out.println(q);
		//q.put("D");//����������(put����Ϊ����ʽ����)
		//q.add("D");���˻��׳��쳣
		//System.out.println(q);
		
		System.out.println(q.take());
		System.out.println(q.take());
		System.out.println(q.take());
		//System.out.println(q.take());//����ʽ����,����������.
		System.out.println(q.remove());//������ʽ����,�������׳��쳣
	}
	public static void main(String[] args)throws Exception {
		method02();
	}
}
