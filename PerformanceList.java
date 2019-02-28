/**
 * This class holds the lists. It holds has the nodes created in the previous class together 
 * in a doubly linked list, meaning it can go back and forth
 * 
 * @author Keven Disen <br>
 * email: keven.disen@stonybrook.edu <br>
 * ID: 111433335 <br>
 * Recitation: 06
 *
 */
public class PerformanceList {
	private PerformanceNode head;
	private PerformanceNode tail;
	private PerformanceNode cursor; //current
	private int length = 0;
	/**
	 * This method is default and sets all of the data to null
	 */
	public PerformanceList() {
		head = null;
		tail = null;
		cursor = null;
	}
	/**
	 * This method adds the nodes to the ends and has its 3 cases:
	 * 		if there's nothing at head, add the first node
	 *		if there's only one node add another node to tail
	 * 		if there's more than 2 nodes add the node to tail
	 * 
	 * Length increases when a node is added
	 * @param newPerformance
	 * 		represents a new node
	 */
	public void addToEnd(PerformanceNode newPerformance) {
		PerformanceNode newNode = newPerformance;
		if(head==null) {		//if there's nothing at head, add first node
			newNode.setStartTime(0);
			newNode.setPrev(null);
			head = cursor = tail = newNode;
		//	newNode.setStartTime(0);
		}
		else if(tail == head) {		//if there's one node add another node
			cursor.setNext(newNode);
			newNode.setPrev(cursor);
			newNode.setNext(null);
			tail =cursor = newNode;
		}
		else if(length>=2) {		//if there's more than or equal 2 nodes add node in the middle
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = cursor = newNode;
		}
		length++;
	}
	/**
	 * This method adds a node after the current/cursor 
	 * Adds a node in between 2 nodes 
	 * @param newNode
	 * 		Represents the node being added in between
	 */
	public void addAfterCurrent(PerformanceNode newNode) {
		//in slides
		if(cursor == null) {
			newNode.setStartTime(0);
			head = tail = cursor = newNode;
		}
		else if(cursor.getNext()!= null){
			newNode.setNext(cursor.getNext());		//sets pointer to the next node
			newNode.setPrev(cursor);
			cursor.setNext(newNode);				//sets the current pointer to the added node
			newNode.getNext().setPrev(newNode);		// fixxx
			cursor = newNode;						//moves cursor to new node
			if(cursor.getNext() == null)
				tail = cursor;
		}
		else { 
			newNode.setNext(null);
			newNode.setPrev(cursor);
			cursor.setNext(newNode);
			cursor = newNode;
		}
		
		length++;
	}
	/**
	 * This method proves that a node has been removed and there's 3 cases:
	 * 		if cursor is at head remove head
	 * 		if cursor is at tail go to previous node and delete the tail
	 * 		if cursor is in between, connect both nodes before and after current node
	 * @return
	 * 		returns true if node is removed
	 */
	public boolean removeCurrentNode() {
		PerformanceNode tempCursor = head;
		boolean remove = false;
		if(cursor == head) {			//removes current node if it's at head
			if(cursor.getNext() != null) {
				tempCursor = head;
				head = cursor.getNext();
				head.setPrev(null);
				cursor = head;
				remove = true;
			}
			else 
				head = null;
			
		}
		if(cursor == tail) {					//removes current node if it's at tail
			cursor.getPrev().setNext(null);		//gets the previous node and sets it to null 
			tail = cursor = cursor.getPrev();	//moves tail and cursor to node before
			remove = true;
			
		}
	
		if(cursor.getNext()!= null && cursor.getPrev()!= null) {	//removes current node if it's in the middle
			cursor.getPrev().setNext(cursor.getNext()); 		//sets previous node to current next node
			cursor.getNext().setPrev(cursor.getPrev());			//sets after next node to previous current node
			cursor = cursor.getNext();		//moves cursor to after deleted node
			remove = true;
		}
		length--;
		return remove;
	}
/**
 * This method displays the node that the cursor is on
 */
	public void displayCurrentPerfomance() {
		if(cursor == null) throw new NullPointerException("There are no nodes in the list");
		else {
			System.out.println(String.format("%-28s %-30s %-10s %-10s","Performance Name","Lead Performer Name" 
					,"Participants","Duration","Start Time"));
			System.out.println("--------------------------------------------------------------------------------------------------------------");

			System.out.println(String.format("%-28s %-32s %-10s %-10s",cursor.getName(), cursor.getLead(),cursor.getParticipants() 
				,cursor.getDuration()));
		}
	}
	/**
	 * This method proves that the cursor advances forward to another node
	 * @return
	 * 		returns true if cursor moves forward
	 */
	public boolean moveCursorForward() {
		boolean moveForward = false;
		if(cursor.getNext() != null) {
			cursor = cursor.getNext();			//moves cursor up a node
			moveForward = true;
		}else
			moveForward = false;
		return moveForward;
	}
	/**
	 * This method proves that the cursor moves backwards to the previous node
	 * @return
	 * 		returns true if cursor moves backwards
	 */
	public boolean moveCursorBackward() {			//Not working******
		boolean moveBackward = false;
		if(cursor.getPrev() != null) {
			cursor = cursor.getPrev();				//moves cursor back a node
			moveBackward = true;
		}
		return moveBackward;
	}
	/**
	 * This method proves the position the cursor jumps to is a real node
	 * @param position
	 * 		user puts in the position they want the cursor to be on
	 * @return
	 * 		returns true if node it's jumping to is valid
	 */
	public boolean jumpToPosition(int position) {
		PerformanceNode tempCursor = head;
		int i = 1;
		if(position<=0) throw new IllegalArgumentException();
		while(i<position && tempCursor != null) {
			tempCursor = tempCursor.getNext();
			i++;
		}
		if(tempCursor != null) cursor = tempCursor;
		return (tempCursor != null);
	}
	/**
	 * This method is for the length of the list
	 * @return
	 * 		returns the amount of nodes in the list
	 */
	public int getLength() {
		return length;
	}
	/**
	 * This method increases the start time
	 * @param node
	 * each node has a different start time
	 * @return
	 * the duration + starting time of previous node
	 */
	public int getTime(PerformanceNode node) {
		int time = 0;
		if(node == head)
			node.setStartTime(0);
		else {
			time = node.getPrev().getStartTime() + node.getPrev().getDuration();
			node.setStartTime(time);
		}
		return time;
	}
	/**
	 * This method prints a * to show what the cursor is pointing to
	 * @param tempCursor
	 * @return
	 * returns the * with the specific node the cursor is pointing to
	 */
	public String displayCursor(PerformanceNode tempCursor) {
		String ptr = "";
		if(cursor == tempCursor) {
			ptr = "*";
		}
		return ptr;
	}
	/**
	 * This method neatly prints out the entire list with its headers
	 */
	public String toString() {
		System.out.println(String.format("%-3s %-4s %-26s %-30s %-15s %-10s %-10s","Current", "No.","Performance Name","Lead Performer Name" 
				,"Participants","Duration","Start Time"));
		System.out.println("--------------------------------------------------------------------------------------------------------------");

		PerformanceNode tempCursor = head;
		int i=1;
		for(tempCursor = head; tempCursor!=null;tempCursor = tempCursor.getNext()) {
			System.out.println(String.format("%-6s%-7d %-27s %-30s %-15d %-10d %-10d",displayCursor(tempCursor), i , tempCursor.getName() , tempCursor.getLead(), tempCursor.getParticipants() 
			, tempCursor.getDuration(), getTime(tempCursor)));
			System.out.println();
			i++;
		}
		String empty = "";
		return empty;
	}
}