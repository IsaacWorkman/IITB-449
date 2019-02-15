import java.util.ArrayList;


//Task object for use with Machine class
public class Task {
    
    int penaltyValue; //value for tree calculations
    private char name; //Task's name
    private int number;
    ArrayList<String[]> tooNearPartners = new ArrayList<String[]>();
    
    //Constructor 
    public Task(int num, char taskName, int penalty, ArrayList<String[]> tooNear) {
    	this.name = taskName;
        number = num;
        penaltyValue = penalty;
        for (int i = 0; i < tooNear.size(); i++) {
            if (tooNear.get(i)[0].charAt(0) == taskName) {

                String[] tooNearPair = {tooNear.get(i)[1], tooNear.get(i)[2]};
                tooNearPartners.add(tooNearPair);

            }
        }
    }
    
    public char getName() {return this.name;}
    
    public int getPenalty() {return penaltyValue;}
    
    public int getNumber() {return number;}
    
    public int getTooNearPenalty(char tooNearName) {
        System.out.println("taskName: " + name + " " + tooNearPartners.size());
        for (int i = 0; i < tooNearPartners.size(); i++) {
            System.out.println("task: " + name + " partner: " + tooNearPartners.get(i)[0] + " " + tooNearPartners.get(i)[1]);
            if (tooNearPartners.get(i)[0].charAt(0) == tooNearName) {
                return Integer.parseInt(tooNearPartners.get(i)[1]);
            }
        }
        return 0;
    }
}
