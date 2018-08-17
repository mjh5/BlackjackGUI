import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class BlackjackGUI extends Application {

    //Map containing all the card Images
    HashMap<String, Image> cardImages;

    //Objects that make up the GUI
    Stage window;
    Scene scene;
    ImageView dealerCard1;
    ImageView dealerCard2;
    ImageView playerCard1;
    ImageView playerCard2;
    TextArea gameText;
    TextField betEntryField;
    Button betButton;
    Button hitButton;
    Button standButton;


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
        welcomeScreenValues();

        HBox dealerHand = new HBox();
        dealerHand.getChildren().addAll(dealerCard1, dealerCard2);

        HBox playerHand = new HBox();
        playerHand.getChildren().addAll(playerCard1, playerCard2);
/*
        HBox hitOrStandBox = new HBox();
        hitOrStandBox.getChildren().addAll(hitButton, standButton);

        VBox betBox = new VBox();
        betBox.getChildren().addAll(gameText, betEntryField, betButton);
*/
        BorderPane layout = new BorderPane();
        layout.setLeft(dealerHand);
        layout.setRight(playerHand);
        //layout.setBottom(hitOrStandBox);
        //layout.setCenter(betBox);

        scene = new Scene(layout, 300, 200);

        window.setScene(scene);
        window.show();
    }

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
        for (String cardRank: rank) {
            for (String suit: suits) {
                cardFile = new File(pathToCards + cardRank + suit + ".GIF");
                Image cardToLoad = new Image(cardFile.toURI().toString());
                String cardKey = cardRank.substring(0,1).toUpperCase() + suit.substring(0,1).toUpperCase();
                cardImages.put(cardKey, cardToLoad);
            }
        }
    }

    private void welcomeScreenValues() {
        dealerCard1 = new ImageView(cardImages.get("back"));
        dealerCard2 = new ImageView(cardImages.get("back"));
        playerCard1 = new ImageView(cardImages.get("back"));
        playerCard2 = new ImageView(cardImages.get("back"));
    }

    public static void main(String[] args) {
        //To run a JavaFX application, you call the launch method and pass in args
        launch(args);
    }
}
