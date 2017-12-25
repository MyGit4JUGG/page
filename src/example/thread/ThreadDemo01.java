package example.thread;
/**
 * 1.何为进程?(正在运行的程序,例如JVM进程,eclipse进程)
 * 1)一个操作系统可以有多个进程
 * 2)每个进程都有自己独立的内存空间
 * 3)多个进程可以并发执行(严格来讲就是线程并发)
 * 
 * 2.何为线程?(进程中的一个顺序的执行流)
 * 1)一个进程中可以启动多个线程
 * 2)进程中的所有线程共享进程内存
 * 3)每个线程又有自己的线程内存.
 * 4)多个线程可以并发执行.
 * 
 * 3.Java线程对象类型及创建?
 * 1)所有线程类型:Thread类型
 * 2)线程对象的获取方式:
 * a)自己构建线程对象
 * b)通过线程池获取线程对象?
 * 
 * 4.Java线程对象的状态?
 * a)新建
 * b)就绪
 * c)运行
 * d)阻塞
 * e)死亡
 * 
 * 5.Java线程的同步?(在共享数据集上的互斥与协作)
 * 1)互斥(排队依次执行):同步代码块,同步方法
 * 2)协作(线程之间相互通讯,需要建立在互斥基础上)
 * a)wait()表示阻塞,调用此方法线程会阻塞,同时释放对象锁
 * b)notify(),notifyall()表示通知,通知具备相同锁
 * 对象的线程可以开始执行
 * 
 * 方法应用规则:
 * wait,notify,notifyall必须用在同步代码块或同步方法中,
 * wait,notify,notifyall必须由对象锁调用
 * 
 * 线程同步的目的:保证线程安全(例如保证数据一致性)
 * 
 */

public class ThreadDemo01 {
    static String content;
    static String LOCK="LOCK";
	public static void main(String[] args) {
		new Thread(){
			public void run() {//运行
				synchronized (LOCK) {//对象锁(同步锁)
					content="helloworld";
					LOCK.notifyAll();
				}
			};
		}.start();//就绪

		synchronized (LOCK) {
			while(content==null)
			try{LOCK.wait();}catch(Exception e){e.printStackTrace();}
			System.out.println(content.toUpperCase());
		}
	}
}














