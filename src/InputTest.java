import static org.junit.Assert.*;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputTest {
    
	String filename = "CalculatorLogic.java";
	
	private boolean noDefaultConstructor(){
	boolean noDefault = true;
	try {
		BufferedReader in = new BufferedReader(new FileReader(".java"));
		String line = in.readLine();
		while (line != null) {
			if (line.contains("public class Input{")) {
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

	@Test
	public void test_input1() {
		Input inputTest = newInput();
		String expected = "2";
		String actual = inputTest.squareRoot("4");
		
		assertEquals("Created new Input(): Testing for ___ result", expected, actual);
	}
	
	@Test
	void testFinalize() {
		fail("Not yet implemented");
	}

}
