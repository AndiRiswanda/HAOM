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

        Circle clip = new Circle(20, 20, 20);
        profileImageView.setClip(clip);

        StackPane.setAlignment(profileImageView, Pos.CENTER_RIGHT);

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


        Label actionLabel = new Label("What Would You Like to Do Today?");
        actionLabel
                .setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-font-family: Impact; -fx-text-fill: white;");

        Button makeAChallengeButton = new Button("Make Challenge");
        makeAChallengeButton.setStyle("-fx-font-size: 17px; -fx-font-family: Impact; -fx-font-weight: bold; -fx-text-fill: white");
        Button findChallengeButton = new Button("Find Challenge");
        findChallengeButton.setStyle("-fx-font-size: 17px; -fx-font-family: Impact; -fx-font-weight: bold; -fx-text-fill: white");
        Button leaderboardButton = new Button("LeaderBoard");
        leaderboardButton.setStyle("-fx-font-size: 17px; -fx-font-family: Impact; -fx-font-weight: bold; -fx-text-fill: white");



        makeAChallengeButton.setPrefSize(200, 100);
        findChallengeButton.setPrefSize(200, 200);
        leaderboardButton.setPrefSize(200, 100);


        PostDisplayScene postDisplayScene = new PostDisplayScene(mainStage, username);


        makeAChallengeButton.setOnAction(e -> new PostCreationScene(mainStage, username).show());
        findChallengeButton.setOnAction(e -> postDisplayScene.show());
        leaderboardButton.setOnAction(e -> {
            try {
                new LeaderBoard(mainStage, username).show();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        StackPane stackPane = new StackPane();
        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setVgap(20);
        buttonGrid.setHgap(20);
        buttonGrid.add(makeAChallengeButton, 0, 1);
        buttonGrid.add(findChallengeButton, 1, 1);
        buttonGrid.add(leaderboardButton, 2, 1);


        VBox actionBox = new VBox(10);
        actionBox.setAlignment(Pos.CENTER);
        actionBox.getChildren().addAll(actionLabel, buttonGrid, stackPane);


        BorderPane root = new BorderPane();
        root.setId("main-border-root");
        root.setCenter(actionBox);
        BorderPane.setAlignment(panel, Pos.TOP_CENTER);
        root.setTop(new VBox(panel));

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(MainScene.class.getResource("/style.css").toExternalForm());

        mainStage.setTitle("Home Screen App");
        mainStage.setScene(scene);
        mainStage.show();
    }

    
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
