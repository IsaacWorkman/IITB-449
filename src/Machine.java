import java.util.ArrayList;
import java.util.PriorityQueue;

//It's not a task, dummy.
public class Machine {
    int name;//durr
    
    //ordered list of possible tasks for given machine
    public ArrayList<Task> sortedList;
    private Task[] taskList = new Task[8];
    /*
     * constructor assigns name and makes the tasklist and 
     * populates it (size 8)
     */
    public Machine(int number, char[][] penalties) {
        
        name = number;
        char taskName = 'A';
        for (int i = 0; i < 8; i++, taskName++) {
            taskList[i] = new Task(i, taskName, (int)penalties[name-1][i]);
        }
    }
    
    /*
     * takes the task to keep and removes all other tasks.
     * if there is already a forced partial assignment for the machine
     * then returns executed false, else returns true if the forced assignment
     * can occur.
     *
     */
    public boolean forcedAssign(char taskKeep) {
        boolean executed = false;
        int index = Character.getNumericValue(taskKeep);
        if (taskList.length > 1) {
            Task toKeep = taskList[index];
            sortedList.add(toKeep);
            executed = true;
            Task[] temp = {toKeep};
            taskList = temp;
        }
        else {
            if (taskList[0].getName() == taskKeep) {
                executed = true;
            }
        }
        return executed;
    }
    /*
     * attempts to remove specified forbidden task from task list
     * 
     */
    public boolean forbiddenTask(int toRemove) {
        boolean executed = false;
        if (taskList.length == 1) {
            if (taskList[0].getNumber() != toRemove){
                executed = true;
            }
        }
       
        else {
            taskList[toRemove] = null;
            executed = true;
        }
        return executed;
    }
    
    /*
     * sorts array in terms of lowest penalty first
     */
    public void sortedQueue() {
        if (sortedList.size() != 1) {
            PriorityQueue<Task> temp = new PriorityQueue<Task>(taskList.length, (a,b)->a.getPenalty() - b.getPenalty());
            for (int i = 0; i < taskList.length; i++) {
                if (taskList[i] != null) {
                    temp.add(taskList[i]);
                }
            }
            for (int i = 0; i < temp.size(); i++) {
                sortedList.add(temp.remove());
            }
        }
        
    }

}
