import java.util.ArrayList;
import java.io.*;


/*
 * The strange object found to contain cryptic, esoteric
 * and downright dastardly data.
 */
public class Deck {
    
    //the list of the machines (that each hold their taskList and sortedList)
    public ArrayList<Machine> sortedDeck;
    public int errorOccured = -1;
    
    /* constructor creates the sortedDeck and populates
     * it (size 8) with machines; 
     * removes any forbidden machine task pairs and assigns forced pairs
     * if an error occurs sets errorOccured true which will need to
     * be checked after the constructor returns. 
     */
    public Deck(Input ourInput, String outputFile) {
        sortedDeck = new ArrayList<Machine>(8);
        int machineIndex;
        ArrayList<Character> assigned = new ArrayList<Character>(ourInput.forcedPartial.size());
        //creates deck containing the 8 machines
        for (int i = 1; i <= 8; i++) {
            sortedDeck.add(new Machine(i, ourInput.machinePen, ourInput.tooNearPen));
        }
        /*
         * iterates through forced assignments array and attempts to 
         * force the assignment. if an error occurred executed will return false and 
         * the constructor will set errorExecuted to true then return early.
         */
        for (int i = 0; i < ourInput.forcedPartial.size(); i++) {
            char[] pair = ourInput.forcedPartial.get(i);
            machineIndex = Character.getNumericValue(pair[0]) -1;
            if (assigned.contains(pair[1])) {
                errorOccured = 1;
                outputError(errorOccured, outputFile);
                return;
            }
            boolean executed = sortedDeck.get(machineIndex).forcedAssign(pair[1]);
            if (!executed) {
                errorOccured = 1;
                outputError(errorOccured, outputFile);
                return;
            }
            assigned.add(pair[i]);
        }
        /*
         * iterates through forbidden machines array and attempts to 
         * remove any forbidden machines. if an error occurred executed will return false and 
         * the constructor will set errorExecuted to true then return early.
         */
        for (int i = 0; i < ourInput.forbiddenMachine.size(); i++) {
            char[] pair = ourInput.forbiddenMachine.get(i);
            machineIndex = Character.getNumericValue(pair[0]) -1;
            boolean executed = sortedDeck.get(machineIndex).forbiddenTask(pair[1]);
            if (!executed) {
                errorOccured = 2;
                outputError(errorOccured, outputFile);
                return;
            }
        }
        //for each machine, sorts the task list in order of penalties
        for (Machine mach: sortedDeck) {
            mach.sortedQueue();
        }
        //print stuff
        
        for (int i = 0; i < 8; i++) {
        	System.out.println("machine index: " + i);
        	for (int j = 0; j < sortedDeck.get(i).sortedList.size(); j++) {
        		System.out.println("task" + sortedDeck.get(i).sortedList.get(j).getName());
        	}
        }
        
        
    }
    
    private void outputError(int errorCode, String outputFile) {
    	FileWriter writer = null;
		try {
			writer = new FileWriter(outputFile, true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
    	if (errorCode == 1) {
    		try {
				writer.write("partial assignment error");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	} else if (errorCode == 2) {
    		try {
				writer.write("forbidden error");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}
