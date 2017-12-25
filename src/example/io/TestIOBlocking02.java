package example.io;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/**
 * 1.���ж������,ϣ������һ���߳�,˳��ִ��,�������ʵ��?
 * 2.����Java�����Լ�ʵ��һ������ʽ���нṹ?
 * 3.Java�еĶ�ʱ����������ʵ��?(Timer,ScheduledExecutorService)
 * 4.Spring��һ�����ʵ�ֶ�ʱ�������?(����Quartz���)
 * 5.�Լ�ʵ��һ��LruCache����ṹ����?(��������LinkedListHashMap)
 * */
class MyExecutor{//������ȶ���
	private static WorkThread thread=new WorkThread();
	private static BlockingQueue<Runnable> container=
			new ArrayBlockingQueue<>(3);
	public MyExecutor() {thread.start();}
	//?????��Ҫ���һ������,�洢�������
	static class WorkThread extends Thread{
		@Override
		public void run() {
			while(true){
				try{
				Runnable r=container.take();
				r.run();
				}catch(Exception e){e.getMessage();}
			}
		}
	}
	public void execute(Runnable r){//Runnable������������
	    try{
		container.put(r);
	    }catch(Exception e){
	    e.printStackTrace();
	    }
	}
}
public class TestIOBlocking02 {
     public static void main(String[] args) {
    	 MyExecutor e=new MyExecutor();
    	 e.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("task01");
			}
		 });
    	 e.execute(new Runnable() {
    		 @Override
    		 public void run() {
    			 System.out.println("task02");
    		 }
    	 });
	 }
	
}
