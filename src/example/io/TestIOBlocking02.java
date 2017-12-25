package example.io;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/**
 * 1.现有多个任务,希望交给一个线程,顺序执行,请问如何实现?
 * 2.利用Java技术自己实现一个阻塞式队列结构?
 * 3.Java中的定时任务调度如何实现?(Timer,ScheduledExecutorService)
 * 4.Spring中一般如何实现定时任务调度?(整合Quartz框架)
 * 5.自己实现一个LruCache缓存结构对象?(不依托于LinkedListHashMap)
 * */
class MyExecutor{//任务调度对象
	private static WorkThread thread=new WorkThread();
	private static BlockingQueue<Runnable> container=
			new ArrayBlockingQueue<>(3);
	public MyExecutor() {thread.start();}
	//?????需要添加一个容器,存储多个任务
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
	public void execute(Runnable r){//Runnable代表任务类型
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
