/** 
 * File: PyramidPlay.java 
 * Class Main is at bottom
 * The class PyramidPlay creates a new PyramidPlay Object and allows the player 
 * to play a game in the console. To play the console version, the loadImages(this) line in the card constructor
 * has to be commented out as well as the getChildren().add(new StackPane(frontImage)). 
 * @author Brandy Washington
 * 
 * LOOKBACK: There were a lot of ups and downs during the process of creating this game, but 
 * it was really fun to do! First and foremost, I want to thank you for your advice! I was
 * confused at first about the concepts of console based and GUI based programs, but I took a day to 
 * do some research and it finally made sense. Once I began getting the console based version of
 * Pyramid, everything clicked. I, then, was able to easily created another class for the GUI 
 * version of pyramid that would be used for the actual application of the game. For starters, I
 * focused on trying to modify the code for the console based version, so that it would work best with the GUI. It was pretty 
 * confusing at first to understand how everything worked together; however, by using the debugging
 * tool in Visual Code, it made it a lot easier to understand what was going wrong and why.
 * Again, I really appreciate you telling me to take a step back from the way I was doing things
 * at first. I'll admit I was frustrated, but now that I have a somewhat have a nearly finished
 * program, I definitely understand why you gave me such advice. My program is functioning, however, a
 * bug I found was that the undo button stops working at a certain point, and I have not been able
 * to find out where that issue is occuring and why. Overall, doing this project was really fun and eye opening to me.
 * I got the do some research and learn how some classes in JavaFX work. 
 * 
 */
 

import java.util.ArrayList;
import java.util.Scanner;

public class PyramidPlay 
{
    private Deck deck;
    private ArrayList<Card> pyramid = new ArrayList<Card>();
    private ArrayList<Card> draw = new ArrayList<Card>();
    private int score;
    private String isOver;
    private Card cardSelected1;
    private Card cardSelected2;
    private Scanner scanner;
    private final static String selectCardPrompt = "Please select cards to match or type d to draw a card from the draw pile",
        WHITE_SPACE = "\\s+";
    private ArrayList<Card> moves = new ArrayList<Card>();
    private Deck copyDeck;
    private int drawIndex;
    private Card drawCard;
    private int pyramidIndex;
    private boolean gameStarted = false;

    /**
     * Constructs a PyramidPlay object
     * 
     * @param deck used to play the game with
     */
    public PyramidPlay(Deck deck) 
    {
        this.deck = deck;
        score = 0;
        isOver = "";
        drawIndex = 0;
        pyramidIndex = 0;

    }

    /**
     * Checks to see if the sum of the card's ranks are 13
     * 
     * @param card1 first card
     * @param card2 second card
     * @return a boolean value to check if the card rank is 13
     */
    public boolean isMoveValid(Card card1, Card card2) {

        if (card1.getRank() + card2.getRank() == 13) 
        {
            return true;
        } 
        else 
        {
            return false;
        }

    }

    /**
     * sets the score for the game
     * @return an int that is the new score for the game
     */
    public int setScore() 
    {

        score = score + 13;
        return score;

    }

    /**
     * sets the score for the game
     * @param deck deck used to play game
     * @param shuffle deck used for dealing and determine if it needs to be shuffled
     * @return an Deck object that is the deck to be used for the game
     */
    public Deck dealCards(Deck deck, boolean shuffle) {
        if (shuffle == true) 
        {
            copyDeck = new Deck();
            deck.shuffle();
            copyDeck = deck;
            System.out.println(copyDeck);
            drawCard = getDrawCard(deck);
            System.out.println("Draw:" + drawCard);
            System.out.println("Score:" + score);
            return copyDeck;

        } 
        else 
        {
            if (drawCard == draw.get(draw.size() - 1)) 
            {
                copyDeck = new Deck();
                copyDeck = deck;
                System.out.println(copyDeck);
                System.out.println("Draw:" + drawCard);
                System.out.println("Score:" + score);
                return copyDeck;
            } 
            else 
            {
                copyDeck = new Deck();
                copyDeck = deck;
                System.out.println(copyDeck);
                System.out.println("Draw:" + drawCard);
                System.out.println("Score:" + score);
                return copyDeck;
            }

        }

    }

