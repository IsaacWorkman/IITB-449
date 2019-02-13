import java.io.IOException;
import java.util.ArrayList;

//Main class
public class Main {

	public static void main(String[] args) {
		
		try {
			Input ourInput = new Input(args);
			Deck ourDeck = new Deck(ourInput, args[1]);
			TaskTree myTree = new TaskTree(ourDeck, ourInput);
			TaskNode currentNode = myTree.getBestNode();
			while (currentNode.getDepth()!= -1) {
				currentNode = currentNode.getParent();
			}
		}
		catch(IOException e) {
			System.out.println("Something went wrong. Unsure what it is. Error:");
			System.out.println(e);
		}
		//Construct deck based off of calculations performed by Input static class.
		//Assumed that args[1] contains the output file.
	}

}

