/**
 * This class shows the probability of passengers arriving at the bus stop
 * @author Keven Disen
 * ID: 111433335
 * Recitation: 06
 *
 */
public class BooleanSource {
	private double probability;
	public BooleanSource(double p) throws IllegalArgumentException {
		if (p < 0.0 || p > 1.0)
			throw new IllegalArgumentException();
		
	    probability = p;
	  }
	/**
	 * This method converts the boolean source into a boolean
	 * @return
	 * whether the random number is less than the probability set abovve
	 */
	  public boolean occurs() {
	    return (Math.random() < probability);
	}
}
