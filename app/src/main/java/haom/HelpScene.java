package haom;

import controller.UserController;
import haom.MainScene.BaseScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelpScene extends BaseScene {

    private String title;
    private String genre;
    private String description;
    private String picPath;

    public HelpScene(Stage stage, String username, String title, String genre, String description, String picPath) {
        super(stage, username);
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.picPath = picPath;
    }

    @Override
    public void show() {
        Label titleLabel = new Label(genre);
        titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-padding: 10px;");

        Label genreLabel = new Label("Genre: " + title);
        genreLabel.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");

        Label descriptionLabel = new Label(description);
        descriptionLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");

        ImageView imageView = new ImageView(new Image(picPath));
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-padding: 10px;");

        Button helpButton = new Button("Help");
        helpButton.setStyle("-fx-background-color: #6a0dad; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        helpButton.setOnAction(e -> {
            UserController.incrementHaomicPoints(username);
            System.out.println("Help button clicked! Haomic point added.");
            CustomAlert.showCustomAlert(AlertType.INFORMATION,"Success", "1 Haomic point has been added.");
        });

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #6a0dad; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        backButton.setOnAction(e -> new PostDisplayScene(stage, username).show());

        HBox buttonsBox = new HBox(20);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.getChildren().addAll(helpButton, backButton);

        VBox contentBox = new VBox(20);
        contentBox.setPadding(new Insets(20));
        contentBox.setAlignment(Pos.CENTER);
        contentBox.getChildren().addAll(titleLabel, genreLabel, imageView, descriptionLabel, buttonsBox);

        BorderPane root = new BorderPane();
        root.setCenter(contentBox);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
