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
	private ArrayList<char[]> tooNearPenalty;

	//TODO: Add implementation to constructor method to receive massaged input data. (Aka the "kingdex")
	public TaskTree(Deck deck, ArrayList<char[]> tooNearPenalty) {
		this.deck = deck;
		this.tooNearPenalty = tooNearPenalty;
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
	public void findPairs(TaskNode currentNode) {
		System.out.println("Checking node of depth: " + currentNode.getDepth());
		while (currentNode.getDepth() < 8 && currentNode.getDepth() > 0) {
			int currentPenalty = calculatePenalty(currentNode);
			// halt on branches exceeding the current best penalty score.
			if (currentPenalty > this.lowestPenalty) {
				break;
			}
			// updates bestNode if node is a leaf and has a lower penalty score
			if (currentPenalty < this.lowestPenalty && currentNode.getDepth() == 7) {
				this.lowestPenalty = currentPenalty;
				this.bestNode = currentNode;
				break;
			}
		}
			//Adds the current node's name to the list of used tasks. Do not repeat tasks.
			if (currentNode.getDepth() != -1) {
				usedTasks.add(currentNode.getTask().getName());
			}
			//Retrieves an ArrayList of eligible tasks for the next machine. Assumes it is sorted.
			@SuppressWarnings("unchecked")
			ArrayList<Task> toCheck	 = (ArrayList<Task>) this.deck.sortedDeck.get(currentNode.getDepth()+1).sortedList.clone();
			//Removes invalid tasks, such as bad neighbours or tasks that have already been assigned.
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

	//Calculate the total penalty value from currentNode to the root TaskNode
	public int calculatePenalty(TaskNode currentNode) {
		int penalty = 0;
		int depth = currentNode.getDepth();
		for (int i = 0; i < depth; i++) {
			penalty += currentNode.getTask().getPenalty();
			penalty += currentNode.getTooNearPenalty(currentNode.getParent().getName());
			currentNode = currentNode.getParent();
		}
		System.out.println("current penalty: "+ penalty);
		return penalty;
	}
}
