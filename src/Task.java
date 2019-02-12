import java.util.ArrayList;


//Task object for use with Machine class
public class Task {
    
    int penaltyValue; //value for tree calculations
    private char name; //Task's name
    private int number;
    ArrayList<String[]> tooNearPartners = new ArrayList<String[]>();
    
    //Constructor 
    public Task(int num, char taskName, int penalty, ArrayList<String[]> tooNear) {
    	taskName = name;
        number = num;
        penaltyValue = penalty;
        for (int i = 0; i < tooNear.size(); i++) {
            if (tooNear.get(i)[0].charAt(0) == taskName) {
                String[] tooNearPair = {tooNear.get(i)[1], tooNear.get(i)[2]};
                tooNearPartners.add(tooNearPair);
            }
        }
    }
    
    public char getName() {return this.getName();}
    
    public int getPenalty() {return penaltyValue;}
    
    public int getNumber() {return number;}
    
    public int getTooNearPenalty(String tooNearName) {
        for (int i = 0; i < tooNearPartners.size(); i++) {
            if (tooNearPartners.get(i)[0] == tooNearName) {
                return Integer.parseInt(tooNearPartners.get(i)[1]);
            }
        }
        return 0;
    }
}
