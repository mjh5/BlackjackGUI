import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class BlackjackGUI extends Application implements EventHandler<ActionEvent> {

    //Map containing all the card Images
    HashMap<String, Image> cardImages;

    //Objects that make up the GUI
    Stage window;
    Scene scene;
    HBox dealerHand;
    HBox playerHand;
    HBox hitOrStandBox;
    VBox betBox;
    BorderPane layout;
    ImageView dealerCard1;
    ImageView dealerCard2;
    ImageView dealerCard3;
    ImageView dealerCard4;
    ImageView dealerCard5;
    ImageView playerCard1;
    ImageView playerCard2;
    ImageView playerCard3;
    ImageView playerCard4;
    ImageView playerCard5;
    TextArea gameText;
    TextField betEntryField;
    Button betButton;
    Button hitButton;
    Button standButton;

    //The game engine object to control the game
    BlackjackGameEngine gameEngine;


    /**
     * The start method is an abstract method inherited from the Application class. This is essentially your main method
     * for the application. Inside you build your window out of different GUI nodes, such as buttons, menus, text,
     * images, and much more.
     *
     * @param primaryStage - Stage passed in when the application is run
     */
    @Override
    public void start(Stage primaryStage) {
        //Sets the window to the primaryStage and sets the title
        window = primaryStage;
        window.setTitle("Blackjack");

        loadCardImages();
        loadWelcomeScreenValues();

        betButton.setOnAction(this);
        hitButton.setOnAction(this);
        standButton.setOnAction(this);

        //Builds sublayouts for the children nodes to be organized in
        dealerHand = new HBox();
        dealerHand.getChildren().addAll(dealerCard1, dealerCard2);

        playerHand = new HBox();
        playerHand.getChildren().addAll(playerCard1, playerCard2);

        hitOrStandBox = new HBox();
        hitOrStandBox.getChildren().addAll(hitButton, standButton);

        betBox = new VBox();
        betBox.getChildren().addAll(gameText, betEntryField, betButton);

        //Adds the sublayouts to the main GUI layout
        layout = new BorderPane();
        layout.setLeft(dealerHand);
        layout.setRight(playerHand);
        layout.setBottom(hitOrStandBox);
        layout.setCenter(betBox);

        scene = new Scene(layout, 600, 250);
        //TODO: Set background color of scene to dark green

        window.setScene(scene);
        window.show();

        gameEngine = new BlackjackGameEngine();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == betButton) {
            if (betButton.getText().equals("Play Blackjack!")) {
                betButton.setText("Bet");
                gameText.setText("Your balance is " + gameEngine.getPlayerBalance() + ". Please enter your bet below.");
                betEntryField.setVisible(true);
            } else {
                try {
                    gameEngine.init();

                    int playerBet = Integer.parseInt(betEntryField.getText());
                    gameEngine.playRound(playerBet);

                    loadDealerHand();
                    loadPlayerHand();
                } catch (NumberFormatException e) {
                    gameText.setText("Please enter an numeric bet less than your balance." +
                            " Your balance is " + gameEngine.getPlayerBalance());
                }
            }
        }
    }

    /**
     * This is a helper method that loads all the card GIf files from the cards directory into a HashMap of Image
     * objects, which can be easily accessed and displayed into the ImageView object displayed in the main GUI
     */
    private void loadCardImages() {
        String pathToCards = "C:\\Users\\micha\\CodeForDad\\BlackjackGUI\\cards\\";
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};

        cardImages = new HashMap<>();

        //load back of card
        File cardFile = new File("C:\\Users\\micha\\CodeForDad\\BlackjackGUI\\cards\\back1.GIF");
        Image backOfCard = new Image(cardFile.toURI().toString());
        cardImages.put("back", backOfCard);

        //load other card images
        for (String cardRank : rank) {
            for (String suit : suits) {
                cardFile = new File(pathToCards + cardRank + suit + ".GIF");
                Image cardToLoad = new Image(cardFile.toURI().toString());
                String cardKey = cardRank.substring(0, 1).toUpperCase() + suit.substring(0, 1).toUpperCase();
                cardImages.put(cardKey, cardToLoad);
            }
        }
    }

    /**
     * This helper method sets all the values of the GUI nodes to the initial welcome screen values
     */
    private void loadWelcomeScreenValues() {
        //Initializing the displayed card images to the back of the card
        dealerCard1 = new ImageView(cardImages.get("back"));
        dealerCard2 = new ImageView(cardImages.get("back"));
        dealerCard3 = new ImageView(cardImages.get("back"));
        dealerCard4 = new ImageView(cardImages.get("back"));
        dealerCard5 = new ImageView(cardImages.get("back"));

        playerCard1 = new ImageView(cardImages.get("back"));
        playerCard2 = new ImageView(cardImages.get("back"));
        playerCard3 = new ImageView(cardImages.get("back"));
        playerCard4 = new ImageView(cardImages.get("back"));
        playerCard5 = new ImageView(cardImages.get("back"));

        //set the extra card slots to not visible
        dealerCard3.setVisible(false);
        dealerCard4.setVisible(false);
        dealerCard5.setVisible(false);

        playerCard3.setVisible(false);
        playerCard4.setVisible(false);
        playerCard5.setVisible(false);

        //Initialize buttons with text values
        hitButton = new Button("Hit");
        standButton = new Button("Stand");
        betButton = new Button("Play Blackjack!");

        gameText = new TextArea("Welcome to Blackjack!");

        betEntryField = new TextField();
        betEntryField.setVisible(false);
    }

    public void loadPlayerHand() {
        //Gets the array of card objects that make up the hand
        ArrayList<Card> cardsInHand = gameEngine.getPlayerHand().getaHand();
        System.out.print(cardsInHand.toString());

        //Loads cards into the image view objects displayed in the GUI
        //The 3 if statements at the end are for the cases where the player hits,
        // in which case his hand is larger than 2 cards
        playerCard1 = new ImageView(cardImages.get(cardsInHand.get(0).toString()));
        playerCard2 = new ImageView(cardImages.get(cardsInHand.get(1).toString()));
        if (cardsInHand.size() > 2) {
            playerCard3 = new ImageView(cardImages.get(cardsInHand.get(2).toString()));
            playerCard3.setVisible(true);
            playerHand.getChildren().add(playerCard3);
        } else {
            playerCard3.setVisible(false);
            playerHand.getChildren().remove(playerCard3);
        }

        if (cardsInHand.size() > 3) {
            playerCard4 = new ImageView(cardImages.get(cardsInHand.get(3).toString()));
            playerCard4.setVisible(true);
            playerHand.getChildren().add(playerCard4);
        } else {
            playerCard4.setVisible(false);
            playerHand.getChildren().remove(playerCard4);
        }

        if (cardsInHand.size() > 4) {
            playerCard5 = new ImageView(cardImages.get(cardsInHand.get(4).toString()));
            playerCard5.setVisible(true);
            playerHand.getChildren().add(playerCard5);
        } else {
            playerCard5.setVisible(false);
            playerHand.getChildren().remove(playerCard5);
        }
    }

    public void loadDealerHand() {
        //Gets the array of card objects that make up the hand
        ArrayList<Card> cardsInHand = gameEngine.getDealerHand().getaHand();

        //Loads cards into the image view objects displayed in the GUI
        //The 3 if statements at the end are for the cases where the player hits,
        // in which case his hand is larger than 2 cards
        dealerCard1 = new ImageView(cardImages.get(cardsInHand.get(0).toString()));
        dealerCard2 = new ImageView(cardImages.get(cardsInHand.get(1).toString()));
        if (cardsInHand.size() > 2) {
            dealerCard3 = new ImageView(cardImages.get(cardsInHand.get(2).toString()));
            dealerCard3.setVisible(true);
            dealerHand.getChildren().add(dealerCard3);
        } else {
            dealerCard3.setVisible(false);
            dealerHand.getChildren().remove(dealerCard3);
        }

        if (cardsInHand.size() > 3) {
            dealerCard4 = new ImageView(cardImages.get(cardsInHand.get(3).toString()));
            dealerCard4.setVisible(true);
            dealerHand.getChildren().remove(dealerCard4);
        } else {
            dealerCard4.setVisible(false);
            dealerHand.getChildren().remove(dealerCard4);
        }

        if (cardsInHand.size() > 4) {
            dealerCard5 = new ImageView(cardImages.get(cardsInHand.get(4).toString()));
            dealerCard5.setVisible(true);
            dealerHand.getChildren().add(dealerCard5);
        } else {
            dealerCard5.setVisible(false);
            dealerHand.getChildren().remove(dealerCard5);
        }
    }

    public static void main(String[] args) {
        //To run a JavaFX application, you call the launch method and pass in args
        launch(args);
    }
}
