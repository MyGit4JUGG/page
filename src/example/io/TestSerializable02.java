package example.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/***
 * 如何对序列化内容进行细粒度控制(
 * 按需求对内容进行序列化)?
 * 1)static 修饰的变量默认不会被序列化.
 * 2)transient修饰的变量不会被序列化.
 * 3)通过Externalizable对象自定义序列化.
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
    	out.defaultWriteObject();//序列化
    	out.writeInt(SUCCESS);
    }
    private void readObject(ObjectInputStream in)
    throws ClassNotFoundException,IOException{
    	in.defaultReadObject();//反序列化
    	SUCCESS=in.readInt();
    }
	
}
//RMI(远程方法调用)-->是RPC(跨进程调用)的一种实现
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






