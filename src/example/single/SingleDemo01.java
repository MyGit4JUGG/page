package example.single;
/**
 * ����ģʽ:��֤���ϵͳ�д����ʵ��ֻ��һ��,
 * ���ڴ�����ģʽ��һ��.
 * Ӧ�ó���:ȫ����Դ�������(������Դ��Ժ�ʱ)
 * 1)�ַ�����,������
 * 2)��������,...
 * ����ģʽʵ��?
 * 1)��������(��ʱ��Ҫ��ʱ����)
 * 2)��������(�����ʱ����)
 * 3)....................
 */
/**�̰߳�ȫ����������
 * Ӧ�ó���:�����(ռ����Դ�Ƚ϶�)��ϡ����
 * */
class Singleton01{
	private Singleton01(){}
	private static Singleton01 instance=null;
	//�κ�һ��Ҫ����instanceʵ������������ͬ�������
	public static synchronized Singleton01 getInstance(){
		if(instance==null){
			instance=new Singleton01();
		}
		return instance;
	}
	public static void display(){}
}
/**�Ż������������*/
class Singleton02{
	private Singleton02(){}
	/**volatile��֤instance�����Ŀɼ���*/
	private static volatile Singleton02 instance=null;
	public static Singleton02 getInstance(){
		if(instance==null){
		    synchronized (Singleton02.class) {
			 if(instance==null){
				  instance=new Singleton02();
			  }
		    }
		}//ͨ��˫���ж���Ŀ���Ǽ��ٶ��̵߳�����
		return instance;
	}
}
/**��������(�����ʱ�������ʵ��):
 * Ӧ�ó���:С����(ռ����Դ��),Ƶ����
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
/**�Ż���Ķ�������(�ӳٶ���ĳ�ʼ��):��ʱ��Ҫ��ʱ����*/
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
/**ʹ��ö��ʵ�ֶ�������*/

enum Singleton05{//���췽��Ĭ�Ͼ���˽��
	instance;//���ʵ��(�����ʱ����)
	public void show(){}
	//......
}

/**ͨ��ThreadLocalʵ���߳��ڲ�����
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










