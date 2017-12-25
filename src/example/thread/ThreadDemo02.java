package example.thread;
import java.util.concurrent.ArrayBlockingQueue;
/**自己尝试基于数组结构实现一个阻塞式队列*/
class TeduBlockingQueue{
	private Object[] array;
	/**借助size记录有效元素个数*/
	private int size;
	/**@param capacity 用于定义数组的初始容量*/
	public TeduBlockingQueue(int capacity) {
		this.array=new Object[capacity];
	}
	/**
	 * synchronized作用
	 * 1)保证操作的原子性
	 * 2)保证数据的可见性
	 * 思考:volatile的作用:
	 * 1)保证可见性,但不能保证原子性
	 * 2)禁止指令排序
	 * */
	public  synchronized void put(Object obj){
		//1.检测容器是否已满,满了则阻塞
		while(size==array.length)
		try{this.wait();}catch(Exception e){e.printStackTrace();}
		//2.放数据
		array[size]=obj;
		//3.有效元素个数加1
		size++;
		//4.唤醒使用相同锁的正处于wait状态的线程
		this.notifyAll();
	}
	public synchronized Object take(){
		//1.判定容器是否为空,空则等待
		while(size==0)
	    try{this.wait();}catch(Exception e){e.printStackTrace();}
		//2.取数据
		Object temp=array[0];
		//3.移动元素
		System.arraycopy(array, 1, array, 0, size-1);
		//4.有效元素个数减一
		size--;
		array[size]=null;
		//5.唤醒需要具备相同锁且处于阻塞状态的线程
		this.notifyAll();
		//6.返回取出的数据
		return temp;
	}
	public int size(){
		return size;
	}
}
/**生产者线程*/
class Producer extends Thread{
	private TeduBlockingQueue tQueue;
	public Producer(TeduBlockingQueue tQueue) {
		this.tQueue=tQueue;
	}
	@Override
	public void run() {
		int i=1;
		while(true){
			tQueue.put(i);
			i++;
		}
	}
}
/**消费者*/
class Consumer extends Thread{
	private TeduBlockingQueue tQueue;
	public Consumer(TeduBlockingQueue tQueue) {
		this.tQueue=tQueue;
	}
	@Override
	public void run() {
	    while(true){
	    	Object data=tQueue.take();
	    	System.out.println("take:"+data);
	    	try{Thread.sleep(500);}
	    	catch(Exception e){e.printStackTrace();}
	    }
	}
}

public class ThreadDemo02 {
	public static void main(String[] args) 
	throws Exception{
		//1.初始化容器对象
		TeduBlockingQueue tQ=
		new TeduBlockingQueue(2);
		//2.构建生产者对象
		Producer pro=new Producer(tQ);
		pro.start();
		//3.构建消费者对象
		Consumer con=new Consumer(tQ);
		con.start();
		
	}
}




