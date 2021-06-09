
/**	File: Pyramid.java
 * Constructs the application used to play a game of Pyramid Solitaire
 * @author Brandy Washington
 * 
**/

import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


/**	The class Pyramid creates a game of Pyramid Solitiare
 */
public class Pyramid extends Application 
{ 

    private PyramidPlayGUI game;
    private Deck deck, start;
    private Button undoButton = new Button("Undo");
    private Button newGameButton = new Button("New Game");
    private Button quitGame = new Button("Quit");
    private static final String TITLE = "Pyramid Solitaire";
    private static final int WIDTH = 1000, HEIGHT = 750;
    private BorderPane root;
    private Group quit = new Group();
    private Group startNew = new Group();
    private Stage window;
    private Text setScore;
    private Scene scene1;
    private HBox scoreBoard = new HBox();
    private Group undo = new Group();
    private Group pyramid = new Group();
    private GridPane row1 = new GridPane();
    private GridPane row2 = new GridPane();
    private GridPane row3 = new GridPane();
    private GridPane row4 = new GridPane();
    private GridPane row5 = new GridPane();
    private GridPane row6 = new GridPane();
    private GridPane row7 = new GridPane();
    private ArrayList<Card> drawCards;
    private GridPane bottomPane = new GridPane();
    private static final int initialCardX = 455;
    private static final int initialCardY = 250;

    /*
    * Constructor creates a new Pyramid Object wihich intializes a new Deck, a new PyramidPlayGUI object
    and a deck called start that will be used for the game
    */
    public Pyramid()
    {
        deck = new Deck();
        game = new PyramidPlayGUI(deck);
        start = game.startGame();
    }
   
        @Override 
        /**	Customize Java's start method for a game of Pyramid
        @param stage a Stage object that Java provides
        */
        public void start( Stage stage ) 
        {
            window = stage;
            Label label1= new Label("WELCOME TO PYRAMID! CLICK THE START BUTTON TO START.");
            label1.setStyle("-fx-font-size: 14;");
            Button startGameButton = new Button ("Start Game");
            
            //Sets action for button to create game screen
            startGameButton.setOnAction(event -> {
                setBoard(start);
                addMouseActionPyramid();
                setQuitButton();
                setNewGameButton();
                setUndoButton();
                setNewGameButton();

                //adds music to the game
                String path = "MoonChild Cure.mp3";
                Media media = new Media(new File(path).toURI().toString());   
                MediaPlayer mediaPlayer = new MediaPlayer(media);    
                mediaPlayer.setAutoPlay(true); 
                mediaPlayer.setOnEndOfMedia(new Runnable() {
                    public void run() {
                      mediaPlayer.seek(Duration.ZERO);
                    }
                });
                
                window.setScene(new Scene(root, WIDTH, HEIGHT));
            });

            //Start Screen with Start Game Button
            VBox box1 = new VBox(20);
            box1.setAlignment(Pos.CENTER);
            box1.setStyle("-fx-background-color: green;");
            box1.getChildren().addAll(label1, startGameButton);
            
            scene1 = new Scene(box1, WIDTH, HEIGHT);
            

            // 	Set title for the Stage.
            window.setTitle( TITLE ); 
             
            // 	Add scene to the stage.
            window.setScene(scene1);
    
            // 	Display the contents of the stage.
            window.show();         
        } 
       
        /**
        Define the unnecessary main() method that launches application.
        */
       public static void main( String args [] ) { 
            launch( args ); 
        } 

