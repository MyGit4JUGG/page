package example.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/***
 * ��ζ����л����ݽ���ϸ���ȿ���(
 * ����������ݽ������л�)?
 * 1)static ���εı���Ĭ�ϲ��ᱻ���л�.
 * 2)transient���εı������ᱻ���л�.
 * 3)ͨ��Externalizable�����Զ������л�.
 */
class Result implements Serializable{
	private static final long serialVersionUID = 1L;
	static int SUCCESS=1;
	transient int state;
	public Result() {}
	public Result(int state) {
		this.state=state;
		SUCCESS=2;
	}
	
    private void writeObject(ObjectOutputStream out)
    throws IOException{
    	out.defaultWriteObject();//���л�
    	out.writeInt(SUCCESS);
    }
    private void readObject(ObjectInputStream in)
    throws ClassNotFoundException,IOException{
    	in.defaultReadObject();//�����л�
    	SUCCESS=in.readInt();
    }
	
}
//RMI(Զ�̷�������)-->��RPC(����̵���)��һ��ʵ��
public class TestSerializable02 {
	public static void main(String[] args)throws Exception {
	/*	Result r1=new Result(1);
		ObjectOutputStream out=
		new ObjectOutputStream(
		new FileOutputStream(new File("o1.txt")));
		out.writeObject(r1);
		out.close();
		System.out.println("write ok");*/
		
		ObjectInputStream in=
		new ObjectInputStream(new FileInputStream(new File("o1.txt")));
		Result r2=(Result)in.readObject();
		System.out.println("success="+r2.SUCCESS);
		System.out.println("state="+r2.state);
		in.close();
	}
}






