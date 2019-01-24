//Isaac Workman
//30005845

import java.util.ArrayList;
public class TaskTree {
	//List of available tasks. Note that this must be in the form of Strings, as TaskNodes are reserved for tree construction.
	//Note that tasks and TaskNodes are seperate and not interchangeable.
	private ArrayList<String> tasks; 
	private TaskNode bestNode; //The best pairing of machines > tasks that has been found. Only need to store reference to deepest node of the root.
	private int lowestPenalty; 
	private TaskNode root = new TaskNode("root", 0);
	
	
	//TODO: Add implementation to constructor method to receive massaged input data. (Aka the "kingdex")
	public TaskTree(ArrayList<String> tasks, ArrayList<String> machines) {
		this.tasks = tasks;
	}
	
	
	public TaskNode getBestNode() {return this.bestNode;}
	public int getPenalty() {return this.lowestPenalty;}
	
	//Returns an ArrayList containing the current best order found.
	public ArrayList<String> getBestOrder(){
		ArrayList<String> result = new ArrayList<String>();
		TaskNode currentNode = this.getBestNode();
		
		while (currentNode.getParent() != null) {
			result.add(0, currentNode.getName());
			currentNode = currentNode.getParent();
		}
		return result;
	}
	public void findPairs() {
		//TODO: Iterate down to branches, recording the best found path.
		//TODO: Halt on branches exceeding the current best penalty score.
	}
}
