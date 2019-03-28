/**
 * This class creates a passenger
 * @author Keven Disen
 * ID: 111433335
 * Recitation: 06
 *
 */
public class Passenger {
	private int groupSize;
	private int destination;
	

	private int arrivalTime;
	/**
	 * Default method
	 */
	public Passenger() {}
	
	/**
	 * Method holds the constructors for the variables a apassenger holds
	 * @param groupSize
	 * @param destination
	 * @param arrivalTime
	 */
	public Passenger(int groupSize, int destination, int arrivalTime) {
		this.groupSize = groupSize;
		this.destination = destination;
		this.arrivalTime = arrivalTime;
		
	}
	/**
	 * Getter and setter for group size
	 * @param size
	 */
	public void setGroupSize(int size) {
		this.groupSize = size;
	}
	public int getGroupSize() {
		return groupSize;
	}
	/**
	 * Getter and Setter for destination
	 * @param dest
	 */
	public void setDestination(int dest) {
		this.destination = dest;
	}
	public int getDestination() {
		return destination;
	}
	/**
	 * Setter and Getter for the arrival time
	 * @param arrive
	 */
	public void setArrivalTime(int arrive) {
		this.arrivalTime = arrive;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}

}
