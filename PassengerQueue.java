import java.util.*;
/**
 * This class holds the Passengers in a queue, whether at the bus stop or on the bus
 * @author Keven Disen
 * ID: 111433335
 * Recitation: 06
 *
 */
public class PassengerQueue extends LinkedList<Passenger>{
	/**
	 * Default Method
	 */
	public PassengerQueue() {}
	
	/**
	 * This method adds the passenger to the queue
	 * @param p
	 */
	public void enqueue(Passenger p) { // new group joins line
		super.add(p);
	}
	
	/**
	 * This method takes out the passenger from the stop queue 
 	*	eventually adds them on the bus
	 * @return
	 * the Passenger it removed from the queue
	 * @throws EmptyQueueException
	 * if empty then throw exception
	 */
	public Passenger dequeue() throws EmptyQueueException {	//remove group on line and check if allowed on bus
		if(isEmpty()) {
			throw new EmptyQueueException();
		}
		return (Passenger) remove();
	}
	
	/**
	 * This method holds the size of the queue
	 * @return
	 * returns size of queue
	 */
	public int size() {
		int size = 0;
		for(Passenger p : this)
			size += p.getGroupSize();
			
		return size;
	}
	
	/**
	 * This method holds the number of passengers in the group
	 * @return
	 * number of passengers in the groups
	 */
	public int numGroups() {
		return super.size();
	}
}
