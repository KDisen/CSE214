import java.util.*;
/**
 * This is the main method of the program
 * This method holds the printing of the game board and holds the functions the player uses
 * to play the game
 * @author Keven Disen
 * ID: 111433335
 * Recitation: 06
 * Programming Homework #3: Stacks
 *
 */
public class Stackotaire {
	//Have to have no Exception errorssssss
	public static CardStack[] tableauStacks = new CardStack[7];
	public static CardStack[] foundationStacks = new CardStack[4];
	
	public static CardStack stock = new CardStack(CardStack.STOCK);
	public static CardStack waste = new CardStack(CardStack.WASTE);
	
	public static ArrayList<Card> main = new ArrayList<Card>(CardStack.DECK);
	public static CardStack distinct = new CardStack(CardStack.DECK);
	
	public static ArrayList<Card> copyCards = new ArrayList<Card>();
	public static CardStack recycle = new CardStack(CardStack.STOCK);

	public static void main(String[] args) throws FullStackException, EmptyStackException {
		initializeGame();
		displayGameBoard();
		
		Scanner input = new Scanner(System.in);
		String userSelection = "";
		int userInt = 0;
		do {
			System.out.println();
			System.out.println("To move a card to/from tableau it's 0-6 and to move to foundation it's 0-3");
			System.out.print("\nEnter a command: ");
			 userSelection = input.nextLine();

			 
			 
			 if(userSelection.equalsIgnoreCase("draw")) {
				 //recycles the stock pile if it's empty
				 if(stock.isEmpty()) {
					 while(!recycle.isEmpty()) {
						 Card a = recycle.pop();
						 a.setFaceUp(false);
						 stock.push(a);
					 }
					}
				 else {
					 Card c = stock.pop();
					 recycle.push(c);
					 c.setFaceUp(true);
					 waste.push(c);
					 
				 }
			 }
			 
			 //how to move waste to one of the tableau piles
			 if(userSelection.indexOf("move") != -1){
				 if(userSelection.length() < 10) {
					 System.out.println("Wrong input");
					 continue;
				 }
				String firstArgu = userSelection.substring(5).trim();
				String firstNum = userSelection.substring(6);
				String secondArgu = userSelection.substring(8).trim();
				//index of second argument
				String secondNum = userSelection.substring(9);
				
				//moves from waste stack
				if(firstArgu.charAt(0) == 'w' || firstArgu.charAt(0) == 'W' ) {
					
					//***** if foundation stack is not empty pop the last card and add the card with specific restrictions
					//moves from waste stack to foundation stack
					if(secondArgu.charAt(0) == 'f') {
						Card c = waste.pop();
						c.setFaceUp(true);
						for(int i=0;i<foundationStacks.length;i++) {
							if(i == Integer.parseInt(secondNum)) {
								//work on card equaling to A 
								if(foundationStacks[i].isEmpty() && c.getValue() == Arrays.asList(Card.VALUES).indexOf("A")) {
									foundationStacks[i].push(c);
									break;
								}
								else if(!foundationStacks[i].isEmpty()){
									if(foundationStacks[i].peek().SUITS[foundationStacks[i].peek().getSuit()] == c.SUITS[c.getSuit()]) {
										if(c.getValue() - foundationStacks[i].peek().getValue() == 1) {
											foundationStacks[i].pop();
											foundationStacks[i].push(c);
										}
									}
						
									else {
										System.out.println("This card can't be put in there right now!! \n\n");
										waste.push(c);
								}
								}
						}
								}
							}
				//moves from waste stack to tableau stack
				else if(secondArgu.charAt(0) == 't') {
					Card c = waste.pop();
					c.setFaceUp(true);
					for(int i=0; i<tableauStacks.length;i++) {
						if(i== Integer.parseInt(secondNum)) {
							if(tableauStacks[i].isEmpty()) {
								if(Arrays.asList(Card.VALUES).indexOf("K") == 13)
									c.setFaceUp(true);
									tableauStacks[i].push(c);
							}
							else if(tableauStacks[i].peek().getValue() - c.getValue() ==1) {
								tableauStacks[i].push(c);
								waste.push(stock.pop()).setFaceUp(true);
								break;
							}
							else {
								System.out.println("This card can't go there!!! \n \n");
								waste.push(c);
								
							}
							}
						}
					
					}
				}
				//move from tableau stack 
						Card a = new Card();
						Card b = new Card();
						CardStack c = new CardStack(CardStack.TABLEAU);
						//first argument 
						if(firstArgu.charAt(0) == 't' || firstArgu.charAt(0) == 'T') {
							for(int i=0; i<tableauStacks.length;i++
									) {
								//gets the card that is being moved
								
								if(i == Character.getNumericValue(firstNum.charAt(0))) {
									a = tableauStacks[i].peek();
									
									//gets the stack that the other card is going to be pushed to
									
									if(secondArgu.charAt(0) == 't') {
											for(int j =0; j<tableauStacks.length; j++) {
												if( j == Integer.parseInt(secondNum)) {
													
													// need to move card if there's only one and put K card in there
												
													if(tableauStacks[j].isEmpty()) {
														if(a.getValue() == Arrays.asList(Card.VALUES).indexOf("K")) {
															tableauStacks[j].push(a);
															tableauStacks[i].pop();
															tableauStacks[i].peek().setFaceUp(true);
														}
														else {
															System.out.println("This card can't be put in the foundation stacks right now!! \n\n");
														}
													}
													else if(!tableauStacks[j].isEmpty()) {
														b = tableauStacks[j].peek();
														
														//compare both peeks
														
														if(b.getValue() - a.getValue() == 1) {
															
										//push it to the stack, and delete it from the other stack and set the new peek face up
													
															tableauStacks[j].push(a);
															tableauStacks[i].pop();
															if(tableauStacks[i].isEmpty()) {
																tableauStacks[i].toString();
															}
															else {
																tableauStacks[i].peek().setFaceUp(true);
															}
														}
														else {
															System.out.println("This card can't be put in the foundation stacks right now!! \n\n");
															}
														}
													}
												}
											}
										
										
										
						//move from tableau stack to foundation stack
						
							if(secondArgu.charAt(0) == 'f') {
								for(int j=0; j<foundationStacks.length;j++) {
										if(j==Integer.parseInt(secondNum)) {
											if(foundationStacks[j].isEmpty() && a.getValue() == Arrays.asList(Card.VALUES).indexOf("A")) {
												foundationStacks[j].push(a);
												tableauStacks[i].pop();
												
												if(tableauStacks[i].isEmpty()) {
													tableauStacks[i].toString();
												 	
												}
												else
													tableauStacks[i].peek().setFaceUp(true);
											}
											
										//have to pop value in foundation stack and push the new one in****
											
											else if(!foundationStacks[j].isEmpty()) {
												b = foundationStacks[j].peek();
												
												//check if its the same suit and in order, in order to put it in the same foundation stack
												
												if(b.SUITS[b.getSuit()] == a.SUITS[a.getSuit()]) {
														if(a.getValue() - b.getValue() == 1 ) {
															foundationStacks[j].pop();
															foundationStacks[j].push(a);
															tableauStacks[i].pop();
															tableauStacks[i].peek().setFaceUp(true);
														}
													}
											}
										}
										}
								}
									}
								}
							}
			 }
						
			 
							
					
									
			 //moving cards in blocks
			 
			 if(userSelection.indexOf("moveN") != -1) {
				 if(userSelection.length() < 12) {
					 System.out.println("Wrong input");
					 continue;
				 }
				String firstArgu = userSelection.substring(5).trim();
				String firstNum = userSelection.substring(7);
				String secondArgu = userSelection.substring(9).trim();
				String secondNum = userSelection.substring(10);
				String thirdArgu = userSelection.substring(12);
				
				int counter = Character.getNumericValue(thirdArgu.charAt(0));
				CardStack a = new CardStack(CardStack.TABLEAU);
				Card b = new Card();
				
				if(firstArgu.charAt(0) == 't' || firstArgu.charAt(0) == 'T') {
					for(int i=0; i<tableauStacks.length;i++) {
						if(i == Character.getNumericValue(firstNum.charAt(0))) {
						
							//gets specific stack, then pushes the cards faced up to a cardstack
							
							if(secondArgu.charAt(0) == 't') {
								for(int j = 0; j<tableauStacks.length;j++) {
									if(j == Character.getNumericValue(secondNum.charAt(0))) {
										if(tableauStacks[j].isEmpty()) {
											while(tableauStacks[i].peek().isFaceUp())
												a.push(tableauStacks[i].pop());
											if(tableauStacks[i].peek().getValue() == Arrays.asList(Card.VALUES).indexOf("A") || a.peek().getValue() == Arrays.asList(Card.VALUES).indexOf("K")) {
												while(!a.isEmpty())
													tableauStacks[j].push(a.pop());
												if(!tableauStacks[i].isEmpty())
													tableauStacks[i].peek().setFaceUp(true);
												else
													tableauStacks[i].toString();

											
										}
										}
										else if(!tableauStacks[j].isEmpty()){
										b = tableauStacks[j].peek();
										while(counter>=1 && tableauStacks[i].peek().isFaceUp()) {
											a.push(tableauStacks[i].pop());
											counter--;
										}
										if(b.getValue() - a.peek().getValue() == 1) {
										
											//while the cardStack is not empty pop all of the cards to the new tableau stack
											
											while(!a.isEmpty())
												tableauStacks[j].push(a.pop());
											if(tableauStacks[i].isEmpty())
												tableauStacks[i].toString();
											else
												tableauStacks[i].peek().setFaceUp(true);
										}
										else {
											System.out.println("Card can't go there");
										}
										}
									}
								}
							}
						}
					}
				}
			 }
			 
			 displayGameBoard();

			 	//restarts game
			
			 if(userSelection.equalsIgnoreCase("restart")) {
				 System.out.println("Do you want to restart the game");
				 String restart = input.nextLine();
				 if(restart.equalsIgnoreCase("Yes")) {
					 waste.pop();
					 initializeGame();
				 	displayGameBoard();
				 }
				 else if(restart.equalsIgnoreCase("No"))
					 displayGameBoard();	 	
			 }
			 
			 
			 //quits game
		
		}while(!userSelection.equalsIgnoreCase("quit"));
		
		
		
	}
	
	
	/**
	 * This method initializes the game
	 * It sets up the tableau stacks, foundation stack and stock stack
	 * It shuffles the distinct cards
	 */
	public static void initializeGame()  {
			//make stack for tableau
		for(int i=0;i<tableauStacks.length;i++)
			tableauStacks[i] = new CardStack(CardStack.TABLEAU);
			//make stack for foundation
		for(int i=0;i<foundationStacks.length;i++)
			foundationStacks[i] = new CardStack(CardStack.TABLEAU);
		
		for(int i = 1; i<Card.SUITS.length;i++) {
			for(int j = 1; j<Card.VALUES.length;j++) {
				Card newCard = new Card(i,j,false);
				distinct.push(newCard);
				copyCards.add(newCard);
			}
		}
		while(!distinct.isEmpty()) {
			main.add(distinct.pop());
		}
		//shuffle cards
		Collections.shuffle(main);
		
		//put cards in the tableau pile and it increases, with the last one faced up
		for(int i=0; i<tableauStacks.length;i++) {
			for(int j=0; j < i+1;j++) {
				Card c = main.get(main.size()-1);
				main.remove(main.size()-1);
				if(j==i)
					c.setFaceUp(true);
				tableauStacks[i].push(c);
			}
		}
		
		//the rest of the Cards go to the stock pile
		while(!main.isEmpty()) {
			stock.push(main.get(main.size()-1));
			main.remove(main.size()-1);
		}
	}
	
