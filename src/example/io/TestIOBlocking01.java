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
		//q.put("D");//满了则阻塞(put方法为阻塞式方法)
		//q.add("D");满了会抛出异常
		//System.out.println(q);
		
		System.out.println(q.take());
		System.out.println(q.take());
		System.out.println(q.take());
		//System.out.println(q.take());//阻塞式方法,满了则阻塞.
		System.out.println(q.remove());//非阻塞式方法,空了则抛出异常
	}
	public static void main(String[] args)throws Exception {
		method02();
	}
}
