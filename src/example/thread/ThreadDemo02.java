package example.thread;
import java.util.concurrent.ArrayBlockingQueue;
/**�Լ����Ի�������ṹʵ��һ������ʽ����*/
class TeduBlockingQueue{
	private Object[] array;
	/**����size��¼��ЧԪ�ظ���*/
	private int size;
	/**@param capacity ���ڶ�������ĳ�ʼ����*/
	public TeduBlockingQueue(int capacity) {
		this.array=new Object[capacity];
	}
	/**
	 * synchronized����
	 * 1)��֤������ԭ����
	 * 2)��֤���ݵĿɼ���
	 * ˼��:volatile������:
	 * 1)��֤�ɼ���,�����ܱ�֤ԭ����
	 * 2)��ָֹ������
	 * */
	public  synchronized void put(Object obj){
		//1.��������Ƿ�����,����������
		while(size==array.length)
		try{this.wait();}catch(Exception e){e.printStackTrace();}
		//2.������
		array[size]=obj;
		//3.��ЧԪ�ظ�����1
		size++;
		//4.����ʹ����ͬ����������wait״̬���߳�
		this.notifyAll();
	}
	public synchronized Object take(){
		//1.�ж������Ƿ�Ϊ��,����ȴ�
		while(size==0)
	    try{this.wait();}catch(Exception e){e.printStackTrace();}
		//2.ȡ����
		Object temp=array[0];
		//3.�ƶ�Ԫ��
		System.arraycopy(array, 1, array, 0, size-1);
		//4.��ЧԪ�ظ�����һ
		size--;
		array[size]=null;
		//5.������Ҫ�߱���ͬ���Ҵ�������״̬���߳�
		this.notifyAll();
		//6.����ȡ��������
		return temp;
	}
	public int size(){
		return size;
	}
}
/**�������߳�*/
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
/**������*/
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
		//1.��ʼ����������
		TeduBlockingQueue tQ=
		new TeduBlockingQueue(2);
		//2.���������߶���
		Producer pro=new Producer(tQ);
		pro.start();
		//3.���������߶���
		Consumer con=new Consumer(tQ);
		con.start();
		
	}
}




