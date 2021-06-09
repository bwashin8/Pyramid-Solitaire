
/**
 * File: Deck.java
 * The class Deck creates a new Deck by adding new Card objects inside of it  contains 13 cards of each suit
 * @author Brandy Washington
 */

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.layout.Pane;

public class Deck extends Pane 
{

    private ArrayList<Card> cardsInDeck = new ArrayList<Card>();
    
    /**
     * Constructor: Creates a deck of 52 cards with 13 cards in spades,
     * 13 cards in hearts, 13 cards in diamonds, 13 cards in clubs.
     */
    public Deck()  
    {
        for(int suit = 0; suit < 4; suit++)
        {
            for(int rank = 1; rank < 14; rank++)
            {
                addCard(new Card (rank, suit));
            }
        }
    }


    /**	Compares to Card objects 
	@param card a Card that is to be added to the deck 
 	*/
    public void addCard(Card card)
    {
        if(cardsInDeck.size() <= 52)
        {   
            cardsInDeck.add(card);
            
        }
    }

    /**	Shuffles the deck 
 	*/
    public void shuffle()
    {
        Collections.shuffle(cardsInDeck);
    }


    /** Gets all cards in the deck
     * @return an ArrayList of Card that contains all the cards in the deck.
     */
    public ArrayList<Card> getCardList()
    {
        return cardsInDeck;
    }

    /** Gets all cards that will be used as the draw pile for the game
     * @return an ArrayList of Card that contains all the cards in the draw pile.
     */
    public ArrayList<Card> getDrawPile()
    {
        ArrayList<Card> drawPile =  new ArrayList<Card>();
        for(int i = 28; i <= cardsInDeck.size()-1 ; i++ )
        {
            drawPile.add(cardsInDeck.get(i));
        }
        return drawPile;
    }

    /** Gets all cards that will be used as the pyramid foundation for the game
     * @return an ArrayList of Card that contains all the cards in the pyramid.
     */
    public ArrayList<Card> getPyramidCards()
    {
        ArrayList<Card> pyramidCards =  new ArrayList<Card>();
        for(int i = 0; i < 28 ; i++ )
        {
            pyramidCards.add(cardsInDeck.get(i));
        }
        return pyramidCards;
    }

    /** Gets the Card that it to be used as the first draw card for the game
     * @return a Card that is the top of the draw pile.
     */
    public Card getDrawCard()
    {
        ArrayList<Card> drawCards = this.getDrawPile();
        Card nextCard = null;
        if(drawCards.size() > 0)
        {
            nextCard = drawCards.remove(drawCards.size()-1);
        }
        return nextCard;
    }


    @Override
    /** Formats how the deck should look when playing a console version of Pyramid
     * @return a String that forms the pyramid shape of the deck in the console.
     */

    public String toString()
    {
        return String.format( 

        "            %s\n" +
        "          %s  %s\n" +
        "        %s  %s  %s\n" +
        "      %s  %s  %s  %s\n" +
        "    %s  %s  %s  %s  %s\n" +
        "  %s  %s  %s  %s  %s  %s\n" +
        "%s  %s  %s  %s  %s  %s  %s\n", this.getPyramidCards().toArray());
        
    }

}

