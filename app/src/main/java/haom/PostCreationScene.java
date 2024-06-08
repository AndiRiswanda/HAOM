package haom;

import haom.MainScene.BaseScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PostCreationScene extends BaseScene {

    private ObservableList<String> genresList;
    public String ImagePath;

    public PostCreationScene(Stage stage, String username) {
        super(stage, username);
        this.genresList = FXCollections.observableArrayList("Bantuan Kecil", "Kebutuhan\nKomunitas", "Masalah Pribadi"); // tambahkan genre disini
    }

    @Override
    public void show() {
        Label titleLabel = new Label("Create a Post");
        titleLabel.setStyle("-fx-font-size: 40px; -fx-padding: 25px; -fx-text-fill: #6a0dad;");

        ComboBox<String> genreComboBox = new ComboBox<>(genresList);
        genreComboBox.setPromptText("Select Genre");

        TextField titleField = new TextField();
        titleField.setPromptText("Enter title");

        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Enter description");

        Image defaultImage = new Image(getClass().getResource("/PicAsset/userProfile.png").toString());
        ImageView imageView = new ImageView(defaultImage);
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);

        Button uploadButton = new Button("Upload Image");
        uploadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);
                ImagePath = selectedFile.toURI().toString();  // Ensure correct format
            }
        });

        Button postButton = new Button("Post");
        postButton.setStyle("-fx-background-color: #6a0dad; -fx-text-fill: white;");
        postButton.setOnAction(e -> {
            PostInfo post = new PostInfo(
                titleField.getText(),
                genreComboBox.getValue(),
                descriptionArea.getText(),
                ImagePath,
                0 // Initial help points
            );
            PostDisplayScene.addPost(post);
            // clear
            genreComboBox.setValue(null);
            titleField.clear();
            descriptionArea.clear();
            imageView.setImage(null);
        });

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #6a0dad; -fx-text-fill: white;");
        backButton.setOnAction(e -> {
            try {
                MainScene.showMainScreen(stage, username);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, genreComboBox, titleField, descriptionArea, imageView, uploadButton, postButton, backButton);

        layout.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(layout);

        Scene scene = new Scene(root, 940, 630);
        scene.getStylesheets().add(getClass().getResource("/PostStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}

