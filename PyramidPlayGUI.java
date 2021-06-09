
/**	File: PyramidPlayGUI.java
 * Constructs the Class PyramidPlayGui which used to play a game of Pyramid Solitaire in a GUI
    @author Brandy Washington
 */
import java.util.ArrayList;

public class PyramidPlayGUI 
{
    private Deck deck;
    private ArrayList<Card> pyramid = new ArrayList<Card>();
    private ArrayList<Card> draw = new ArrayList<Card>(24);
    private ArrayList<Card> moves = new ArrayList<Card>();
    private ArrayList<Card> undo = new ArrayList<Card>();
    private int score;
    private Card cardSelected1;
    private Card cardSelected2;
    private Card removeCard;
    private Deck copyDeck;
    private int drawIndex;
    private Card drawCard;
    private int pyramidIndex;

    /**
     * Constructs an PyramidPlayGUI object that will be used to play a GUI version
     * of Pyramid
     * @param deck which will be used to play the game
     */
    public PyramidPlayGUI(Deck deck) {
        this.deck = deck;
        score = 0;
        drawIndex = 0;
        pyramidIndex = 0;

    }

    /**
     * Checks to see if the sum of the card's ranks are 13
     * @param card1 first card selected
     * @param card2 second card selected
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
    public int setScore() {

        score = score + 13;
        return score;

    }

    /**
     * sets the score for the game
     * @param deck1 deck used to start game
     * @param shuffle should the deck be shuffled or no
     * @return an Deck object that is the deck to be used for the game
     */
    public Deck dealCards(Deck deck1, boolean shuffle) {
        if (shuffle == true) 
        {
            copyDeck = new Deck();
            deck1.shuffle();
            copyDeck = deck1;
            System.out.println(copyDeck);
            drawCard = getDrawCard(deck1);
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
     * Gets the card that had been selected
     * @param card card that has been selected or clicked in the GUI
     */
    public void selectCard(Card card) {

        Card input = card;
        if (input.getRank() == 13 && exposed(input)) 
        {
            moves.add(input);
            undo.add(input);
            valiadateMove();

        } 
        else if (input.equals(drawCard) && moves.size() == 1) 
        {
            moves.add(input);
            undo.add(input);
            valiadateMove();
        }
        else if (input.isEqual(drawCard)) 
        {
            moves.add(input);
            undo.add(input);
            valiadateMove();

        } 
        else 
        {
            boolean exposed = this.exposed(input);
            if (exposed) 
            {
                moves.add(input);
                undo.add(input);
            } else 
            {
                System.out.println("The card inputed is not an exposed card.");
            }
            if (moves.size() == 2) 
            {
                valiadateMove();
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
        }
        if (moves.size() > 1) 
        {
            cardSelected1 = moves.get(0);
            cardSelected2 = moves.get(1);
            if (cardSelected1.isEqual(drawCard) || cardSelected2.isEqual(drawCard)) 
            {
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
                    drawCard = getNextDrawCard();

                }
            }

            else 
            {
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
                }
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
     * starts the game for the Pyramid
     * @return a Deck which will be used for the game
     */
    public Deck startGame() 
    {

        if (deck == null) 
        {
            throw new IllegalArgumentException("There are no cards in the Deck");
        }

        Deck copy = this.dealCards(deck, true);
        this.pyramid = copy.getPyramidCards();
        this.draw = copy.getDrawPile();
        return copy;

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

        removeCard = new Card();
        if (card.isEqual(drawCard)) 
        {

            removeCard = this.getCardAtReference(card);
            drawCard = getNextDrawCard();
            removeCard.getFrontImage().setVisible(false);
            draw.remove(removeCard);
            if (drawIndex == 0) 
            {
                for (Card x : draw) 
                {
                    x.getFrontImage().setVisible(true);
                }
                drawCard = draw.get(draw.size() - 1);
            }

        } 
        else 
        {

            removeCard = this.getCardAtReference(card);
            removeCard.getFrontImage().setVisible(false);
            pyramid.set(pyramidIndex, removeCard);
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
                if (pyramid.get(i).isEqual(card)) {
                    output = pyramid.get(i);
                    pyramidIndex = i;
                    i = pyramid.size() + 1;
                }
            }
        }
        return output;

    }

    /**
     * Gets the next card in the draw pile
     * @return a Card that is the next Card in the draw pile
     */
    public Card getNextDrawCard() 
    {
        int cardIndex = 0;
        for (int i = 0; i < draw.size(); i++) 
        {
            if (draw.get(i).isEqual(drawCard)) 
            {
                cardIndex = i;
                i = draw.size() + 1;
            }
        }
        if (cardIndex == 0) 
        {
            for (int i = 0; i < draw.size(); i++) 
            {
                draw.get(i).getFrontImage().setVisible(true);
            }
            drawCard = draw.get(draw.size() - 1);
            return drawCard;
        }
        else 
        {
            drawCard.getFrontImage().setVisible(false);
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
        int index = 0;
        int i = 0;

        if (card.isEqual(drawCard)) 
        {
            isExposed = true;
        }

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

        else 
        {
            for (int j = 0; j < pyramid.size() - 1; j++) 
            {
                if (!pyramid.get(j).getFrontImage().isVisible() && !pyramid.get(j + 1).getFrontImage().isVisible()) 
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
     * Determines if the game is over or not so game ending screen can be shown
     * @return a boolean that determines if game is over
     */
    public boolean isGameOver() 
    {
        for (Card x : pyramid) 
        {
            if (x.getFrontImage().isVisible()) 
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the current score of the game
     * @return a int that is the current score
     */
    public int getScore() 
    {
        return score;
    }

    /**
     * Gets the current draw card in the draw pile
     * @return a card that is the current draw card in the draw pile
     */
    public Card getDrawCard() 
    {
        return drawCard;
    }

    /**
     * Supports the ability to undo a move for the game
     */
    public void undoMove() 
    {
        if (undo.size() > 0) 
        {
            int i = undo.size() - 1;
            if (undo.size() > 1 && (undo.get(i).isEqual(undo.get(i - 1)))) 
            {
                drawCard = undo.get(i);
                drawCard.getFrontImage().setVisible(true);
                undo.remove(i);
                undo.remove(i - 1);

            }

            else if (undo.get(i).getRank() == 13 && !pyramid.contains(undo.get(i))) 
            {
                drawCard = undo.get(i);
                drawCard.getFrontImage().setVisible(true);
                this.draw.add(drawIndex + 1, drawCard);
                undo.remove(i);
                score = decreaseScore();

            }

            else if (undo.get(i).getRank() == 13 && pyramid.contains(undo.get(i))) 
            {

                undo.get(i).getFrontImage().setVisible(true);
                undo.remove(i);
                score = decreaseScore();

            }

            else if (undo.size() >= 1 && (!undo.get(i).getFrontImage().isVisible())) 
            {

                if (isMoveValid(undo.get(i), undo.get(i - 1))) 
                {
                    if (pyramid.contains(undo.get(i)) && pyramid.contains(undo.get(i - 1))) 
                    {
                        undo.get(i).getFrontImage().setVisible(true);
                        undo.get(i - 1).getFrontImage().setVisible(true);
                        undo.remove(i);
                        undo.remove(i - 1);
                        score = decreaseScore();
                    } 
                    else if (pyramid.contains(undo.get(i)) && !pyramid.contains(undo.get(i - 1))) 
                    {
                        drawCard = undo.get(i - 1);
                        drawCard.getFrontImage().setVisible(true);
                        this.draw.add(drawIndex + 1, drawCard);
                        undo.get(i).getFrontImage().setVisible(true);
                        undo.get(i - 1).getFrontImage().setVisible(true);
                        undo.remove(i);
                        undo.remove(i - 1);
                        score = decreaseScore();
                    } 
                    else 
                    {
                        drawCard = undo.get(i);
                        drawCard.getFrontImage().setVisible(true);
                        this.draw.add(drawIndex + 1, drawCard);
                        undo.get(i).getFrontImage().setVisible(true);
                        undo.get(i - 1).getFrontImage().setVisible(true);
                        undo.remove(i);
                        undo.remove(i - 1);
                        score = decreaseScore();

                    }
                }
            }
        }

        else 
        {
            for (Card x : draw) 
            {
                x.getFrontImage().setVisible(true);
            }
            drawCard = draw.get(draw.size() - 1);
        }

    }

    /**
     * Decreases the score after a move has been undone
     * @return a int that is the new score after a move has been undone
     */
    public int decreaseScore() 
    {
        score = score - 13;
        return score;
    }

    /**
     * Starts a new game with a new Deck
     * @return a Deck that is to be used for the new game
     */
    public Deck newGameDeck() 
    {
        Deck newGame = new Deck();
        Deck gameDeck = this.dealCards(newGame, true);
        this.pyramid = gameDeck.getPyramidCards();
        this.draw = gameDeck.getDrawPile();
        score = 0;
        undo.clear();
        return gameDeck;
    }

}
