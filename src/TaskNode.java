//Isaac Workman
//30005845

import java.util.ArrayList;
public class TaskNode {
	//
	private String taskName;
	private TaskNode parent = null;
	private ArrayList<String> invalidNeighbours = new ArrayList<String>();
	private ArrayList<TaskNode> children = new ArrayList<TaskNode>();
	private String machine; //Machine this task is assigned to
	private int depth = 0;
	
	//Constructor to be used for non-root TaskNodes
	public TaskNode(String taskName, int depth, TaskNode parent) {
		this.taskName = taskName;
		this.parent = parent;
		this.depth = depth;
	}
	//Constructor for root TaskNode. Has no parent reference.
	public TaskNode(String newName, int depth) {
		this.taskName = newName;
		this.depth = depth;
	}
	//Adds a single task to the list of invalid neighbours. Invalid neighbours can not be the parent of child of this node.
	public void AddInvalidNeighbours(String badNeighbour) {
		this.invalidNeighbours.add(badNeighbour);
	}
	//Adds several tasks to the list of invalid neighbours.
	public void AddInvalidNeighbours(ArrayList<String> badNeighbours) {
		this.invalidNeighbours.addAll(badNeighbours);
	}
	
	@SuppressWarnings("unchecked")
	//Get functions.
	public ArrayList<String> getInvalidNeighbours(){return (ArrayList<String>) invalidNeighbours.clone();} //Gets a list of invalid neighbours.
	public TaskNode getParent() {return this.parent;}
	public String getMachine() {return this.machine;}
	public int getDepth() {return this.depth;}
	public String getName() {return this.taskName;}
}
