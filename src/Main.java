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
		try{
			Input.inputFile(args);
		}
		catch(Exception e) {
			System.out.println("Something went wrong on input. Unsure what it is. Error:");
			System.out.println(e);
		}
		//Construct deck based off of calculations performed by Input static class.
		//Assumed that args[1] contains the output file.
		Deck deck = new Deck(Input.forbiddenMachine, Input.forcedPartial, Input.machinePen, args[1]);
	}

}
