//Isaac Workman
//30005845

import java.util.ArrayList;
import java.util.Arrays;
public class TaskTree {
	//List of available tasks. Note that this must be in the form of Strings, as TaskNodes are reserved for tree construction.
	//Note that tasks and TaskNodes are separate and not interchangeable.
	private Deck deck;
	private TaskNode bestNode; //The best pairing of machines > tasks that has been found. Only need to store reference to deepest node of the root.
	private int lowestPenalty = Integer.MAX_VALUE;
	private TaskNode root = new TaskNode();
	private ArrayList<Character> usedTasks = new ArrayList<Character>();
	private ArrayList<String[]> tooNearPenalty;
	//boolean penaltySet = false;
	private TaskNode topNode;
	private Input ourInput;
	//TODO: Add implementation to constructor method to receive massaged input data. (Aka the "kingdex")
	public TaskTree(Deck deck, Input ourInput) {
		this.deck = deck;
		this.tooNearPenalty = ourInput.tooNearPen;
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
			System.out.println("topNode: " + topNode.getName());
		}
		System.out.println("Checking node of depth: " + currentNode.getDepth());
		//while (currentNode.getDepth() < 8 && currentNode.getDepth() > 0) {
			int currentPenalty = calculatePenalty(currentNode);
			//System.out.println("Jess says put this here: current penalty = " + currentPenalty);
			// halt on branches exceeding the current best penalty score.
			
//			if (!penaltySet && (currentNode.getDepth() == 7)) {
//				this.lowestPenalty = currentPenalty;
//				penaltySet = true;
//				this.bestNode = currentNode;
//			}
//			if (currentPenalty > this.lowestPenalty && penaltySet) {
//				return;
//			}
//			// updates bestNode if node is a leaf and has a lower penalty score
			if ((currentPenalty < this.lowestPenalty) && (currentNode.getDepth() == 7)) {
				if (!currentNode.getInvalidNeighbours().contains(topNode.getName())) {
					this.lowestPenalty = currentPenalty;
					this.bestNode = currentNode;
					usedTasks.remove((Character)currentNode.getName());
					return;
				}
			}
		//}
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
				//System.out.println(currentNode.getInvalidNeighbours());
				System.out.println(aTask.getName());
				if (currentNode.getInvalidNeighbours().contains(aTask.getName())){
					keptTasks.remove(aTask);
				}
				if(usedTasks.contains(aTask.getName())){
					keptTasks.remove(aTask);
				}
//				if((currentNode.getDepth() == 7) && topNode.getInvalidNeighbours().contains(aTask.getName())) {
//					keptTasks.remove(aTask);
//				}
			}
			//Creates new children based off of the keptTasks ArrayList
			currentNode.setChildren(keptTasks, this.ourInput);
//			if (currentNode.getDepth() == 6) {
//				
//				ArrayList<TaskNode> toRemove = new ArrayList<TaskNode>();
//				for (TaskNode aNode : currentNode.getChildren()) {
//					System.out.println("hi" + aNode.getName());
//					if (aNode.getInvalidNeighbours().contains(topNode.getName())) {
//						toRemove.add(aNode);
//						
//					}
//				}
//				currentNode.getChildren().removeAll(toRemove);
//			}
			
			System.out.println("Number of children = " + currentNode.getChildren().size());
			if(currentNode.getChildren().isEmpty()) {
				//System.out.println("no children");
				usedTasks.remove((Character)currentNode.getName());
				return;
			}
			//checks for a better (lower) penalty score.
			//Recurses on each potential child.
			if (!currentNode.getChildren().isEmpty()) {
				for (TaskNode child : currentNode.getChildren()) {
					System.out.println("chile for find pair: " + child.getName());
					System.out.println();
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
		for (int i = 0; i < depth; i++) {
			TaskNode parent = currentNode.getParent();
			penalty += currentNode.getTask().getPenalty();
			penalty += parent.getTooNearPenalty(currentNode.getName());
			
			currentNode = currentNode.getParent();
		}
		System.out.println("current penalty: "+ penalty);
//		if(!penaltySet && (depth == 7)) {
//			penaltySet = true;
//			this.lowestPenalty = penalty;
//		}
		return penalty;
	}
}
