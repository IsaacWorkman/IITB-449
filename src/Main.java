import java.io.IOException;
import java.util.ArrayList;

//Main class
public class Main {

	public static void main(String[] args) {
		
		try{
			System.out.println("Getting input!");
			Input ourInput = new Input(args);
			System.out.println("Processing deck!");
			Deck ourDeck = new Deck(ourInput, args[1]);
			System.out.println("Creating tree!");
			TaskTree myTree = new TaskTree(ourDeck, ourInput.tooNearPen);
			System.out.println("lowest penalty: " + myTree.getPenalty());
		}
		catch(IOException e) {
			System.out.println("Something went wrong. Unsure what it is. Error:");
			System.out.println(e);
		}
		//Construct deck based off of calculations performed by Input static class.
		//Assumed that args[1] contains the output file.
	}

}

