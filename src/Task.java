import java.util.ArrayList;


//It's a task, dummy.
public class Task {
    
    int penaltyValue; //value for tree calculations
    private char name; //Task's name
    private int number;
    
    /*Constructor gives name based on switch and assigns
     * penalty value
     */
    public Task(int num, char name, int penalty) {
        this.name = name;
        number = num;
        penaltyValue = penalty;
    }
    public char getName() {return this.getName();}
    
    public int getPenalty() {return penaltyValue;}
    
    public int getNumber() {return number;}
    
}
