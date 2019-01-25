import java.util.ArrayList;


//It's a task, dummy.
public class Task {
    
    int penaltyValue; //value for tree calculations
    char name; //Task's name
    
    /*Constructor gives name based on switch and assigns
     * penalty value
     */
    public Task(int numberName, int penalty) {
        switch (numberName) {
            case 1: name = 'A'; break;
            case 2: name = 'B'; break;
            case 3: name = 'C'; break;
            case 4: name = 'D'; break;
            case 5: name = 'E'; break;
            case 6: name = 'F'; break;
            case 7: name = 'G'; break;
            case 8: name = 'H'; break;
        }
        penaltyValue = penalty;
    }
}
