//Isaac Workman
//30005845

import java.util.ArrayList;
public class TaskTree {
	//List of available tasks. Note that this must be in the form of Strings, as TaskNodes are reserved for tree construction.
	//Note that tasks and TaskNodes are separate and not interchangeable.
	private Deck deck;
	private TaskNode bestNode; //The best pairing of machines > tasks that has been found. Only need to store reference to deepest node of the root.
	private int lowestPenalty = Integer.MAX_VALUE;
	private TaskNode root = new TaskNode();
	private ArrayList<Character> usedTasks = new ArrayList<Character>();
	boolean penaltySet = false;
	private TaskNode topNode;
	private Input ourInput;
	//TODO: Add implementation to constructor method to receive massaged input data. (Aka the "kingdex")
	public TaskTree(Deck deck, Input ourInput) {
		this.deck = deck;
		this.ourInput = ourInput;
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
		if (currentNode.getDepth() == 0) {
			topNode = currentNode;
		}
			int currentPenalty = calculatePenalty(currentNode);
			// halt on branches exceeding the current best penalty score.
			// updates bestNode if node is a leaf and has a lower penalty score
			if ((currentPenalty < this.lowestPenalty) && (currentNode.getDepth() == 7)) {
				if (!currentNode.getInvalidNeighbours().contains(topNode.getName())) {
					this.lowestPenalty = currentPenalty;
					this.bestNode = currentNode;
					usedTasks.remove((Character)currentNode.getName());
					penaltySet = true;
					return;
				}
			}
	
			//Adds the current node's name to the list of used tasks. Do not repeat tasks.
			if (currentNode.getDepth() != -1) {
				usedTasks.add(currentNode.getTask().getName());
			}
			//Retrieves an ArrayList of eligible tasks for the next machine. Assumes it is sorted
			if (currentNode.getDepth() == 7) {return;}
			@SuppressWarnings("unchecked")
			ArrayList<Task> toCheck	 = (ArrayList<Task>) this.deck.sortedDeck.get(currentNode.getDepth()+1).sortedList.clone();
		
			//Removes invalid tasks, such as bad neighbours or tasks that have already been assigned.
			@SuppressWarnings("unchecked")
			ArrayList<Task> keptTasks = (ArrayList<Task>) toCheck.clone();
			
			for (Task aTask: toCheck) {
				if (currentNode.getInvalidNeighbours().contains(aTask.getName())){
					keptTasks.remove(aTask);
				}
				if(usedTasks.contains(aTask.getName())){
					keptTasks.remove(aTask);
				}

			}
			//Creates new children based off of the keptTasks ArrayList
			currentNode.setChildren(keptTasks, this.ourInput);
			if(currentNode.getChildren().isEmpty()) {
				usedTasks.remove((Character)currentNode.getName());
				return;
			}
			//checks for a better (lower) penalty score.
			//Recurses on each potential child.
			if (!currentNode.getChildren().isEmpty()) {
				for (TaskNode child : currentNode.getChildren()) {
					findPairs(child);
				}
			}

			if (currentNode.getDepth() != -1) {
				usedTasks.remove((Character) currentNode.getTask().getName());
			}
		}

	//Calculate the total penalty value from currentNode to the root TaskNode
	public int calculatePenalty(TaskNode currentNode) {
		TaskNode bottomNode = currentNode;
		int penalty = 0;
		int depth = currentNode.getDepth();
		if (depth == 7) {
		    penalty += bottomNode.getTooNearPenalty(topNode.getName());
		}
		for (int i = 0; i <= depth; i++) {
			TaskNode parent = currentNode.getParent();
			penalty += currentNode.getTask().getPenalty();
			System.out.println("hi");
			if (i > 0) {
			    char pName = parent.getTask().getName();
	            System.out.println("no");
			}
			
			if (i > 0) {
			    penalty += parent.getTask().getTooNearPenalty(currentNode.getName());
			}
			
			currentNode = currentNode.getParent();
		}
		return penalty;
	}
}