        /**
         * Sets the board of the game like the draw pile, the pyramid foundation,
         * the buttons, and the score
         * @param newDeck deck used to set the board for the game
        */
        private void setBoard(Deck newDeck)
        {
            root = new BorderPane();

            bottomPane.setLayoutX(50);
            bottomPane.setLayoutY(250);

            root.setStyle("-fx-background-color: green;");
            root.setPrefSize(WIDTH, HEIGHT);
            row1.setLayoutX(initialCardX);
            row1.setLayoutY(initialCardY);
            row2.setLayoutX(initialCardX - 35);
            row2.setLayoutY(initialCardY + 40);
            row3.setLayoutX(initialCardX - 70);
            row3.setLayoutY(initialCardY + 80);
            row4.setLayoutX(initialCardX - 105);
            row4.setLayoutY(initialCardY + 120);
            row5.setLayoutX(initialCardX - 140);
            row5.setLayoutY(initialCardY + 160);
            row6.setLayoutX(initialCardX - 175);
            row6.setLayoutY(initialCardY + 200);
            row7.setLayoutX(initialCardX - 210);
            row7.setLayoutY(initialCardY + 240);
                        
            setScore = new Text("SCORE:" + game.getScore());
            setScore.setStyle("-fx-font-size: 14;");
            scoreBoard.getChildren().add(setScore);
            scoreBoard.setLayoutX(465);
            scoreBoard.setLayoutY(50);

            quit.setLayoutX(900);
            quit.setLayoutY(720);
            quit.getChildren().add(quitGame);
            
            startNew.setLayoutX(810);
            startNew.setLayoutY(720);
            startNew.getChildren().add(newGameButton);

            undo.setLayoutX(745);
            undo.setLayoutY(720);
            undo.getChildren().add(undoButton);


        //start adding cards to the respective Hboxs to create card Pyramid
        drawCards = newDeck.getDrawPile();
        for(Card draw: drawCards)
        {
            bottomPane.getChildren().add(draw);
        }
        int index = 0;
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j <= i; j++)
            {
                if(i == 0)
                {
                    Card card = newDeck.getPyramidCards().get(index);
                    row1.getChildren().add(j,card);
                    index++;
                }
                if(i == 1)
                {
                    Card card = newDeck.getPyramidCards().get(index); 
                    row2.addColumn(j,card);
                    index++;
                }
                if(i == 2)
                {
                    Card card = newDeck.getPyramidCards().get(index); 
                    row3.addColumn(j,card);
                    index++;
                }
                if(i == 3)
                {
                    Card card = newDeck.getPyramidCards().get(index); 
                    row4.addColumn(j,card);
                    index++;
                }
                if(i == 4)
                {
                    Card card = newDeck.getPyramidCards().get(index); 
                    row5.addColumn(j,card);
                    index++;
                }
                if(i == 5)
                {
                    Card card = newDeck.getPyramidCards().get(index); 
                    row6.addColumn(j,card);
                    index++;
                }
                if(i == 6)
                {
                    Card card = newDeck.getPyramidCards().get(index); 
                    row7.addColumn(j,card);
                    index++;
                }
            }
        }
        pyramid.getChildren().addAll(row1, row2, row3, row4, row5, row6, row7);
        root.getChildren().addAll(pyramid, bottomPane, scoreBoard, quit, startNew, undo);
        }

        /**
         * Sets the actions for the quit button for the game. When clicked, game exits
        */
        private void setQuitButton()
        {
            quitGame.setOnAction(event ->{
                Platform.exit();
                System.exit(0);
                event.consume();
            }
            );
        }

        /**
         * Sets the actions for the undo button for the game. When clicked, 
         * a move is undone
        */
        private void setUndoButton()
        {
            undoButton.setOnAction(event ->{
                game.undoMove();
                event.consume();
                setScore = new Text ("SCORE:" + game.getScore());
                scoreBoard.getChildren().set(0, setScore);
                
            }
            );
        }

        /**
         * Sets the actions for the new game button for the game. When clicked, 
         * a new game is created
        */
        private void setNewGameButton()
        {
            newGameButton.setOnAction(event ->{
            Deck newGame = game.newGameDeck();
            root = new BorderPane();
            bottomPane = new GridPane();
            row1 = new GridPane();
            row2 = new GridPane();
            row3 = new GridPane();
            row4 = new GridPane();
            row5 = new GridPane();
            row6 = new GridPane();
            row7 = new GridPane();
            scoreBoard = new HBox();
            startNew = new Group();
            pyramid = new Group();
            quit = new Group();
            undo = new Group();
            setBoard(newGame);
            addMouseActionPyramid();
            setQuitButton();
            setNewGameButton();
            setUndoButton();
            setNewGameButton();
            window.setScene(new Scene(root, WIDTH, HEIGHT));
            event.consume();
          
            
        });
        }

        /**
         * Sets the mouse actions for the cards in the game.
        */
        private void addMouseActionPyramid()
        {
            for(Node card_in_row: row7.getChildren())
            {
                card_in_row.setOnMouseClicked(event ->{ 

                
                    Node card = (Node) event.getSource();
                    if(card instanceof Card)
                    {   
                        Card input = (Card) card;
                        if(input.getFrontImage().isVisible())
                        {
                            game.selectCard(input);
                        }
                    }
                    setScore = new Text ("SCORE:" + game.getScore());
                    scoreBoard.getChildren().set(0, setScore);
                    event.consume();
                });
                
            } 

            for(Node card_in_row: row6.getChildren())
            {
                card_in_row.setOnMouseClicked(event ->{ 

                
                    Node card = (Node) event.getSource();
                    if(card instanceof Card)
                    {   
                        Card input = (Card) card;
                        if(input.getFrontImage().isVisible())
                        {
                            game.selectCard(input);
                        }
                    }
                    setScore = new Text ("SCORE:" + game.getScore());
                    scoreBoard.getChildren().set(0, setScore);
                    event.consume();
                });
                
            } 
            for(Node card_in_row: row5.getChildren())
            {
                card_in_row.setOnMouseClicked(event ->{ 

                
                    Node card = (Node) event.getSource();
                    if(card instanceof Card)
                    {   
                        Card input = (Card) card;
                        if(input.getFrontImage().isVisible())
                        {
                            game.selectCard(input);
                        }
                    }
                    setScore = new Text ("SCORE:" + game.getScore());
                    scoreBoard.getChildren().set(0, setScore);
                    event.consume();
                });
                
            } 
            for(Node card_in_row: row4.getChildren())
            {
                card_in_row.setOnMouseClicked(event ->{ 

                
                    Node card = (Node) event.getSource();
                    if(card instanceof Card)
                    {   
                        Card input = (Card) card;
                        if(input.getFrontImage().isVisible())
                        {
                            game.selectCard(input);
                        }
                    }
                    setScore = new Text ("SCORE:" + game.getScore());
                    scoreBoard.getChildren().set(0, setScore);
                    event.consume();
                });
                
            } 
            for(Node card_in_row: row3.getChildren())
            {
                card_in_row.setOnMouseClicked(event ->{ 

                
                    Node card = (Node) event.getSource();
                    if(card instanceof Card)
                    {   
                        Card input = (Card) card;
                        if(input.getFrontImage().isVisible())
                        {
                            game.selectCard(input);
                        }
                    }
                    setScore = new Text ("SCORE:" + game.getScore());
                    scoreBoard.getChildren().set(0, setScore);
                    event.consume();
                });
                
            } 
            for(Node card_in_row: row2.getChildren())
            {
                card_in_row.setOnMouseClicked(event ->{ 

                
                    Node card = (Node) event.getSource();
                    if(card instanceof Card)
                    {   
                        Card input = (Card) card;
                        if(input.getFrontImage().isVisible())
                        {
                            game.selectCard(input);
                        }
                    }
                    setScore = new Text ("SCORE:" + game.getScore());
                    scoreBoard.getChildren().set(0, setScore);
                    event.consume();
                });
                
            } 
            for(Node card_in_row: row1.getChildren())
            {
                card_in_row.setOnMouseClicked(event ->{ 

                
                    Node card = (Node) event.getSource();
                    if(card instanceof Card)
                    {   
                        Card input = (Card) card;
                        if(input.getFrontImage().isVisible())
                        {
                            game.selectCard(input);
                        }
                    }
                    setScore = new Text ("SCORE:" + game.getScore());
                    scoreBoard.getChildren().set(0, setScore);
                    GameOver();
                    event.consume();
                    
                });
                
            } 
            for(Node card_in_row: bottomPane.getChildren())
            {
                card_in_row.setOnMouseClicked(event ->{ 

                
                    Node card = (Node) event.getSource();
                    if(card instanceof Card)
                    {   
                        Card input = (Card) card;
                        if(input.getFrontImage().isVisible())
                        {
                            game.selectCard(input);
                        }
                        else
                        {
                            input = game.getDrawCard();
                            game.selectCard(input);
                        }
                    }
                    setScore = new Text ("SCORE:" + game.getScore());
                    scoreBoard.getChildren().set(0, setScore);
                    event.consume();
                });
                
            } 
        }

        /**
         * Sees if the game is over or not, if so, it shows a new screen that congratulates
         * the player and gives them the option to quit or start a new game.
        */
        private void GameOver()
        {
            if(game.isGameOver())
            {
                VBox box = new VBox(20);
                Label gameOver = new Label("CONGRATULATIONS! YOU HAVE WON!");
                box.setAlignment(Pos.CENTER);
                Button newButton = new Button("New Game");

                newButton.setOnAction(event ->{
                    Deck newGame = game.newGameDeck();
                    root = new BorderPane();
                    bottomPane = new GridPane();
                    row1 = new GridPane();
                    row2 = new GridPane();
                    row3 = new GridPane();
                    row4 = new GridPane();
                    row5 = new GridPane();
                    row6 = new GridPane();
                    row7 = new GridPane();
                    scoreBoard = new HBox();
                    startNew = new Group();
                    pyramid = new Group();
                    quit = new Group();
                    undo = new Group();
                    setBoard(newGame);
                    addMouseActionPyramid();
                    setQuitButton();
                    setNewGameButton();
                    setUndoButton();
                    setNewGameButton();
                    window.setScene(new Scene(root, WIDTH, HEIGHT));
                    event.consume();
                });

                Button end = new Button("Quit");
                end.setOnAction(event ->{
                    Platform.exit();
                    System.exit(0);
                    event.consume();
                }
                );
                

                box.setStyle("-fx-background-color: green;");
                box.getChildren().addAll(gameOver,newButton,end);
                window.setScene(new Scene(box,WIDTH,HEIGHT));
            }
        }
}
   
