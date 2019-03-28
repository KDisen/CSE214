import java.util.*;
/**
 * This class sets up the simulation and starts it
 * This class also contains the main method
 * @author Keven Disen
 * ID: 111433335
 * Recitation: 06
 *
 */
public class Simulator {
	private int numInBusses;
	private int numOutBusses;
	private int minGroupSize;
	private int maxGroupSize;
	private int capacity;
	private double arrivalProb;
	private int groupsServed;
	private int totalTimeWaited;
	
	private static final int NUM_BUS_STOPS = 8;
	
	private static String inRoute[]  = {"South P", "West", "SAC", "Chapin"};
	private static String outRoute[] = {"South P", "PathMart", "Walmart", "Target"};
	
	//joint stopNames
	private static String stopNames[] = {"South P", "West", "SAC", "Chapin", 
			"South P", "PathMart", "Walmart", "Target"};
	
	/**
	 * This method holds the constructors for the private variables listed above
	 * @param numInBusses2 
	 * inRoute bus
	 * @param numOutBusses2
	 * outRoute bus
	 * @param minGroupSize2
	 * minimum group size
	 * @param maxGroupSize2
	 * maximum group size
	 * @param capacity2
	 * capacity of the bus
	 * @param arrivalProb2
	 * time bus arrives
	 */
	public Simulator(int numInBusses2, int numOutBusses2, int minGroupSize2, int maxGroupSize2, int capacity2,
			double arrivalProb2) {
		this.numInBusses  = numInBusses2;
		this.numOutBusses = numOutBusses2;
		this.minGroupSize = minGroupSize2;
		this.maxGroupSize = maxGroupSize2;
		this.capacity = capacity2;
		this.arrivalProb = arrivalProb2;
	}
	
