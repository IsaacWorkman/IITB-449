//Isaac Workman
//30005845

import java.util.ArrayList;
import java.util.Arrays;
public class TaskTree {
	//List of available tasks. Note that this must be in the form of Strings, as TaskNodes are reserved for tree construction.
	//Note that tasks and TaskNodes are separate and not interchangeable.
	private Deck deck; 
	private TaskNode bestNode; //The best pairing of machines > tasks that has been found. Only need to store reference to deepest node of the root.
	private int lowestPenalty; 
	private TaskNode root = new TaskNode();
	private ArrayList<Character> usedTasks = new ArrayList<Character>();
	
	//TODO: Add implementation to constructor method to receive massaged input data. (Aka the "kingdex")
	public TaskTree(Deck deck) {
		this.deck = deck;
		findPairs(root);
	}
	
	
	public TaskNode getBestNode() {return this.bestNode;}
	public int getPenalty() {return this.lowestPenalty;}
	
	//Returns an ArrayList containing the current best order found.
	public ArrayList<Character> getBestOrder(){
		ArrayList<Character> result = new ArrayList<Character>();
		TaskNode currentNode = this.getBestNode();
		
		while (currentNode.getParent() != null) {
			result.add(0, currentNode.getTask().getName());
			currentNode = currentNode.getParent();
		}
		return result;
	}
	
	//Finds the best set of machine:task pairs.
	//TODO: Halt on branches exceeding the current best penalty score.
	public void findPairs(TaskNode currentNode) {
		while (currentNode.getDepth() < 8) {
			//Checks to see if it's at a leaf. If so, perform penalty calculations.
			if (currentNode.getDepth() == 7) {
				int currentPenalty = calculatePenalty(currentNode);
				if (currentPenalty < this.lowestPenalty) {
					this.lowestPenalty = currentPenalty;
					this.bestNode = currentNode;
					break;
				}
			}
			//Adds the current node's name to the list of used tasks. Do not repeat tasks.
			if (currentNode.getDepth() != -1) {
				usedTasks.add(currentNode.getTask().getName());
			}
			//Retrieves a set of eligible tasks for the next machine.
			Task[] toCheckAsArray = this.deck.sortedDeck.get(currentNode.getDepth()+1).taskList; //Gets the array of possible tasks for the current machine. Assumes it is sorted.
			ArrayList<Task> toCheck = new ArrayList<Task>(Arrays.asList(toCheckAsArray));		//Turns it into an arrayList
			//Removes invalid tasks, such as bad neighbours or tasks that have alread been assigned.
			for (Task aTask: toCheck) {
				if (currentNode.getInvalidNeighbours().contains(aTask.getName())){
					toCheck.remove(aTask);
				}
				if(usedTasks.contains(aTask.getName())){
					toCheck.remove(aTask);
				}
			}
			//Creates new children based off of the toCheck ArrayList
			currentNode.setChildren(toCheck);
			//checks for a better (lower) penalty score.
			//Recurses on each potential child.
			for (TaskNode child : currentNode.getChildren()) {
				findPairs(child);
			}
			usedTasks.remove(currentNode.getTask().getName());
		}
	}
	
	public int calculatePenalty(TaskNode currentNode) {
		//TODO: calculate penalty score.
		return 100;
	}
}
