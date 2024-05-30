package haom;

import config.DatabaseConnection;
import controller.UserController;
import haom.MainScene.BaseScene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelpScene extends BaseScene {

    public HelpScene(Stage stage, String username) {
        super(stage, username);
    }

    @Override
    public void show() {
        Label helpLabel = new Label("Want to Help?");
        helpLabel.setStyle("-fx-font-size: 24px; -fx-padding: 25px;");

        Button helpButton = new Button("Help");
        helpButton.setStyle("-fx-background-color: #6a0dad; -fx-text-fill: white;");
        helpButton.setOnAction(e -> {
            // Add your help functionality here
            UserController.incrementHaomicPoints(username);
            System.out.println("Help button clicked! Haomic point added.");
            showAlert("Success", "1 Haomic point has been added to your account.");
        });

        PostDisplayScene postDisplayScene = new PostDisplayScene(stage, username);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #6a0dad; -fx-text-fill: white;");
        backButton.setOnAction(e -> postDisplayScene.show());


        VBox layout = new VBox(20);
        layout.getChildren().addAll(helpLabel, helpButton, backButton);
        layout.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(layout);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