	/**
	 * This method outputs a random integer between the given min and max
	 * @param min
	 * minimum number
	 * @param max
	 * maximum number
	 * @return
	 * returns random in between both numbers
	 */
	public static int randInt(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max - min) + min;
	}
	
	/**
	 * This method sets up the passengers, which are their 
	 * Group size, destination, and arrival time and adds them to the queue of the bus stop
	 * @param waitingLines
	 * queue where passengers are waiting at the bus stop for the bus
	 * @param time
	 * the time they arrive to the bus stop
	 */
	private void determineArrivals(PassengerQueue[] stopLines, int time) {
		//Boolean source
		BooleanSource source = new BooleanSource(this.arrivalProb);
		// Add passengers to the bus stop queues 
		for(int i=0; i<NUM_BUS_STOPS; i++) {
			//if passenger doesn't arrive 
			if(!source.occurs())
				continue;
			
			//if passenger does arrive to bus stop
				//randomizes the destination for every group
			int destination = i;
			while(destination == i) {
				if(i < inRoute.length)
					destination = (int)(Math.random()* inRoute.length - 1);
				else
					destination = (int)(Math.random()* outRoute.length - 1) + inRoute.length;
		    }
			//randomizes the group size
		    int passengersInGroup = Simulator.randInt(this.minGroupSize, this.maxGroupSize);
		    
		    // sets random group size, random destination, arrival time
		    Passenger passengerIn = new Passenger(passengersInGroup, destination, time);
		    
		    //put passengers on line at bus stop,
	    	stopLines[i].enqueue(passengerIn);
	    	//printing when groups arrive to bus stop and states where they are heading
	    	System.out.printf("A group of %d passengers arrived at %s heading to %s\n",
	    			passengersInGroup, stopNames[i], stopNames[destination]);
		}
	}
	
	/**
	 * This method does the bus simulations
	 * @param duration
	 * duration is how long you want the duration to last 
	 * it tells you every minute of the simulation
	 */
	public void simulate(int duration){
		Bus.CAPACITY = capacity;
		//creates the different busses, with specific bus route
		//inroute bus setup
												//0 here is offset, because inRoute bus stops start at 0
		Bus[] inBusses = separateBusSetup(inRoute, numInBusses, Bus.INBUS, 0, 0);
		//outroute bus setup
												//inRoute.length here is offset, because outRoute bus stops start at 4
		Bus[] outBusses = separateBusSetup(outRoute, numOutBusses, Bus.OUTBUS, 30*numInBusses, inRoute.length);
		
		//creates stop queue at every stop
		PassengerQueue[] stopQueues =  new PassengerQueue[NUM_BUS_STOPS];
		for(int i = 0; i < NUM_BUS_STOPS; i++) {
			stopQueues[i] = new PassengerQueue();
		}
		//starts the simulation at minute 1
		for(int time = 1; time <= duration; time++) {
			System.out.println("Minute " + time + "\n");
			//shows when passenger arrives to bus stop
			determineArrivals(stopQueues, time);	
			//
			busFunctions(inBusses, time, stopQueues);
			busFunctions(outBusses, time, stopQueues);
			System.out.println();
		}
		
		//After simulation
			//shows how many groups were served and the average wait time for all groups
		System.out.println("Groups served: " + groupsServed);
		System.out.println("Average Wait Time: " + (totalTimeWaited / groupsServed) + " Minutes");
	}
	
	/**
	 * This method creates the different busses for the simulation
	 * @param route
	 * busses take 2 different routes, the inRoute and the outRoute
	 * @param numBusses
	 * how many busses the user puts in the simulation
	 * @param busRoute
	 * type of bus, 0 is in, 1 is out
	 * @param timeToRest
	 * calculates when the bus has to rest
	 * @param offset
	 * this variable represents the index for the other route, since I put them together as stopNames
	 * @return
	 * returns both busses and their routes
	 */
	private Bus[] separateBusSetup(String[] route, int numBusses, int busRoute, int timeToRest, int offset) {
		Bus[] busses = new Bus[numBusses];
		for(int i = 0; i < busses.length; i++, timeToRest += 30) {
			busses[i] = new Bus(busRoute, route, timeToRest, offset);
		}
		
		return busses;
	}
	
	/**
	 * This method does the function of the busses
	 * it mounts and unmounts the passengers
	 * shows the amount of time to get to next stop
	 * @param busses
	 * shows the type of Bus
	 * @param time
	 * time it takes to get to the other stop
	 * @param waitingLines
	 * uses the lines at each stop to mount and unmount passengers
	 */
	private void busFunctions(Bus[] busses, int time, PassengerQueue[] waitingLines) {
		//for each bus
		for(int i = 0; i < busses.length; i++) {
			Bus b = busses[i];
			//time to rest
			b.decTimeToRest();
			//if time to rest > 0 then the bus is inactive
			if(b.isInactive()) {
				continue;
			}
			//amount of time to get to next stop
			b.decToNextStop();
			//if the bus is at stop, unload the passengers first, then load passengers
			if(b.isAtNextStop()) {
				//unload passengers
				unmountPassengers(b);
				//stop queue
				PassengerQueue stopQueue = waitingLines[b.getNextStop()];
				
				//if bus goes back to South P then it should take its rest
				if(b.getNextStop() == 0 && !b.isFirstRun())
					b.setTimeToRest(30);
				//load the passengers that are at the stop queue
				mountPassengers(b, stopQueue, time);
				b.incNextStop();
				//20 mins to get to next stop
				b.setToNextStop(20);
				b.setFirstRun();
				
			}
			//Prints where the bus is every minute
			System.out.printf("Bus arrives at %s in %d minutes \n", stopNames[b.getNextStop()], b.getToNextStop());
		}
	}
	
	/**
	 * Unloads passengers at their specific destination
	 * @param bus
	 * uses whatever bus is unloading 
	 */
	private void unmountPassengers(Bus bus) {
		int n = bus.unload();
		groupsServed += n;
		System.out.println("Bus drops " + n + " group(s) at " + stopNames[bus.getNextStop()]);
	}
	
	/**
	 * Loads passengers at the specific stop they are at
	 * @param bus
	 * the bus that is going to pick them up
	 * @param line
	 * the stop queue where the passengers are waiting
	 * @param time
	 * uses time for how long waited to get on the bus
	 */
	private void mountPassengers(Bus bus, PassengerQueue line, int time) {
		LinkedList<Integer> indexToRemove = new LinkedList<Integer>();
		int passengersBoard = 0;
		for(int i = 0; i < line.numGroups() && bus.hasRoom(); i++) {
			Passenger p = line.get(i);
			passengersBoard +=p.getGroupSize();
			
			if(bus.load(p)) {
				totalTimeWaited += time - p.getArrivalTime();
				indexToRemove.add(i);
			}
		}
		System.out.printf("Bus arrives at %s. Picks up %d passengers\n",
				 stopNames[bus.getNextStop()], bus.getOnBus());
		
		
	}
	
	/**
	 * This is the main method which takes in the users inputs
	 * Prints out the Simulation minute by minute
	 * 
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String userSelection = "";

		do {
			try {
			System.out.println("Enter Q to Quit Simulation \nEnter C to perform a simulation");
			System.out.print("Input: ");
			userSelection = input.nextLine().toUpperCase();
			
			//if entered C then will perform a simulation
			if(userSelection.equalsIgnoreCase("C")) {
				//user inputs parameters
				System.out.println("Enter the number of In Route busses: ");
				int numInBusses = input.nextInt();
				
				System.out.println("Enter the number of Out Route busses: ");
				int numOutBusses = input.nextInt();
				
				System.out.println("Enter the minimum group size of passengers: ");
				int minGroupSize = input.nextInt();
				System.out.println("Enter the maximum group size of passengers: ");
				int maxGroupSize = input.nextInt();
				//throws exception if numbers are incorrect
				
					if(minGroupSize > maxGroupSize) throw new IllegalArgumentException();
				
				
				System.out.println("Enter the capacity of a bus: ");
				int capacity = input.nextInt();
				
				
					if(capacity< minGroupSize) throw new IllegalArgumentException();
				
				System.out.println("Enter the arrival probability: ");
				double arrivalProb = input.nextDouble();
				
				System.out.println("Enter the duration of the simulation: ");
				int duration = input.nextInt();
				
				//uses the variables to do simulation
				Simulator s = new Simulator(
						numInBusses, 
						numOutBusses,
						minGroupSize,
						maxGroupSize,
						capacity,
						arrivalProb
						);
				//runs simulation
				s.simulate(duration);
				System.out.println();
				System.out.println();

			}	
			}catch(IllegalArgumentException e) {
				System.out.println();
				System.out.println("Wrong input \ntry again");
				input.nextLine();
			}
		}while(!userSelection.equalsIgnoreCase("Q"));
		System.out.println("Terminating Program.........");
	}

			  
}
	
	


