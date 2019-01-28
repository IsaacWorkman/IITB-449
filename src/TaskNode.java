//Isaac Workman
//30005845

import java.util.ArrayList;
public class TaskNode {
	//
	private Task template;
	private TaskNode parent = null;
	private ArrayList<Character> invalidNeighbours = new ArrayList<Character>();
	private ArrayList<TaskNode> children = new ArrayList<TaskNode>();
	private String machine; //Machine this task is assigned to
	private int depth = 0;
	
	//Constructor to be used for non-root TaskNodes
	public TaskNode(Task template, int depth, TaskNode parent) {
		this.template = template;
		this.parent = parent;
		this.depth = depth;
	}
	//Constructor for root TaskNode. Has no parent reference and a depth of -1.
	public TaskNode() {
		this.depth = -1;
	}
	//Adds a single task to the list of invalid neighbours. Invalid neighbours can not be the parent of child of this node.
	public void AddInvalidNeighbours(Character badNeighbour) {
		this.invalidNeighbours.add(badNeighbour);
	}
	//Adds several tasks to the list of invalid neighbours.
	public void AddInvalidNeighbours(ArrayList<Character> badNeighbours) {
		this.invalidNeighbours.addAll(badNeighbours);
	}
	
	@SuppressWarnings("unchecked")
	//Get functions.
	public ArrayList<Character> getInvalidNeighbours(){return (ArrayList<Character>) invalidNeighbours.clone();} //Gets a list of invalid neighbours.
	public TaskNode getParent() {return this.parent;}
	public String getMachine() {return this.machine;}
	public int getDepth() {return this.depth;}
	public ArrayList<TaskNode> getChildren(){return this.children;}
	public Task getTask() {return this.template;}
	public void setChildren(ArrayList<Task> newChildren) {
		for(Task aTask: newChildren) {
			this.children.add(new TaskNode(aTask, this.depth + 1, this));
		}
	}
}
