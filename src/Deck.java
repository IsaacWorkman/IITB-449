import java.util.ArrayList;

/*The strange object found to contain cryptic, esoteric
 * and downright dastardly data.
 */

public class Deck {
    
    //the list of the machines (that hold the lists of tasks)
    public ArrayList<Machine> sortedDeck;
    public int errorOccured = -1;
    
    /*constructor creates the sortedDeck and populates
     * it (size 8) with machines; 
     *  removes any forbidden machine task pairs and assigns forced pairs
     *  if an error occurs sets errorOccured true which will need to
     *  be checked after the constructor returns. 
     */
    public Deck(ArrayList<char[]> forbidden, ArrayList<char[]> forced, char[][] penalties) {
        sortedDeck = new ArrayList<Machine>(8);
        int machineIndex;
        ArrayList<Character> assigned = new ArrayList<Character>(forced.size());
        //creates deck containing the 8 machines
        for (int i = 1; i <= 8; i++) {
            sortedDeck.add(new Machine(i, penalties));
        }
        /*
         * iterates through forced assignments array and attempts to 
         * force the assignment. if an error occurred executed will return false and 
         * the constructor will set errorExecuted to true then return early.
         */
        for (int i = 0; i < forced.size(); i++) {
            char[] pair = forced.get(i);
            machineIndex = Character.getNumericValue(pair[0]) -1;
            if (assigned.contains(pair[1])) {
                errorOccured = 1;
                return;
            }
            boolean executed = sortedDeck.get(pair[machineIndex]).forcedAssign(pair[1]);
            if (!executed) {
                errorOccured = 1;
                return;
            }
            assigned.add(pair[i]);
        }
        /*
         * iterates through forbidden machines array and attempts to 
         * remove any forbidden machines. if an error occurred executed will return false and 
         * the constructor will set errorExecuted to true then return early.
         */
        for (int i = 0; i < forbidden.size(); i++) {
            char[] pair = forbidden.get(i);
            machineIndex = Character.getNumericValue(pair[0]) -1;
            boolean executed = sortedDeck.get(machineIndex).forbiddenTask(Character.getNumericValue(pair[1]));
            if (!executed) {
                errorOccured = 2;
                return;
            }
        }
        //for each machine, sorts the task list in order of penalties
        for (Machine mach: sortedDeck) {
            mach.sortedQueue();
        }
    }
}
