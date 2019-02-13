import java.io.FileWriter;
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
			TaskTree myTree = new TaskTree(ourDeck, ourInput);
			System.out.println("lowest penalty: " + myTree.getPenalty());
			System.out.println("Sequence: ");
			TaskNode currentNode = myTree.getBestNode();
			FileWriter writer = null;
			try {
				writer = new FileWriter(args[1], true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			writer.write("Solution ");
			while (currentNode.getDepth()!= -1) {
				writer.write(currentNode.getTask().getName() + " ");
				currentNode = currentNode.getParent();
			}
			writer.write("Quality:" + myTree.getPenalty());
			writer.close();
			
		}
		catch(IOException e) {
			System.out.println("Something went wrong. Unsure what it is. Error:");
			System.out.println(e);
		}
		//Construct deck based off of calculations performed by Input static class.
		//Assumed that args[1] contains the output file.
		//"Solution" *task*1 *task*2 *task*3 *task*4 *task*5 *task*6 *task*7 *task*8"; Quality:" *qual*
			
		
	}

}

