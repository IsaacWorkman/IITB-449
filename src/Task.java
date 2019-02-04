import java.util.ArrayList;


//Task object for use with Machine class
public class Task {
    
    int penaltyValue; //value for tree calculations
    private char name; //Task's name
    private int number;
    
    //Constructor 
    public Task(int num, char taskName, int penalty) {
    	taskName = name;
        number = num;
        penaltyValue = penalty;
    }
    
    public char getName() {return this.getName();}
    
    public int getPenalty() {return penaltyValue;}
    
    public int getNumber() {return number;}
    
   
}
