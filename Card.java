/**
 * File: Card.java
 * The class Card creates a new Card and gets the front Image of the card that
 * corresponds with the card rank and its suit
 * 
 * @author Brandy Washington
 */

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Card extends Parent
{
	private int rank;
	private int suit;
	private ImageView frontImage;
	private static final int  HEARTS    =  0 ;
	private static final int  DIAMONDS  =  1 ;
	private static final int  SPADES    =  2 ;
	private static final int  CLUBS     =  3 ;
	private static final Map<String, ImageView> cardImages = new HashMap<String, ImageView>();
	
	/*
	 * Constucts a Card object with an integer rank and an integer suit
	 * and loads and stores the front Images for card
 	*/
	public Card(int rank, int suit) 
	{
		this.rank = rank;
		this.suit = suit;
		
		loadCardImage(this);
		
		if(suit == HEARTS)
		{
			frontImage = cardImages.get(rank + "h");
		}
		else if(suit == DIAMONDS)
		{
			frontImage = cardImages.get(rank + "d");
		}
		else if(suit == SPADES)
		{
			frontImage = cardImages.get(rank + "s");
		}
		else 
		{
			frontImage = cardImages.get(rank + "c");
		}
		
		getChildren().add(new StackPane(frontImage));




	}
	/*
 	* Constructs a blank Card object
 	*/
	public Card()
	{
		
	}

	/**	Constructs a new Card image with a string
	@param string the string used to create a card
	@return a Card that is now the newly created card
 	*/
	public Card(String string)
	{

		int i = 0;
		if(string.length() == 3)
		{
				String number = string.substring(0,2);
				rank = Integer.parseInt(number);
		}
		else
		{
			rank = Character.getNumericValue(string.charAt(i));
		}
		if(string.charAt(string.length()-1)== 'h')
		{
			suit = HEARTS;
		}
		else if(string.charAt(string.length()-1) == 'd')
		{
			suit = DIAMONDS;
		}
		else if(string.charAt(string.length()-1) == 's')
		{
			suit = SPADES;
		}
		else if(string.charAt(string.length()-1) == 'c')
		{
			suit = CLUBS;
			
		}

	}
	
	/**	Defines the two string method for a Card object 
	@return a String that represents how a Card object is printed
	 */
	 @Override
	public String toString() 
	{
		if(suit == HEARTS)
		{
			return rank + "h";
		}
		else if(suit == DIAMONDS)
		{
			return rank + "d";
		}
		else if(suit == SPADES)
		{
			return rank + "s";
		}
		else 
		{
			return rank + "c";
		}
	}

	/**	Checks to see if the two card objects are equal
	@param card used to determine if card is equal to the current Card object
	@return a boolean value to confirm of the two cards are equal or not
 	*/
	public boolean isEqual(Card card)
	{
		if(rank == card.rank && suit == card.suit)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**	Gets the image that is used to represent a Card object of a 
	 * specific rank and suit
	@return an ImageView that is the used for the front of the card.
 	*/
	public ImageView getFrontImage()
	{
		return this.frontImage;
	
	}

	/**	Gets only the rank of the current card
	@return an int that is the current card's rank
 	*/
	public int getRank()
	{
		return this.rank;
	}

	/**	loads the images used for the Card objects in the game
	 * @param card which is the card you want to load the front Image for
	 */
	public void loadCardImage(Card card)
	{
		String image_file_name = "cards/" + card.toString() + ".gif";
  
		Image card_face_image = new Image(image_file_name, false);
			  
		ImageView imageView = new ImageView(card_face_image);
	  
		String key_for_image = card.toString() ;
	  
		cardImages.put( key_for_image, imageView ); 
				
	}


}
