import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class BlackjackGUI extends Application {

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

        //TODO: Write method to load images into the card ImageView's

        HBox dealerHand = new HBox();
        dealerHand.getChildren().addAll(dealerCard1, dealerCard2);

        HBox playerHand = new HBox();
        dealerHand.getChildren().addAll(playerCard1, playerCard2);

        HBox hitOrStandBox = new HBox();
        hitOrStandBox.getChildren().addAll(hitButton, standButton);

        VBox betBox = new VBox();
        betBox.getChildren().addAll(gameText, betEntryField, betButton);

        BorderPane layout = new BorderPane();
        layout.setLeft(dealerHand);
        layout.setRight(playerHand);
        layout.setBottom(hitOrStandBox);
        layout.setCenter(betBox);

        scene = new Scene(layout, 300, 200);

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        //To run a JavaFX application, you call the launch method and pass in args
        launch(args);
    }
}
