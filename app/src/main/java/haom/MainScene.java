package haom;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MainScene {

    public static void showMainScreen(Stage mainStage, String username) throws Exception {
        // Yellow panel
        StackPane panel = new StackPane();
        panel.setPadding(new Insets(10, 20, 10, 10));
        panel.setStyle(
                "-fx-background-color: #6a0dad; -fx-pref-height: 90px; -fx-background-radius: 5; -fx-border-radius: 5;");

        // Load profile image
        String userImagePath = ImageLoader.getUserImagePathFromDatabase(username);
        Image profileImage = ImageLoader.loadProfileImage(userImagePath);
        ImageView profileImageView = new ImageView(profileImage);
        profileImageView.setFitHeight(40);
        profileImageView.setFitWidth(40);

        // Create a circle to clip the ImageView
        Circle clip = new Circle(20, 20, 20);
        profileImageView.setClip(clip);

        StackPane.setAlignment(profileImageView, Pos.CENTER_RIGHT);

        // Event handler for profile image click
        profileImageView.setOnMouseClicked(e -> {
            try {
                new ProfileScene(mainStage, username).show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Label titleLabel = new Label("Welcome, " + username);
        titleLabel.getStyleClass().add("title-label");
        StackPane.setAlignment(titleLabel, Pos.CENTER_LEFT);

        panel.getChildren().addAll(titleLabel, profileImageView);

        // Teks di atas tombol
        Label actionLabel = new Label("What Would You Like to Do Today?");
        actionLabel
                .setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-font-family: Impact; -fx-text-fill: white;");

        Button btn1 = new Button("Make Challenge");
        btn1.setStyle("-fx-font-size: 17px; -fx-font-family: Impact; -fx-font-weight: bold; -fx-text-fill: white");
        Button btn2 = new Button("Find Challenge");
        btn2.setStyle("-fx-font-size: 17px; -fx-font-family: Impact; -fx-font-weight: bold; -fx-text-fill: white");
        Button btn3 = new Button("LeaderBoard");
        btn3.setStyle("-fx-font-size: 17px; -fx-font-family: Impact; -fx-font-weight: bold; -fx-text-fill: white");

        Button logoutButton = new Button("Logout");
        logoutButton.setId("logout-button");
        logoutButton.setOnAction(e -> {
            try {
                LoginScene.showLoginScreen(mainStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Set button sizes
        btn1.setPrefSize(200, 100);
        btn2.setPrefSize(200, 200);
        btn3.setPrefSize(200, 100);

        // Create instances of scenes to be reused
        PostDisplayScene postDisplayScene = new PostDisplayScene(mainStage, username);

        // Set button actions
        btn1.setOnAction(e -> new PostCreationScene(mainStage, username).show());
        btn2.setOnAction(e -> postDisplayScene.show());
        btn3.setOnAction(e -> {
            try {
                new LeaderBoard(mainStage, username).show();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(logoutButton);
        StackPane.setAlignment(logoutButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(logoutButton, new Insets(0, 0, -115, -35)); 
        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setVgap(20);
        buttonGrid.setHgap(20);
        buttonGrid.add(btn1, 0, 1);
        buttonGrid.add(btn2, 1, 1);
        buttonGrid.add(btn3, 2, 1);

        // VBox untuk teks dan tombol
        VBox actionBox = new VBox(10);
        actionBox.setAlignment(Pos.CENTER);
        actionBox.getChildren().addAll(actionLabel, buttonGrid, stackPane);

        // Main layout
        BorderPane root = new BorderPane();
        root.setId("main-border-root");
        root.setCenter(actionBox);
        BorderPane.setAlignment(panel, Pos.TOP_CENTER);
        root.setTop(new VBox(panel));

        // Scene setup
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(MainScene.class.getResource("/style.css").toExternalForm());

        mainStage.setTitle("Home Screen App");
        mainStage.setScene(scene);
        mainStage.show();
    }

    // Abstract base class for scenes
    abstract static class BaseScene {
        protected Stage stage;
        protected String username;

        public BaseScene(Stage stage, String username) {
            this.stage = stage;
            this.username = username;
        }

        public abstract void show() throws SQLException;
    }

    
    
}
