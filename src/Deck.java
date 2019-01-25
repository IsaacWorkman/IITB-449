import java.util.ArrayList;

/*The strange object found to contain cryptic, esoteric
 * and downright dastardly data.
 */
public class Deck {
    
    //the list of the machines (that hold the lists of tasks)
    public ArrayList<Machine> sortedDeck;
    
    /*constructor creates the sortedDeck and populates
     * it (size 8) with machines; massages dat data
     *  
     */
    public Deck(int[][] pairs, int[][] penalties) {
        sortedDeck = new ArrayList<Machine>();
        for (int i = 1; i <=8; i++) {
            sortedDeck.add(new Machine(i, penalties));
        }
        /*iterates through the 'pairs' array for
         * the forbidden and forced assignments and does it  
         */
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j< 8; j++) {
                if (pairs[i][j] == 1) {
                    sortedDeck.get(i).forbiddenTask(j);
                }
                else if (pairs[i][j] == 2){
                    sortedDeck.get(i).forcedAssign(j);
                }
            }
        }
        //for each machine, sorts the task list in order of penalties
        for (Machine mach: sortedDeck) {
            mach.sortedQueue();
        }
    }
}
