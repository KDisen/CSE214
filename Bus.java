/**
 * This class represents all of the functions the busses do
 * 
 * @author Keven Disen
 * ID: 111433335
 * Recitation: 06
 *
 */
public class Bus {
	//different busses
	public static int OUTBUS = 0;
	public static int INBUS = 1;
	
	public static int CAPACITY=0;		//capacity of busses

	
	private boolean firstRun = true;		//bus first route
	private int nextStop = 0;			//next bus stop
	private int toNextStop = 0;			//time to get to next stop
	private int timeToRest = 0;			//resting time
	private int route;					//type of route bus taking
	private int onBus;				//how many people on bus
	private int offset;				//index which separates the bus routes
	
	
	private String[] stops;
	private PassengerQueue[] passengerQueue;
	 
	/**
	 * This method is used to create the type of bus
	 * @param routeType
	 * which bus is being created, in or out
	 * @param routeStops
	 * which stops correlate with the type of bus it is
	 * @param restTime
	 * time for bus driver to rest
	 * @param offset2
	 * separation of 2 routes since they are both in one array
	 */
	public Bus(int routeType, String[] routeStops, int restTime, int offset2) {
		route = routeType;
		stops = routeStops;
		timeToRest = restTime;
		onBus = 0;
		offset = offset2;
		
		passengerQueue = new PassengerQueue[stops.length];
		//creates a passenger queue at eat bus stop
		for(int i = 0; i < stops.length; i++) {
			passengerQueue[i] = new PassengerQueue();
		}
	}
	/**
	 * This method represents if the bus is at rest, it should start at 30 and decrease
	 */
	public void decTimeToRest() {
		if(timeToRest > 0)
			timeToRest--;
	}
	/**
	 * This method shows the time to next stop
	 */
	public void decToNextStop() {
		if(toNextStop > 0)
			toNextStop--;
	}
	/**
	 * getter and setter for if its the first time the bus is doing its route
	 * it's used to rest
	 * @return
	 * true or false, if true then the bus is making its first route
	 * if false then it should take its rest before starting again
	 */
	public boolean isFirstRun() {
		return firstRun;
	}
	
	public void setFirstRun() {
		firstRun = false;
	}
	/**
	 * This method shows if the bus is at the next stop
	 * @return
	 * true if bus is at the next stop, then the time should be 0
	 * false if bus is on its way, should start at 20 and decrease until gets to stop
	 */
	public boolean isAtNextStop() {
		return toNextStop == 0;
	}
	/**
	 * This method shows of the bus is not active
	 * @return
	 * true if time to rest is greater than 0, if it is, it should be resting
	 * false if it's active and picking and dropping passengers off
	 */
	public boolean isInactive() {
		if(timeToRest > 0)
			return true;
		
		else return false;
	}
	/**
	 * This method shows if there's room on the bus
	 * @return
	 * true if people on bus is less than Capacity
	 * False if people on bus is more, passenger wouldn't be able to be picked up
	 */
	public boolean hasRoom() {
		return onBus < CAPACITY;
	}
	
	//load passengers
	/**
	 * This method shows if the passengers have been loaded on the bus
	 * @param p
	 * checks to see if passenger gets on bus
	 * @return
	 * true if there is space for passengers getting on bus
	 * false if theres no space
	 */
	public boolean load(Passenger p) {
		if(onBus + p.getGroupSize() > CAPACITY)
			return false;
			
		passengerQueue[p.getDestination() - offset].add(p);
		onBus += p.getGroupSize();
		return true;
	}
	
	// unload passengers
	/**
	 * This method shows how many passengers got off the bus
	 * @return
	 * returns the amount of passengers that got off the bus
	 */
	public int unload() {
		int numGroups = passengerQueue[nextStop].numGroups();
		onBus -= passengerQueue[nextStop].size();
		passengerQueue[nextStop].clear();
		return numGroups;
	}
	/**
	 * This method is the index for the next bus stop
	 * @return
	 * returns the index for the next bus stop, which is put in the stopNames array
	 * 	where all of the bus stops are
	 */
	public int getNextStop() {
		return nextStop + offset;
	}
	/**
	 * This method gets the index number where it separates the stops between both routes
	 * @return
	 * integer for index which separates 2 bus routes
	 * 
	 * Bus routes are combined together in the Simulator class, 
	 * if the outRoute is ready to move then the offset will be 4 because 
	 * thats the point where it separates both bus stop routes
	 */
	public int getOffset() {
		return offset;
	}
	/**
	 * This method increases the next stop of the bus
	 */
	public void incNextStop() {
		nextStop++;
		//if the stop is 8 then it has to go back to South P which is 0
		if(nextStop >= stops.length)
			nextStop = 0;
	}
	/**
	 * Getters and setters that shows the time to get to the next stop
	 * @return
	 * returns the amount of minutes to get to the next stop
	 */
	public int getToNextStop() {
		return toNextStop;
	}
	public void setToNextStop(int toNextStop) {
		this.toNextStop = toNextStop;
	}
	/**
	 * Getters and Setters for time to rest
	 * @return
	 * resting time
	 */
	public int getTimeToRest() {
		return timeToRest;
	}
	public void setTimeToRest(int timeToRest) {
		this.timeToRest = timeToRest;
	}
	
	/**
	 * Getters and Setters for the route of the bus
	 * @return
	 * route of the bus
	 */
	public int getRoute() {
		return route;
	}
	public void setRoute(int route) {
		this.route = route;
	}
	
	/**
	 * Getters and Setters for the amount of people on the bus
	 * @return
	 * number of people on bus
	 */
	public int getOnBus() {
		return onBus;
	}
	public void setOnBus(int onBus) {
		this.onBus = onBus;
	}
	
	/**
	 * This method gets the stop names
	 * @return
	 * stop names
	 */
	public String[] getStops() {
		return stops;
	}
	
	
}



	
		