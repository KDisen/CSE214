/**
*The CardCollection Java application contains all the methods needed for the CollectionManager
*@author Keven Disen, ID:111433335
**/
public class CardCollection implements Cloneable{
	final int MAX_CARDS = 100;
	private BaseballCard[] cards;
	int itemsCurrently = 0;
	public CardCollection() {
		cards = new BaseballCard[MAX_CARDS];
		
	}
	/**
	 * The size method finds the number of items currently in the collection
	 * <dt>Precondition<dt>
	 * <dd>CardCollection was instantiated
	 * @return
	 * The number of BaseballCards in this specific collection
	 */
	public int size() {
		return itemsCurrently;
	}
	/**
	 * Adds a card into the position you want it to be in
	 * @param newCard - new Baseball card added to the collection
	 * @param position - position you want the newCard inserted in the Collection
	 * @throws IllegalArgumentException
	 * @throws FullCollectionException
	 */
	public void addCard(BaseballCard newCard, int position) 
			throws IllegalArgumentException, FullCollectionException {
		if(position<1 || position>this.size()+1 ) throw new IllegalArgumentException();
		else if(this.size()==MAX_CARDS) throw new FullCollectionException();
		else {
			for(int i = cards.length -1; i<position-1;i--) {
				cards[i+1] = cards[i];
			}
		cards[position-1] = newCard;
		itemsCurrently++;
		}
	}
	/**
	 * adds the Baseball card to the end of the list
	 * @param newCard
	 * @throws IllegalArgumentException
	 * @throws FullCollectionException
	 */
	public void addCard(BaseballCard newCard) throws IllegalArgumentException, FullCollectionException {
		addCard(newCard, this.size()+1);
	}
	/**
	 * Removes a Baseball card depending the position it is in
	 * @param position
	 * <dt> Precondition<dt>
 	 * <dd> CardCollection object was instantiated
	 * <dt>Postcondition<dt> <dd>the card at the desired position is removed, cards that are still in the collection 
	 * are moved up one position
	 * @throws IllegalArgumentException
	 */
	public void removeCard(int position) throws IllegalArgumentException {
		if(position<1 || position>this.size()+1 ) throw new IllegalArgumentException();
		else {
				for(int i = position-1; i < cards.length-1; i++){
					cards[i] = cards[i+1];
					cards[i+1] = null;
				}
				itemsCurrently--;
		}
		}
	/**
	 * Gets any card you want that is in a specific position
	 * @param position
	 * @return
	 * returns what the card has inside the array (name, manufacturer, price, size)
	 * @throws IllegalArgumentException
	 */
	public BaseballCard getCard(int position) throws IllegalArgumentException{
		if(position<1 || position>this.size()+1 ) throw new IllegalArgumentException();
		return cards[position-1];
	}
	/**
	 * Exchange a baseball card from current collection to another collection
	 * @param other
	 * @param myPosition
	 * @param theirPosition
	 * <dt> Precondition<dt> 
	 * <dd> Both Cardcollection objects are instantiated and
	 *  1 < myPosition < items_currently_in_this_list and 1 < theirPosition < items_currently_in_other_list.
	 *  @throws IllegalArgumentException
	 */
	public void trade(CardCollection other, int myPosition, int theirPosition) throws IllegalArgumentException {
		if(myPosition<1 || myPosition>this.size()+1 ) {
			if(theirPosition<1 || theirPosition>this.size()+1)throw new IllegalArgumentException();
		}
	}
	/**
	 * This method shows that the card the user inputs exists inside of the collection
	 * @param card
	 * @return
	 * returns true if the input card is equal to a card inside of the collection
	 */
	public boolean exists (BaseballCard card) {
		boolean exist = false; 
		for(int i=0; i<cards.length; i++) {
			if(card.equals(cards[i])) {
				exist = true;
				break;
			}
		}
		return exist;
	}
	/**
	 * This method goes to the method below it, basically prints out all of the cards in the collection
	 */
	public void printAllCards() {
		System.out.println(this.toString());

	}
	/**
	 * This toString method prints out the card with a format fitting the headers in Collection Manager
	 */
	public String toString() {
			String allCards = "";
			for(int i=1; i<size()+1; i++) {
			try {
				allCards = String.format("%-2d%-16s%-15s%-8d%-1s%-7.2f%-11s",i,getCard(i).getName(), getCard(i).getManufacturer(), 
						getCard(i).getYear(), "$", getCard(i).getPrice(), getCard(i).printSize());
			}catch(IllegalArgumentException e) {
				e.printStackTrace();
			}
			System.out.println(allCards);
			}
			String empty = " ";
			return empty;
		}
}
