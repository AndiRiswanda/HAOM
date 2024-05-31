package haom;

import java.io.File;
import java.util.Map;

import controller.UserController;
import haom.MainScene.BaseScene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

// ProfileScene class
public class ProfileScene extends BaseScene {

    private String username;

    public ProfileScene(Stage stage, String username) {
        super(stage, username);
        this.username = username;
    }

    @Override
    public void show() {

        Map<String, String> userDetails = UserController.getUserDetails(username);
        String email = userDetails.get("email");
        String haomicPoints = userDetails.get("haomicpoint");


        Label titleLabel = new Label(username);
        titleLabel.setId("profile-title-label");
    
        // Get user-specific profile image path from the database
        String userImagePath = UserController.getProfileImagePath(username);
    
        Image profileImage = ImageLoader.loadProfileImage(userImagePath);
        ImageView profileImageView = new ImageView(profileImage);
        profileImageView.setFitHeight(100);
        profileImageView.setFitWidth(100);
        profileImageView.setPreserveRatio(false);
        profileImageView.setId("profile-image-view");

        Button changeImageButton = new Button("Change Profile");
        changeImageButton.setPrefHeight(30);
        changeImageButton.setPrefWidth(70);
        changeImageButton.setId("change-image-button");
        changeImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters()
                    .add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                // Define user-specific path to save the image
                String newImagePath = "C:/Path/To/PicAsset/" + username + "_profile.png";
                // Save the new profile image
                ImageLoader.saveProfileImage(selectedFile, newImagePath);
                // Update the image path in the database
                UserController.setProfileImagePath(username, newImagePath);
                // Reload the profile image
                profileImageView.setImage(ImageLoader.loadProfileImage(newImagePath));
            }
        });

        Button deleteImageButton = new Button("Delete Profile");
        deleteImageButton.setPrefHeight(30);
        deleteImageButton.setPrefWidth(70);
        deleteImageButton.setId("delete-image-button");
        deleteImageButton.setOnAction(e -> {
        // Define default image path or null if there's no default image
            String defaultImagePath = "D:/HAOMGIT/HAOM/app/src/main/resources/PicAsset/userProfile.png";
            UserController.setProfileImagePath(username, defaultImagePath);
            profileImageView.setImage(ImageLoader.loadProfileImage(defaultImagePath));
        });

        Button backButton = new Button("Back");
        backButton.setId("back-button");
        backButton.setOnAction(e -> {
            try {
                MainScene.showMainScreen(stage, username);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Circle circle = new Circle(120, Color.web("#424242"));
        circle.setStyle("-fx-translate-y: 50;");

        Rectangle rectangle = new Rectangle(350, 90);
        rectangle.setStroke(Color.web("#6a0dad5b"));
        rectangle.setFill(Color.web("#424242"));
        rectangle.setStyle("-fx-translate-y: 90;");

        Label emailLabel = new Label("Email : " + email);
        emailLabel.setId("email-label");
        
        Label pointsLabel = new Label("Haomic Points  : " + haomicPoints);
        pointsLabel.setId("points-label");

        StackPane stackPane = new StackPane();
        stackPane.setId("profile-scene-root");
        stackPane.getChildren().addAll(circle, rectangle, emailLabel, pointsLabel, titleLabel, profileImageView, changeImageButton, backButton, deleteImageButton);
        StackPane.setAlignment(circle, Pos.TOP_CENTER);
        StackPane.setAlignment(rectangle, Pos.CENTER);
        StackPane.setAlignment(emailLabel, Pos.CENTER);
        StackPane.setAlignment(pointsLabel, Pos.CENTER);
        StackPane.setAlignment(titleLabel, Pos.TOP_CENTER);
        StackPane.setAlignment(profileImageView, Pos.TOP_CENTER);
        StackPane.setAlignment(changeImageButton, Pos.CENTER);
        StackPane.setAlignment(backButton, Pos.TOP_RIGHT);
        StackPane.setAlignment(deleteImageButton, Pos.CENTER);

        Circle clip = new Circle(50, 50, 50); // Menggunakan radius yang sesuai dengan ukuran ImageView
        profileImageView.setClip(clip);

        

        Scene scene = new Scene(stackPane, 800, 600);
        scene.getStylesheets().add(MainScene.class.getResource("/profileStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

} 
