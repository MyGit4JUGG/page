package example.io;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

class Rect implements Externalizable{
	private int width;
	private int height;
	public Rect() {}
	public Rect(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(width);
		//out.writeInt(height);
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		width=in.readInt();
		//in.readInt();
	}
	@Override
	public String toString() {
		return "Rect [width=" + width + ", height=" + height + "]";
	}
	
}

public class TestSerializable03 {
	public static void main(String[] args)throws Exception {
			Rect r1=new Rect(10,20);
			ObjectOutputStream out=
			new ObjectOutputStream(
			new FileOutputStream(new File("o1.txt")));
			out.writeObject(r1);
			out.close();
			System.out.println("write ok");
			
			ObjectInputStream in=
			new ObjectInputStream(new FileInputStream(new File("o1.txt")));
			Object r2=(Object)in.readObject();
			System.out.println(r2);
			in.close();
		}
}
