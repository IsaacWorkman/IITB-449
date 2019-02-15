import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Main class
public class Main {

	public static void main(String[] args) {
		
		try {
		    FileWriter writer = null;
		    try {
                writer = new FileWriter(args[1], true);
            } catch (IOException e) {
                e.printStackTrace();
            }
			Input ourInput = new Input(args);
			Deck ourDeck = new Deck(ourInput, args[1]);
			TaskTree myTree = new TaskTree(ourDeck, ourInput);
			
			
			if (!myTree.penaltySet) {
			    writer.write("No valid solution possible!");
			    writer.close();
                System.exit(0);
			}
			ArrayList<Character> result = new ArrayList<Character>();
			result = myTree.getBestOrder();
			writer.write("Solution");
			for (int i = 0; i < result.size(); i++) {
			    writer.write(" " + result.get(i));
			}
			writer.write("; Quality: " + myTree.getPenalty());
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