    /**
     * Allows the user to input 1 or 2 cards to match and it will validate the move
     * to see if it is a valid move.
     */
    public void selectCard() 
    {

        System.out.println(selectCardPrompt);
        scanner = new Scanner(System.in);
        String inpuString = scanner.nextLine();
        if (inpuString.equals("d")) 
        {
            drawCard = getNextDrawCard();

        } 
        else if (inpuString.equals("quit")) 
        {
            gameStarted = false;
        } 
        else 
        {
            String[] cards = inpuString.split(WHITE_SPACE);
            if (cards.length == 1 || cards.length == 2) 
            {

                for (String card : cards) 
                {
                    Card selectedCard = new Card(card);
                    boolean exposed = this.exposed(selectedCard);
                    if (exposed) 
                    {
                        moves.add(selectedCard);
                    } 
                    else 
                    {
                        System.out.println("The card inputed is not an exposed card.");
                        selectCard();
                    }

                }

                valiadateMove();
            } 
            else 
            {
                throw new IllegalArgumentException("You need to input 1 or 2 cards");

            }

        }

    }


    /**
     * Validates the moves with the two cards the user has inputed.
     */
    public void valiadateMove() 
    {
        if (moves.size() == 1) 
        {
            cardSelected1 = moves.get(0);
            if (cardSelected1.getRank() == 13) 
            {
                moves.remove(cardSelected1);
                this.remove(cardSelected1);
                setScore();
            } 
            else 
            {
                moves.remove(cardSelected1);
                System.out.println("The sum of the two card ranks entered does not equal 13.");
                selectCard();

            }
        } 
        else if (moves.size() > 1) 
        {
            cardSelected1 = moves.get(0);
            cardSelected2 = moves.get(1);
            if (isMoveValid(cardSelected1, cardSelected2)) 
            {
                moves.remove(cardSelected1);
                moves.remove(cardSelected2);
                this.remove(cardSelected1);
                this.remove(cardSelected2);
                setScore();
            } 
            else 
            {
                moves.remove(cardSelected1);
                moves.remove(cardSelected2);
                System.out.println("The sum of the two card ranks entered does not equal 13.");
                selectCard();

            }
        }

    }

    /**
     * Gets the first draw card in the draw pile
     * @param deck deck to get the draw card from
     * @return a Card that is the first card in the draw pile
     */
    public Card getDrawCard(Deck deck) 
    {
        Card drawCard = null;
        drawCard = deck.getDrawCard();
        return drawCard;
    }

    /**
     * starts the game for the same pyramid
     */
    public void startGame() 
    {

        if (deck == null) 
        {
            throw new IllegalArgumentException("There are no cards in the Deck");
        }

        Deck copyDeck = this.dealCards(deck, true);
        this.pyramid = copyDeck.getPyramidCards();
        this.draw = copyDeck.getDrawPile();
        this.gameStarted = true;
    }

     /**
     * Removes the card from the deck or the pyramid
     * @param card card to be removed
     */
    public void remove(Card card) 
    {
        this.removeCardHelper(card);
    }

    /**
     * Handles the action to remove the card from the deck or the pyramid
     * @param card card to be removed
     */
    public void removeCardHelper(Card card) 
    {

        Card removeCard = new Card();
        if (card.isEqual(drawCard)) 
        {
            removeCard = this.getCardAtReference(card);
            draw.remove(removeCard);
            drawCard = draw.get(drawIndex - 1);
        } 
        else 
        {

            removeCard = this.getCardAtReference(card);
            pyramid.set(pyramidIndex, new Card()); // sets the index with a card of value 0h which symbolizes blank card

        }

    }

     /**
     * Gets the index of the card to be removed
     * @param card card to be removed
     * @return Card object the card at the specified index
     */
    public Card getCardAtReference(Card card) 
    {

        Card output = null;
        if (card.isEqual(drawCard)) 
        {
            output = drawCard;
            for (int j = 0; j < draw.size(); j++) 
            {
                if (draw.get(j).isEqual(card)) 
                {
                    drawIndex = j;
                    j = draw.size() + 1;
                }
            }
        } 
        else 
        {

            for (int i = 0; i < pyramid.size(); i++) 
            {
                if (pyramid.get(i).isEqual(card)) 
                {
                    output = pyramid.get(i);
                    pyramidIndex = i;
                    i = pyramid.size() + 1;
                }
            }
        }
        return output;

    }


