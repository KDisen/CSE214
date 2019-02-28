/**
 * This class creates each specific node 
 * @author Keven Disen <br>
 * email: keven.disen@stonybrook.edu <br>
 * ID: 111433335 <br>
 * Recitation: 06
 *
 */
public class PerformanceNode {
	private String performanceName;
	private String leadPerformer;
	private int totalParticipants;
	private int duration = 0;
	private int startTime =0;
	private PerformanceNode previous;
	private PerformanceNode next;
/**
 * This method represents default
 */
	public PerformanceNode() {}
/**
 * This method is for the user to create a specific node to be in the list
 * @param name	
 * 		name of the performance
 * @param lead
 * 		name of the lead performer
 * @param participants
 * 		amount of people participating
 * @param length
 * 		the duration of the performance
 * @param start
 * 		the time the performance starts
 */
	public PerformanceNode(String name, String lead, int participants, int length, int start) {
		this.performanceName = name;
		this.leadPerformer = lead;
		this.totalParticipants = participants;
		this.duration = length;
		this.startTime = start;
	}
	/**
	 * These methods are getters and setters for the name of the performace
	 * @return
	 * 		returns the name
	 */
	public String getName() {
		return performanceName;
	}
	public void setName(String performanceName) {
		this.performanceName = performanceName;
	}
	/**
	 * These methods are getters and setters for the name of the lead performer
	 * @return
	 * 		returns name of lead
	 */
	public String getLead() {
		return leadPerformer;
	}
	public void setLead(String leadPerformer) {
		this.leadPerformer = leadPerformer;
	}
	/**
	 * These methods are getters and setters for the amount of people at the performance
	 * @return
	 * 		number of people at performance
	 */
	public int getParticipants() {
		if(totalParticipants<0) throw new IllegalArgumentException("Input can't be negative");
		else 
			return totalParticipants;
	}
	public void setParticipants(int totalParticipants) {
		this.totalParticipants = totalParticipants;
	}
	/**
	 * These methods are getters and setters for the duration of the performance in minutes
	 * @return
	 * 		duration in minutes
	 */
	public int getDuration() {
		if(duration<0)throw new IllegalArgumentException("Input can't be negative");
		else
			return duration;
	}
	public void setDuration(int dur) {
		this.duration = dur;
	}
	/**
	 * These methods are getters and setters for the start time of each performance
	 * @return
	 * 		the start time in minutes
	 */
	public int getStartTime() {
		return  startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	/**
	 * These methods are setters and getters for the node that is going to be next
	 * @param node
	 */
	public void setNext(PerformanceNode node) {
		this.next = node;
	}
	public PerformanceNode getNext() {
		return next;
	}
	/**
	 * These methods are setters and getters for the node that is going to be previous
	 * @param node
	 */
	public void setPrev(PerformanceNode node) {
		this.previous = node;
	}
	public PerformanceNode getPrev() {
		return previous;
	}
}
