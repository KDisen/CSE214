/**
 * This class has the information of each card, these methods are used to put one card in an array of the collection
 * @author Keven Disen, ID: 111433335
 *
 */
public class BaseballCard implements Cloneable {
	private String playerName;
	private String cardManufacturer;
	private int year;
	private float price;
	private int[] imageSize = new int[2];
	private int sizeX;
	private int sizeY;
	/**
	 * default method and empty constructors
	 */
	public BaseballCard() {
	}
	/**
	 * Method holds all of the information on a baseball card
	 * @param name
	 * user inputs a player's name
	 * @param manufacturer
	 * user inputs a player's manufacturer
	 * @param yr
	 * user inputs a player's year
	 * @param p
	 * user inputs a player's price
	 * @param x
	 * user inputs a player's x image size
	 * @param y
	 * user inputs a player's y image size
	 */
	public BaseballCard(String name, String manufacturer, int yr, float p, int x, int y ) {
		this.playerName = name;
		this.cardManufacturer = manufacturer;
		this.year = yr;
		this.price = p;
		this.sizeX = x;
		this.sizeY = y;
		this.imageSize[0] = sizeX;
		this.imageSize[1] = sizeY;
	}
	/**
	 * Getter and Setter for the name of the player
	 * @return player's name
	 */
	public String getName() {
		return playerName;
	}
	public void setName(String playerName) {
		this.playerName = playerName;
	}
	/**
	 * Getter and Setter for the name of the player
	 * @return player's name
	 */
	public String getManufacturer() {
		return cardManufacturer;
	}
	public void setManufacturer(String cardManufacturer) {
		this.cardManufacturer = cardManufacturer;
	}
	/**
	 * Getter and Setter for the name of the player
	 * @return player's name
	 * @throws IllegalArgumentException
	 * throws it if year is negative
	 */
	public int getYear() {
		return year;
	}
	public void setYear(int year)throws IllegalArgumentException{
		if(year > 0)
			this.year = year;
		else
			throw new IllegalArgumentException();
	}
	/**
	 * Getter and Setter for the price of the player
	 * @return player's price
	 * @throws IllegalArgumentException
	 * throws it if price is negative 
	 */
	public double getPrice() {
			return price;	
	}
	public void setPrice(float p) {
		if(price > 0)
			this.price = p;
		else	
			throw new IllegalArgumentException();
		}
	/**
	 * Getter and Setter for the image sizes of the player
	 * @return player's image size
	 */
	public int getSizeX() {
		return sizeX;
	}
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	public int getSizeY() {
		return sizeY;
	}
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	/**
	 * This method gets the x and y from the image size and puts them together
	 * @return
	 * Image Size so, "20x30"
	 */
	public String printSize() {
		imageSize[0] = getSizeX();
		imageSize[1] = getSizeY();
		String sizeImage = imageSize[0] + "x" + imageSize[1];
		return sizeImage;
		
	}
	/**
	 * Clone method clones the baseball card so it wouldn't interfere with another card if you copy it
	 * @return returns a clone of the baseball card you want
	 */
	public Object clone() throws CloneNotSupportedException {
		BaseballCard ballCard = new BaseballCard();
		ballCard = (BaseballCard)super.clone();
		ballCard.playerName = getName();
		ballCard.cardManufacturer = getManufacturer();
		return ballCard;
		
	}
	/**
	 * Equals method, if the object is part of BaseballCard and all of the methods are equal to each 
	 * other then they are the same card
	 * @return
	 * true if all of the methods are the same as the one the user inputs
	 */
	public boolean equals(Object obj) {
		if(!(obj instanceof BaseballCard)) return false;
		else 
			//learned in recitation
			return 
					((getName().equals(((BaseballCard) obj).getName()) && getManufacturer().equals(((BaseballCard) obj).getManufacturer()) 
					&& getYear() == ((BaseballCard) obj).getYear() && getPrice() == ((BaseballCard) obj).getPrice() &&
					getSizeX() == ((BaseballCard) obj).getSizeX() && getSizeY() == ((BaseballCard) obj).getSizeY()));
	}
	/**
	 * This method prints out in certain cases, like when you want to copy a card to another collection, 
	 * it shows which card you copied
	 * @return
	 * it wouldn't return allCards because it will say that it's empty, so I made it that it will 
	 * return an empty string but use "System.out.print" so it can print every time this method is called
	 */
	public String toString() {
		String allCards = "";
		try {
			allCards = getName()+" "+ getManufacturer()+" "+ 
					getYear()+ " $"+ getPrice() +" "+ printSize();
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println(allCards);
		String empty = " ";
		return empty;
	}
}
