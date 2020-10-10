import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class JTMessenger extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane root = new FlowPane();

        root.setHgap(10);
        root.setVgap(20);
        root.setPadding(new Insets(15, 15, 15, 15));

        TextField textField = new TextField();
        textField.setPromptText("User name");
        textField.setPrefWidth(110);

        PasswordField passField = new PasswordField();
        passField.setPrefWidth(110);
        passField.setPromptText("User password");

        Button button = new Button("Login");
        Label label = new Label("Let's Go!");
        button.setOnAction(event ->  {

                label.setText("Accepted");
            });

        root.getChildren().add(textField);
        root.getChildren().add(passField);
        root.getChildren().addAll(button,label);

        Scene scene = new Scene(root, 400, 80);

        primaryStage.setTitle("J.Tea.M.essenger");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
