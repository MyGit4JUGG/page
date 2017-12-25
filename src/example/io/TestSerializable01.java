package example.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Point implements Serializable{
	private static final long serialVersionUID = 4649207750717481901L;
	private int x;
	private int y;
    public void setX(int x) {
		this.x = x;
	}
    /**��Point��Ķ����ڱ����л�ʱ�ײ���Զ����ô˷���*/
    private void writeObject(ObjectOutputStream out)
    throws IOException{
    	x=x>>2;
    	out.defaultWriteObject();//���л�
    }
    private void readObject(ObjectInputStream in)
    throws ClassNotFoundException,IOException{
    	in.defaultReadObject();//�����л�
    	x=x<<2;
    }
	@Override
	public String toString() {
		return "Point [x=" + x + "]";
	}	
}
public class TestSerializable01 {
    public static void main(String[] args)throws Exception {
		ObjectOutputStream out=
		new ObjectOutputStream(
		new FileOutputStream(
		new File("f1.txt")));
		Point p1=new Point();
		p1.setX(16);
		out.writeObject(p1);//���л�
		out.close();
		
		ObjectInputStream in=
				new ObjectInputStream(
		new FileInputStream(new File("f1.txt")));
		
		Object o1=in.readObject();
		in.close();
		System.out.println(o1);
		
		
	}
}






