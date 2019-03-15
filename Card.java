/**
 * This class creates each card
 * @author Keven Disen
 * ID: 111433335
 * Recitation: 06
 * Prgramming Homework #3: Stacks
 *
 */
public class Card {
	
	public static final String VALUES[] = {" ","A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    public static final char SUITS[]    = {' ', '\u2666', '\u2663','\u2665', '\u2660'};
	private int suit = 0;
	private int value = 0;
	private boolean isFaceUp = false;
	/**
	 * This is a default constructor method
	 */
	public Card() {}
	
	/**
	 * This method holds all of the information of a card
	 * @param suit
	 * @param value
	 * @param isFaceUp
	 */
	public Card(int suit, int value, boolean isFaceUp) {
		this.suit = suit;
		this.value = value;
		this.isFaceUp = isFaceUp;
	}
	
	/**
	 * These methods are setters and getter for suit
	 * @param s
	 */
	public void setSuit(int s) {
		this.suit = s;
	}
	
	public int getSuit() {
		return suit;
	}
	
/** 	 
 * 
 * These methods are setters and getter for value
 * @param v
 */
	public void setValue(int v) {
		this.value = v;
	}
	
	public int getValue() {
		return value;
	}
	
	/**
	 * These methods are setters and getter for a faceUp Card
	 * @param faceUp
	 */
	public void setFaceUp(boolean faceUp) {
		this.isFaceUp = faceUp;
	}
	
	public boolean isFaceUp() {
		return isFaceUp;
	}
	
	/**
	 * This method is to determine which cards are red and black
	 * @return
	 */
	public boolean isRed() {
		if(value%2 ==0) 
			return false;
		else
			return true;
	}
	
	/**
	 * This method prints out the card, the value and the suit
	 * If it's empty it will return [XX]
	 */
	public String toString() {
		if(isFaceUp)
			return "[" + VALUES[value]+SUITS[suit] + "]";  // print a card
		
		return "[XX]";
	}
}
