package example.debug;

import java.util.ArrayList;

public class TestMemory {

	public static void main(String[] args) {
		//OutOfMemoryError
		new ArrayList<>(Integer.MAX_VALUE);
	}
}
