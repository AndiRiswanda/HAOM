    package haom;

    import javafx.application.Application;
    import javafx.geometry.Insets;
    import javafx.geometry.Pos;
    import javafx.scene.Scene;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Alert.AlertType;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.control.MenuItem;
    import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
    import javafx.scene.layout.GridPane;
    import javafx.scene.layout.HBox;
    import javafx.scene.layout.StackPane;
    import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

    public class MainScene extends Application {

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            try {
                LoginScene.showLoginScreen(primaryStage); // Start with the login screen
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void showMainScreen(Stage mainStage, String username) throws Exception {
            // Yellow panel
            StackPane panel = new StackPane();
            panel.setPadding(new Insets(10, 20, 10, 10));
            panel.setStyle("-fx-background-color: #6a0dad; -fx-pref-height: 85px;");

            
            // Load profile image
            Image profileImage = new Image("/PicAsset/userProfile.png");
            ImageView profileImageView = new ImageView(profileImage);
            profileImageView.setFitHeight(43);
            profileImageView.setFitWidth(43);
            profileImageView.setPreserveRatio(true);
            
            // Create a circle to clip the ImageView
            Circle clip = new Circle(18, 20, 18);
            profileImageView.setClip(clip);

            StackPane.setAlignment(profileImageView, Pos.CENTER_RIGHT);

            Label titleLabel = new Label("Welcome, " + username);
            titleLabel.getStyleClass().add("title-label");
            StackPane.setAlignment(titleLabel, Pos.CENTER_LEFT);

            panel.getChildren().addAll(titleLabel, profileImageView);

            Label descriptionLabel = new Label("We can help you get involved in volunteer or social impact opportunities, understand and reduce your carbon footprint, or start your own event, project, or campaign for positive social change");
            descriptionLabel.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-text-alignment: center;");

            // Teks di atas tombol
            Label actionLabel = new Label("What Would You Like to Do Today ?");
            actionLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-font-family:Impact; -fx-text-fill: white;");

            Button btn1 = new Button("Volunteer and Find\nImpact Opportunities");
            btn1.setStyle("-fx-font-size: 17px; -fx-font-family: Impact; -fx-font-weight: bold; -fx-text-fill: white");
            Button btn2 = new Button("Lead a Social Impact\nEvent, Fundraiser,\nProject, Club,\nor Organization");
            btn2.setStyle("-fx-font-size: 17px; -fx-font-family: Impact; -fx-font-weight: bold; -fx-text-fill: white");
            Button btn3 = new Button("Understand and Reduce\nMy Climate Footprint");
            btn3.setStyle("-fx-font-size: 17px; -fx-font-family: Impact; -fx-font-weight: bold; -fx-text-fill: white");

            // Set button sizes
            btn1.setPrefSize(200, 200);
            btn2.setPrefSize(200, 200);
            btn3.setPrefSize(200, 200);

            // Set button actions
            btn1.setOnAction(e -> new VolunteerScene(mainStage, username).show());
            btn2.setOnAction(e -> new LeadEventScene(mainStage, username).show());
            btn3.setOnAction(e -> new ClimateFootprintScene(mainStage, username).show());

            

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

            public abstract void show();
        }

        // VolunteerScene class
        static class VolunteerScene extends BaseScene {

            public VolunteerScene(Stage stage, String username) {
                super(stage, username);
            }

            @Override
            public void show() {
                Label titleLabel = new Label("Volunteer Opportunities for " + username);
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

        // ClimateFootprintScene class
        static class ClimateFootprintScene extends BaseScene {

            public ClimateFootprintScene(Stage stage, String username) {
                super(stage, username);
            }

            @Override
            public void show() {
                Label titleLabel = new Label("Understand and Reduce Your Climate Footprint, " + username);
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
    }
