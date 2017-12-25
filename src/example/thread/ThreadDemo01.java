package example.thread;
/**
 * 1.��Ϊ����?(�������еĳ���,����JVM����,eclipse����)
 * 1)һ������ϵͳ�����ж������
 * 2)ÿ�����̶����Լ��������ڴ�ռ�
 * 3)������̿��Բ���ִ��(�ϸ����������̲߳���)
 * 
 * 2.��Ϊ�߳�?(�����е�һ��˳���ִ����)
 * 1)һ�������п�����������߳�
 * 2)�����е������̹߳�������ڴ�
 * 3)ÿ���߳������Լ����߳��ڴ�.
 * 4)����߳̿��Բ���ִ��.
 * 
 * 3.Java�̶߳������ͼ�����?
 * 1)�����߳�����:Thread����
 * 2)�̶߳���Ļ�ȡ��ʽ:
 * a)�Լ������̶߳���
 * b)ͨ���̳߳ػ�ȡ�̶߳���?
 * 
 * 4.Java�̶߳����״̬?
 * a)�½�
 * b)����
 * c)����
 * d)����
 * e)����
 * 
 * 5.Java�̵߳�ͬ��?(�ڹ������ݼ��ϵĻ�����Э��)
 * 1)����(�Ŷ�����ִ��):ͬ�������,ͬ������
 * 2)Э��(�߳�֮���໥ͨѶ,��Ҫ�����ڻ��������)
 * a)wait()��ʾ����,���ô˷����̻߳�����,ͬʱ�ͷŶ�����
 * b)notify(),notifyall()��ʾ֪ͨ,֪ͨ�߱���ͬ��
 * ������߳̿��Կ�ʼִ��
 * 
 * ����Ӧ�ù���:
 * wait,notify,notifyall��������ͬ��������ͬ��������,
 * wait,notify,notifyall�����ɶ���������
 * 
 * �߳�ͬ����Ŀ��:��֤�̰߳�ȫ(���籣֤����һ����)
 * 
 */

public class ThreadDemo01 {
    static String content;
    static String LOCK="LOCK";
	public static void main(String[] args) {
		new Thread(){
			public void run() {//����
				synchronized (LOCK) {//������(ͬ����)
					content="helloworld";
					LOCK.notifyAll();
				}
			};
		}.start();//����

		synchronized (LOCK) {
			while(content==null)
			try{LOCK.wait();}catch(Exception e){e.printStackTrace();}
			System.out.println(content.toUpperCase());
		}
	}
}














