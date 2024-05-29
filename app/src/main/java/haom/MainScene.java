package haom;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import org.sqlite.SQLiteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.DatabaseConnection;
import controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

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

        Label descriptionLabel = new Label(
                "We can help you get involved in volunteer or social impact opportunities, understand and reduce your carbon footprint, or start your own event, project, or campaign for positive social change");
        descriptionLabel.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-text-alignment: center;");

        // Teks di atas tombol
        Label actionLabel = new Label("What Would You Like to Do Today?");
        actionLabel
                .setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-font-family: Impact; -fx-text-fill: white;");

        Button btn1 = new Button("Volunteer and Find\nImpact Opportunities");
        btn1.setStyle("-fx-font-size: 17px; -fx-font-family: Impact; -fx-font-weight: bold; -fx-text-fill: white");
        Button btn2 = new Button("Lead a Social Impact\nEvent, Fundraiser,\nProject, Club,\nor Organization");
        btn2.setStyle("-fx-font-size: 17px; -fx-font-family: Impact; -fx-font-weight: bold; -fx-text-fill: white");
        Button btn3 = new Button("LeaderBoard");
        btn3.setStyle("-fx-font-size: 17px; -fx-font-family: Impact; -fx-font-weight: bold; -fx-text-fill: white");

        // Set button sizes
        btn1.setPrefSize(200, 200);
        btn2.setPrefSize(200, 200);
        btn3.setPrefSize(200, 200);

        // Set button actions
        btn1.setOnAction(e -> new VolunteerScene(mainStage, username).show());
        btn2.setOnAction(e -> new LeadEventScene(mainStage, username).show());
        btn3.setOnAction(e -> {
            try {
                new LeaderBoard(mainStage, username).show();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        // Profile Butt

        // Arrange buttons in a GridPane
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
        actionBox.getChildren().addAll(actionLabel, buttonGrid);

        // Main layout
        BorderPane root = new BorderPane();
        root.setId("main-border-root");
        root.setCenter(actionBox);
        root.setBottom(descriptionLabel);
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

    // ProfileScene class
    public static class ProfileScene extends BaseScene {

        private String username;

        public ProfileScene(Stage stage, String username) {
            super(stage, username);
            this.username = username;
        }

        @Override
        public void show() {
            Label titleLabel = new Label("Profile of " + username);
            titleLabel.setId("profile-title-label");
            titleLabel.setStyle("-fx-font-size: 24px;");

            // Get user-specific profile image path from the database
            String userImagePath = UserController.getProfileImagePath(username);

            Image profileImage = ImageLoader.loadProfileImage(userImagePath);
            ImageView profileImageView = new ImageView(profileImage);
            profileImageView.setFitHeight(100);
            profileImageView.setFitWidth(100);
            profileImageView.setPreserveRatio(true);
            profileImageView.setId("profile-image-view");

            Button changeImageButton = new Button("Change Profile Image");
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

            Button backButton = new Button("Back");
            backButton.setId("back-button");
            backButton.setOnAction(e -> {
                try {
                    MainScene.showMainScreen(stage, username);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(titleLabel, profileImageView, changeImageButton, backButton);
            stackPane.setAlignment(Pos.CENTER);
            StackPane.setAlignment(titleLabel, Pos.TOP_CENTER);
            StackPane.setAlignment(profileImageView, Pos.TOP_CENTER);
            StackPane.setAlignment(changeImageButton, Pos.CENTER);
            StackPane.setAlignment(backButton, Pos.TOP_RIGHT);

            Circle clip = new Circle(50, 50, 50);
            profileImageView.setClip(clip);

            Scene scene = new Scene(stackPane, 800, 600);
            scene.getStylesheets().add(MainScene.class.getResource("/profileStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
    }

    // VolunteerScene class
    static class VolunteerScene extends BaseScene {

        public VolunteerScene(Stage stage, String username) {
            super(stage, username);
        }

        @Override
        public void show() {
            Label titleLabel = new Label("Volunteer Opportunities for " + username);
            titleLabel
                    .setStyle("-fx-font-size: 24px; -fx-padding: 20px; -fx-text-fill: #6a0dad; -fx-font-weight: bold;");

            Button backButton = new Button("Back");
            backButton.setStyle("-fx-background-color: #6a0dad; -fx-text-fill: white; -fx-font-weight: bold;");
            backButton.setOnAction(e -> {
                try {
                    MainScene.showMainScreen(stage, username);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            VBox layout = new VBox(20);
            layout.getChildren().addAll(titleLabel, backButton);
            layout.setAlignment(Pos.CENTER);

            BorderPane root = new BorderPane();
            root.setCenter(layout);

            // Set background color
            root.setStyle("-fx-background-color: #f0f0f0;");

            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        }
    }

    // LeadEventScene class
    static class LeadEventScene extends BaseScene {

        public LeadEventScene(Stage stage, String username) {
            super(stage, username);
        }

        @Override
        public void show() {
            Label titleLabel = new Label("Lead a Social Impact Event for " + username);
            titleLabel.setStyle("-fx-font-size: 24px; -fx-padding: 20px;");

            Button backButton = new Button("Back");
            backButton.setOnAction(e -> {
                try {
                    MainScene.showMainScreen(stage, username);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            VBox layout = new VBox(20);
            layout.getChildren().addAll(titleLabel, backButton);
            layout.setAlignment(Pos.CENTER);

            BorderPane root = new BorderPane();
            root.setCenter(layout);

            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        }
    }

    // LeaderBoard class
    static class LeaderBoard extends BaseScene {

        public LeaderBoard(Stage stage, String username) {
            super(stage, username);
        }

        @Override
        public void show() throws SQLException {
            Label titleLabel = new Label("Haom Leaderboard");
            titleLabel.setStyle("-fx-font-size: 40px; -fx-padding: 25px; -fx-text-fill: #6a0dad;");

            // Fetch usernames and haomic points from the SQLite database
            List<String> leaderboardData = fetchUserHaomicPointsOrderedByHaomicPoints();

            ObservableList<String> leaderboardList = FXCollections.observableArrayList(leaderboardData);
            ListView<String> leaderboardListView = new ListView<>(leaderboardList);
            leaderboardListView.getStyleClass().add("leaderboardstyle");

            // Set custom cell factory to add numbers to each item
            leaderboardListView.setCellFactory(param -> new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        // Get the index of the item in the list
                        int index = getIndex();
                        // Set the text of the cell to display the index and the username with haomic
                        // points
                        setText((index + 1) + ". " + item);
                    }
                }
            });

            Button backButton = new Button("Back");
            backButton.setStyle("-fx-background-color: #6a0dad; -fx-text-fill: white;"); // Set button background and
                                                                                         // text color
            backButton.setOnAction(e -> {
                try {
                    MainScene.showMainScreen(stage, ""); // Replace "" with appropriate username
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            VBox layout = new VBox(20);
            layout.getChildren().addAll(titleLabel, leaderboardListView, backButton);
            layout.setAlignment(Pos.CENTER);

            BorderPane root = new BorderPane();
            root.setCenter(layout);

            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }

        private List<String> fetchUserHaomicPointsOrderedByHaomicPoints() throws SQLException {
            List<String> userHaomicPoints = new ArrayList<>();
            try (Connection conn = DatabaseConnection.connect();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt
                            .executeQuery("SELECT username, haomicpoint FROM users ORDER BY haomicpoint DESC")) {
                while (rs.next()) {
                    String username = rs.getString("username");
                    int haomicPoints = rs.getInt("haomicpoint");
                    userHaomicPoints.add(username + " - " + haomicPoints + " Hoamic Points");
                }
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
            return userHaomicPoints;
        }
    }

}
