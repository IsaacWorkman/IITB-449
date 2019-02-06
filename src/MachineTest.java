import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class MachineTest {
    
	String filename = "Machine.java";
	
	private boolean noDefaultConstructor(){
	boolean noDefault = true;
	try {
		BufferedReader in = new BufferedReader(new FileReader("Machine.java"));
		String line = in.readLine();
		while (line != null) {
			if (line.contains("public class Machine{")) {
				noDefault = false;
			}
			line = in.readLine();
		}
		in.close();
	} catch (FileNotFoundException e) {
		noDefault = false;
	} catch (IOException e) {
		noDefault =  false;
	}
	return noDefault;
	}
	@Test
	public void test_noDefaultConstructor(){
		assertTrue("Checked for the existence of a default constructor, there shouldn't be one.",  noDefaultConstructor());		
	}	

// taskName++ what does it mean?
	
	@Test
	public void testsortedQueue(){
		Machine sortedQueueTest = new Machine();
		String expected = "idk";
		String actual = sortedQueueTest.sortedQueue("idk");
		
		assertEquals("Created new Machine(): Testing for sortedQueue(1) result", expected, actual);

	}
	
	}
	

}