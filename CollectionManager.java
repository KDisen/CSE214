/**
* The <code>Collection Manager</code> class is the main method, which brings together the data structures 
* and the methods from the other 2 classes together.
*    
* @author Keven Disen 
*    e-mail: keven.disen@stonybrook.edu
*    Stony Brook ID: 111433335
**/
import java.util.*;
public class CollectionManager {
	public static void main(String[] args) throws FullCollectionException, CloneNotSupportedException {
		Scanner input = new Scanner(System.in);

		String userSelection = "";
		CardCollection collectionsA = new CardCollection();
		CardCollection collectionsB = new CardCollection();
		BaseballCard ballCard= new BaseballCard();
		//Main Menu of Collection Manager
			System.out.println("Main Menu: \n"
				+ "\n A) Add Card \n C) Copy \n E) Update Price"
				+ "\n G) Get Card \n L) Locate Card \n N) Update Name \n P) Print All Cards"
				+ "\n R) Remove Card \n S) Size \n T) Trade \n V) Value of Collections"
				+ "\n Q) Quit");
			do {
				//when putting operations, menu doesn't show up
				System.out.println("\nSelect an operation: ");
				userSelection = input.nextLine().toUpperCase();
			
				try {
					//Add A Card in Collection A
					if(userSelection.equals("A")) {
						System.out.print("Enter the collection: ");
						String collection = input.nextLine().toUpperCase();
						if(collection.equals("A")) {
							System.out.print("Enter the name: ");
							String playerName = input.nextLine();
							System.out.print("Enter the manufacturer: ");
							String manufacturer = input.nextLine();
							System.out.print("Enter the year: ");						
							int year = input.nextInt();
							while(year < 0){
								System.out.print("Enter a positive value for year: ");
								year = input.nextInt();
							}	
							System.out.print("Enter the size: ");
							int sizeX = input.nextInt(); int sizeY = input.nextInt();
							System.out.print("Enter the price: \n");
							float price = input.nextFloat();
							while(price < 0){
								System.out.print("Enter a valid price: ");
								price = input.nextFloat();
							}
							System.out.print("Enter the position: ");
							int position = input.nextInt();
							collectionsA.addCard(new BaseballCard(playerName, 
									manufacturer, year, price, sizeX, sizeY ));
							
							//Add card in collection B
						}else if(collection.equals("B")) {
							System.out.print("Enter the name: ");
							String playerName = input.nextLine();
							System.out.print("Enter the manufacturer: ");
							String manufacturer = input.nextLine();
							System.out.print("Enter the year: ");						
							int year = input.nextInt();
							while(year < 0){
								System.out.print("Enter a positive value for year: ");
								year = input.nextInt();
							}	
							System.out.print("Enter the size: ");
							int sizeX = input.nextInt(); int sizeY = input.nextInt();
							System.out.print("Enter the price: \n");
							float price = input.nextFloat();
							while(price < 0){
								System.out.print("Enter a valid price: ");
								price = input.nextFloat();
							}
							System.out.print("Enter the position: ");
							int position = input.nextInt();
							collectionsB.addCard(new BaseballCard(playerName, 
									manufacturer, year, price, sizeX, sizeY ));
							}
						input.nextLine();
						}
					//Copy card to another collection
					else if(userSelection.equals("C")) {
						System.out.print("Enter the collection to copy from: ");
						String collection = input.nextLine().toUpperCase();
						System.out.print("Enter the position of the card to copy: ");
						int position = input.nextInt();
						System.out.print("Enter the collection to copy to: ");
						String clone = input.next().toUpperCase();
						//Copy card from a collection and position to another collection
						System.out.println();
						//Copy card from collection A
						if(collection.equals("A")) {
							//if clone is "A" then copy the card and add it collection A
							if(clone.equals("A")) {
								input.nextLine();
								System.out.println("Copied " + collectionsA.getCard(position) + " to collection A ");
								collectionsA.addCard(collectionsA.getCard(position));
							}
							//if clone is "B" then copy the card and add it collection B
							else if(clone.equals("B")) {
								input.nextLine();
								System.out.println("Copied " + collectionsA.getCard(position) + " to collection B ");
								collectionsB.addCard(collectionsA.getCard(position));
							}
}
						//Copy card from collection B
						else if(collection.equals("B")) { 
							//if clone is "A" then copy the card and add it collection A
							if(clone.equals("A")) {
								input.nextLine();
								System.out.println("Copied " + collectionsA.getCard(position) + " to collection A ");
								collectionsA.addCard(collectionsB.getCard(position));
							}
							//if clone is "B" then copy the card and add it collection B
							else if(clone.equals("B")) {
								input.nextLine();
								System.out.println("Copied " + collectionsA.getCard(position) + " to collection B ");
								collectionsB.addCard(collectionsB.getCard(position));
							}
						}

					}
					//edit card with new price
					else if(userSelection.equals("E")) {
						System.out.print("Enter the collection: ");
						String collection = input.nextLine().toUpperCase();
						System.out.print("Enter the position: ");
						int position = input.nextInt();
						input.nextLine();
						System.out.print("Enter the new price: ");
						float newPrice = input.nextFloat();
						while(newPrice < 0){
							System.out.print("Enter a valid price: ");
							newPrice = input.nextFloat();
						}
				//**********gets the card but changes the price for the card

						if(collection.equals("A")) {
							ballCard = (BaseballCard) collectionsA.getCard(position).clone();
							collectionsA.removeCard(position);
							ballCard.setPrice(newPrice);
							collectionsA.addCard(ballCard, position);

						}
						else if(collection.equals("B")) { 
							ballCard = (BaseballCard) collectionsB.getCard(position).clone();
							collectionsB.removeCard(position);
							ballCard.setPrice(newPrice);
							collectionsB.addCard(ballCard, position);
						}
						input.nextLine();
					}
					//Get Card
					else if(userSelection.equals("G")) {
						System.out.print("Enter the collection: ");
						String collection = input.nextLine().toUpperCase();
						System.out.print("Enter the position: ");
						int position = input.nextInt();
						if(collection.equals("A"))
							System.out.print(collectionsA.getCard(position) + "\n");
						else if(collection.equals("B"))
							System.out.print(collectionsB.getCard(position) + "\n");
						 //So the main menu doesn't repeat twice after chosen operation
						input.nextLine();
					}
					
					//Look For Card
					else if(userSelection.equals("L")) {
						System.out.print("Enter the name: ");
						String playerName = input.nextLine();
						System.out.print("Enter the manufacturer: ");
						String manufacturer = input.nextLine();
						System.out.print("Enter the year: ");						
						int year = input.nextInt();
						System.out.print("Enter the size: ");
						int sizeX = input.nextInt(); int sizeY = input.nextInt();
						System.out.print("Enter the price: ");
						float price = input.nextFloat();
						while(price < 0){
							System.out.print("Enter a valid price: ");
							price = input.nextFloat();
						}
						System.out.print("Enter the position: ");
						int position = input.nextInt();
						if(collectionsA.exists(new BaseballCard(playerName, manufacturer, year, price, sizeX, sizeY)))
							System.out.println("The card is in collection A \n" );
						else if(collectionsB.exists(new BaseballCard(playerName, manufacturer, year, price, sizeX, sizeY)))
							System.out.println("The card is in collection B \n" );
				//Has to be in one of the collections
						input.nextLine();
					}
					//edit card with new name
					else if(userSelection.equals("N")) {
						System.out.print("Enter the collection: ");
						String collection = input.nextLine().toUpperCase();
						System.out.print("Enter the position: ");
						int position = input.nextInt();
						input.nextLine();
						System.out.print("Enter the new name: ");
						String playerNewName = input.nextLine();
				//*********changes name of the old card in collection A
						if(collection.equals("A")) {
							//if don't clone then when you put the new name it changes both of the names to one
							ballCard = (BaseballCard) collectionsA.getCard(position).clone();
							collectionsA.removeCard(position);
							ballCard.setName(playerNewName);
							collectionsA.addCard(ballCard, position);

						}
				//*********changes name of the old card in collection B
						else if(collection.equals("B")) { 
							//if don't clone then when you put the new name it changes both of the names to one
							ballCard = (BaseballCard) collectionsB.getCard(position).clone();
							collectionsB.removeCard(position);
							ballCard.setName(playerNewName);
							collectionsB.addCard(ballCard, position);
						}

					}
					
					//Print All Cards
					else if(userSelection.equals("P")) {
						System.out.println("Collection A:");
						System.out.println();
						//headers to print neatly
						System.out.println("# Name \t \t Manufacturer \t Year \t Price \t Size\n"
								+ "- ---- \t \t ------------ \t ---- \t ----- \t ----");
						collectionsA.printAllCards();
						System.out.println();
						System.out.println("Collection B: ");
						System.out.println();
						//headers for print
						System.out.println("# Name \t \t Manufacturer \t Year \t Price \t Size\n"
								+ "- ---- \t \t ------------ \t ---- \t ----- \t ----");
						collectionsB.printAllCards();
					}
					//remove card from a collection
					else if(userSelection.equals("R")) {
						System.out.print("Enter the collection to remove from: ");
						String removeFrom = input.nextLine().toUpperCase();
						System.out.print("Enter the position to remove: ");
						int positionRemove = input.nextInt();
						//removes card from collection A
						if(removeFrom.equals("A")) {
							
							System.out.println("Removed " + collectionsA.getCard(positionRemove) + " from collection A");
							collectionsA.removeCard(positionRemove);
							

						}
						//removes card from collection B
						if(removeFrom.equals("B")) {
							input.nextLine();
							System.out.println("Removed " + collectionsB.getCard(positionRemove) + " from collection B");
							collectionsB.removeCard(positionRemove);
						}
						input.nextLine();

					}
					//Print out sizes of collections
					else if(userSelection.equals("S")) {
						System.out.println("Collection A has " + collectionsA.size() + " card(s). "
								+ "Collection B has " + collectionsB.size() + " card(s).");

					}
					//trade cards with other collection
					else if(userSelection.equals("T")) {
						System.out.print("Enter the position of the card to trade from Collection A: ");
						//exception thrown ArrayOutOFbounds
						int tradeA = input.nextInt();
						collectionsB.addCard(collectionsA.getCard(tradeA));
						collectionsA.removeCard(tradeA);
						System.out.print("Enter the position of the card to trade from Collection B: ");
						int tradeB = input.nextInt();
						input.nextLine();
						collectionsA.addCard(collectionsB.getCard(tradeB));
						collectionsB.removeCard(tradeB);
						input.nextLine();
						//remove card from both collections and add it to the other collection
						System.out.println("Traded " + collectionsB.getCard(tradeB) + " for " + collectionsA.getCard(tradeA));
						
					}

					//Add up total for prices from each collection
					else if(userSelection.equals("V")) {
						float priceSumA =0;
						float priceSumB =0;
						
						for(int i=1; i<collectionsA.size()+1;i++) {
							priceSumA += collectionsA.getCard(i).getPrice();
						}
						for(int i=1; i<collectionsB.size()+1;i++) {
							priceSumB += (collectionsB.getCard(i)).getPrice();
						}
						System.out.println("The total price of Collection A is " + priceSumA +
								"\nThe total price of Collection B is " + priceSumB);
					}
					
				}
				catch(IllegalArgumentException ex){
					ex.printStackTrace();
					input.nextLine();
					System.out.println("invalid input");
				}catch(FullCollectionException fc) {
					input.nextLine();
					System.out.println("Collection is Full");
				}catch(InputMismatchException im) {
					input.nextLine();
					System.out.println("invalid input");
				}
			}while(!userSelection.equals("Q"));
		System.out.println("Quitting...\nPROGRAM TERMINATED!");
	}
}


class FullCollectionException extends Exception{
	FullCollectionException(){
		super();
	}
}