	/**
	 * This method prints out the game board 
	 */
	public static void displayGameBoard() {
		String foundation = "";
		String empty1= "";
				//displays foundation pile
		for(int i=0; i< foundationStacks.length; i++) {
			if(foundationStacks[i].isEmpty())
				empty1+= "[F" + (i+1) + "]";
			else
				empty1+=foundationStacks[i].toString();
		}
		
		foundation = empty1;
		String tableau = "";
			//displays the tableau piles
		for(int i=tableauStacks.length-1; i>=0; i--) {
			tableau += "T" + (i+1) + " " + tableauStacks[i].toString() + "\n";
		}
		System.out.printf("%s 	%s %s \n\n%s", foundation, waste.toString(), 
				stock.toString(), tableau);
	}
	
	/**
	 * This method checks if the player won the game
	 * If all of the cards are faced up then, you have won the game
	 * @return
	 * returns true if you won, false if you didn't
	 */
	public static boolean checkWinningBoard() {
		//Create another list to hold copy of cards
		for(Card c : copyCards) {
			if(!c.isFaceUp())
				return false;
		}
		return true;
	}

}
/**
 * These classes represents the Exceptions in the stackotaire game
 *If the stack is full and if it's empty
 */
class FullStackException extends Exception{
	FullStackException(){
		super();
	}
}
class EmptyStackException extends Exception{
	EmptyStackException(){
		super();
	}
}
