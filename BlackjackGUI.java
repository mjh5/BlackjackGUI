import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class BlackjackGUI extends Application {

    //Objects that make up the GUI
    Stage window;
    Scene scene;
    Button test_button;


    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Blackjack");
        test_button = new Button("Test Button");

        StackPane layout = new StackPane();
        layout.getChildren().add(test_button);

        scene = new Scene(layout, 300, 200);

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
