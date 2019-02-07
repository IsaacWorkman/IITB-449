import java.io.IOException;
import java.util.ArrayList;

//Main class
public class Main {

	public static void main(String[] args) {
		//Old arrays
		/*ArrayList<char[]> forcedPartial = null;
		ArrayList<char[]> forbiddenMachine = null;
		ArrayList<char[]> tooNear = null;
		char[][] machinePen = new char[8][8];
		ArrayList<char[]> tooNearPen = null;*/
		
		Input ourInput;
		try{
			ourInput = new Input(args);
			Deck deck = new Deck(ourInput, args[1]);
		}
		catch(IOException e) {
			System.out.println("Something went wrong in \"Input\". Unsure what it is. Error:");
			System.out.println(e);
		}
		//Construct deck based off of calculations performed by Input static class.
		//Assumed that args[1] contains the output file.
	}

}