     /**
     * Updates the board when cards are removed or to get a new draw card
     * @return Deck that obtains the updatedBoard with removed cards
     */
    public Deck updatedBoard() 
    {
        Deck updated = new Deck();
        ArrayList<Card> cards = this.pyramid;
        ArrayList<Card> drawList = this.draw;
        updated.getCardList().clear();
        for (int i = 0; i < cards.size(); i++) 
        {
            updated.getCardList().add(cards.get(i));
        }
        for (int i = 0; i < drawList.size(); i++) 
        {
            updated.getCardList().add(drawList.get(i));
        }
        return updated;

    }

     /**
     * Starts a game loop for the player until the game is won, it will
     * continue to go on. 
     */
    public void gameLoop() 
    {
        while (gameStarted == true) 
        {
            if (!isGameOver()) 
            {
                selectCard();
                Deck newDeck = updatedBoard();
                dealCards(newDeck, false);
            } 
            else 
            {
                gameStarted = false;
                isOver = "You Won! Here's you final Score" + score;
                System.out.println(isOver);
                scanner.close();
            }

        }

    }

    /**
     * Gets the next draw card in the draw pile
     * @return Card object that is the next card in the draw pile
     */
    public Card getNextDrawCard() 
    {
        int cardIndex = this.draw.indexOf(drawCard);
        if (cardIndex == 0) 
        {
            drawCard = draw.get(draw.size() - 1);
            return drawCard;
        } 
        else 
        {
            drawIndex = cardIndex - 1;
            drawCard = draw.get(drawIndex);
            return drawCard;
        }

    }

    /**
     * Sees if the card can be chosen or not
     * @param card card that the user wants to see if it is exposed or can be chosen
     * @return a boolean that will tell if the card is exposed or not
     */
    public boolean exposed(Card card) 
    {

        boolean isExposed = false;
        Card blank = new Card();
        int index = 0;
        int i = 0;

        for (int k = 0; k < pyramid.size(); k++) 
        {
            if (pyramid.get(k).isEqual(card)) 
            {
                i = k;
                k = pyramid.size() + 1;
            }
        }
        if (i >= 21 && i <= 27) 
        {
            isExposed = true;
        } 
        else if (card.isEqual(drawCard)) 
        {
            isExposed = true;
        } 
        else 
        {
            for (int j = 0; j < pyramid.size() - 1; j++) 
            {
                if (pyramid.get(j).isEqual(blank) && pyramid.get(j + 1).isEqual(blank)) 
                {
                    index = j + 1;
                    if (index >= 21 && index <= 27) 
                    {
                        if (i == (index) - 7) 
                        {
                            isExposed = true;
                            j = pyramid.size();
                        }
                    } 
                    else if (index >= 15 && index <= 20) 
                    {
                        if (i == (index) - 6) 
                        {
                            isExposed = true;
                            j = pyramid.size();
                        }
                    } 
                    else if (index >= 10 && index <= 14) 
                    {
                        if (i == (index) - 5) 
                        {
                            isExposed = true;
                            j = pyramid.size();
                        }
                    } 
                    else if (index >= 6 && index <= 9) 
                    {
                        if (i == (index) - 4) 
                        {
                            isExposed = true;
                            j = pyramid.size();
                        }
                    } 
                    else if (index >= 3 && index <= 5) 
                    {
                        if (i == (index) - 3) 
                        {
                            isExposed = true;
                            j = pyramid.size();
                        }
                    } 
                    else if (index >= 1 && index <= 2) 
                    {
                        if (i == (index) - 2) 
                        {
                            isExposed = true;
                            j = pyramid.size();
                        }
                    }

                }

            }

        }
        return isExposed;
    }

    /**
     * Determines if the game is over or not so gameLoop() method can end or not
     * @return a boolean that determines if game is over
     */
    public boolean isGameOver() 
    {
        for (int i = 0; i < pyramid.size(); i++) 
        {
            if (!pyramid.get(i).isEqual(new Card())) 
            {
                return false;
            }
        }
        return true;
    }

}

/**
* Class Main to test out the game of pyramid in the console
*/

class Main {
    public static void main(String args[]) 
    {
        Deck newDeck = new Deck();
        PyramidPlay play = new PyramidPlay(newDeck);
        System.out.println("Welcome to Pyramid! Here's your board. \n");
        play.startGame();
        play.gameLoop();

    }
}
