package example.single;
/**
 * 单例模式:保证软件系统中此类的实例只有一份,
 * 属于创建型模式的一种.
 * 应用场景:全局资源共享对象(创建资源相对耗时)
 * 1)字符串池,整数池
 * 2)工厂对象,...
 * 单例模式实现?
 * 1)懒汉单例(何时需要何时创建)
 * 2)饿汉单例(类加载时创建)
 * 3)....................
 */
/**线程安全的懒汉单例
 * 应用场景:大对象(占用资源比较多)，稀少用
 * */
class Singleton01{
	private Singleton01(){}
	private static Singleton01 instance=null;
	//任何一个要想获得instance实例必须进入这个同步代码块
	public static synchronized Singleton01 getInstance(){
		if(instance==null){
			instance=new Singleton01();
		}
		return instance;
	}
	public static void display(){}
}
/**优化版的懒汉单例*/
class Singleton02{
	private Singleton02(){}
	/**volatile保证instance变量的可见性*/
	private static volatile Singleton02 instance=null;
	public static Singleton02 getInstance(){
		if(instance==null){
		    synchronized (Singleton02.class) {
			 if(instance==null){
				  instance=new Singleton02();
			  }
		    }
		}//通过双重判定的目的是减少多线程的阻塞
		return instance;
	}
}
/**饿汉单例(类加载时创建类的实例):
 * 应用场景:小对象(占用资源少),频繁用
 * */
class Singleton03{
	private Singleton03(){}
	//int a[]=new int[1024];
	private static Singleton03 instance=
		new Singleton03();
	public static Singleton03 getInstance() {
		return instance;
	}
	//public static void display(){}
	//public void show(){}
}
/**优化版的饿汉单例(延迟对象的初始化):何时需要何时创建*/
class Singleton04{
	private Singleton04(){}
	//int a[]=new int[1024];
	private static class LazyInner{
		public static Singleton04 instance=
				new Singleton04();
	}
	public static Singleton04 getInstance() {
		return LazyInner.instance;
	}
	//public static void display(){}
	//public void show(){} 
}
/**使用枚举实现饿汉单例*/

enum Singleton05{//构造方法默认就是私有
	instance;//类胡实例(类加载时构建)
	public void show(){}
	//......
}

/**通过ThreadLocal实现线程内部单例
 * .....
 * */
public class SingleDemo01 {
    public static void main(String[] args) {
    	for(int i=0;i<10;i++){
    		new Thread(){
    			public void run() {
    				System.out.println(
    				Singleton01.getInstance());
    			};
    		}.start();
    	}
    	Singleton05.instance.show();
	}
}










