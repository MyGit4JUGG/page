package example.debug;
/*Debugģʽ����(F11)
 * f5 (���뷽���ڲ�)
 * f6 (����ִ��)
 * f7 (�ӷ������˳�)
 * f8 (���н���)
*/
public class TestDebug {
	static int get(){
		int a=10;
	    try{
	     return a;//temp=a
	    }finally{
	     a++;
	     //return a;
	    }
	}
	public static void main(String[] args) {
		System.out.println("main");
		int result=get();
		System.out.println(result);//11
	}
}
