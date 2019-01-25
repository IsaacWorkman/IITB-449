import java.util.ArrayList;
import java.util.PriorityQueue;

//It's not a task, dummy.
public class Machine {
    int name;//durr
    
    //tbd
    public PriorityQueue<Task> taskOrder;
    //an unordered list of possible tasks for the machine
    public Task[] taskList;
    /*
     * constructor assigns name and makes the tasklist and 
     * populates it (size 8)
     */
    public Machine(int number, int[][] penalties) {
        
        name = number;
        for (int i = 0; i < 8; i++) {
            taskList[i] = new Task(i+1, penalties[name-1][i]);
        }
    }
    
    //tbd will remove all but one task from tasklist
    public void forcedAssign(int taskKeep) {
    }
    //tbd removes only one task from tasklist
    public void forbiddenTask(int toRemove) {
        this.taskList[toRemove] = -1;
    }

}
