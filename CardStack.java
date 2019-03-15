/**
 * This class makes the Stack for every stack that is needed for the main program
 * 
 * @author Keven Disen
 * ID: 111433335
 * Recitation: 06
 * Programming Homework #3: Stacks
 */
import java.util.Stack;

public class CardStack extends Stack<Card> {
	public static final char STOCK = 's';
	public static final char WASTE = 'w';
	public static final char FOUNDATION = 'f';
	public static final char TABLEAU = 't';
	public static final char DECK = 'n';
	private final static int CAPACITY = 52;
	
	private char type;
	private Stack<Card> data = new Stack<Card>();
	
	/**
	 * This method determins the type of stack it is, because there are 5 different stacks
	 * @param type
	 */
	public CardStack(char type) {
		this.type = type;
		data = new Stack<Card>();
	}
	
	/**
	 * This method returns the data inside of the card
	 * @return
	 */
	public Stack<Card> getData() {
		return data;
	}
	
	/**
	 * This method puts the card inside of a stack
	 * 
	 *@return
	 *returns the Card that was pushed inside the stack
	 *@exception
	 *shows whether the stack is full or not
	 */
	public Card push(Card newCard) {
		if(data.size() == CAPACITY)
			try {
				throw new FullStackException();
			} catch (FullStackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return data.push(newCard);
	}
	
	/**
	 * This method takes out the final card of the stack
	 * 
	 * @return it returns the card that was taken out
	 * 
	 * @exception 
	 * shows whether the stack is empty or not
	 */
	public Card pop(){
		if(isEmpty())
			try {
				throw new EmptyStackException();
			} catch (EmptyStackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		Card topCard = data.pop();
		return topCard;
	}
	
	/**
	 * This method returns the top card of the stack
	 */
	public Card peek() {
		return data.peek();
	}
	/**
	 * This method returns the size of the stack
	 */
	public int size() {
		return data.size();
	}
	
	/**
	 * This method returns true or false, if the stack is empty or not
	 */
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	/**
	 * This toString method prints all of the stacks on whether it is empty or not,
	 * and how it should look in the main program
	 * uses the private individual strings below 
	 */
	public String toString() {
		if(type == STOCK)
			return stockStackToString();
		else if(type == WASTE)
			return wasteStackToString();
		else if(type == FOUNDATION)
			return foundationStackToString();
		else if(type == TABLEAU)
			return tableauStackToString();
		else
			return deckStackToString();
	}
	/**
	 * This method shows the stock string
	 * @return
	 * returns the size of the stock stack
	 */
	private String stockStackToString() {
		if(data.isEmpty())
			return "[  ] 0";
		
		return data.peek().toString() + " " + data.size();
	}

	/**
	 * This method shows the waste string
	 * @return
	 * returns the waste stack and how it should look on the board game
	 */
	private String wasteStackToString() {
		if(data.isEmpty())
			return "W1 [  ]";
		
		return "W1 " + data.peek().toString();
	}

	/**
	 * This method shows the foundation string
	 * @return
	 * returns the value and suit of a card in this stack
	 */
	private String foundationStackToString() {
		if(data.isEmpty())
			return "[  ]";
		
		return data.peek().toString();
	}
	
	/**
	 * This method shows the tableau string
	 * @return
	 * returns how the tableau stack should look if empty or with data inside of it
	 */
	private String tableauStackToString() {
		if(data.isEmpty())
			return "[ ]";
		
		Stack<Card> temp = new Stack<Card>();
		while(!data.isEmpty()) {
			temp.push(data.pop());
		}
		
		String result = "";
		while(!temp.isEmpty()) {
			result += temp.peek().toString();
			data.push(temp.pop());
		}
		
		return result;
	}
	
	/**
	 * This method shows the deck string
	 * @return
	 * returns null because the deck is never shown only the stacks above
	 */
	private String deckStackToString() {
		return null;
	}
}
