package haom;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import config.DatabaseConnection;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScene {
    
    public static void showLoginScreen(Stage mainStage) {

        // Create UI elements
        Label usernameLabel = new Label("Username :");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password  :");
        PasswordField passwordField = new PasswordField();

        Text loginText = new Text("HEY\nLET'S GET STARTED\nHELPING PEOPLE");
        loginText.getStyleClass().add("login-text");

        Text haomText1 = new Text("HAOM");
        haomText1.getStyleClass().add("haom-text-1");

        Text haomText2 = new Text("Help Act Out Mission");
        haomText2.getStyleClass().add("haom-text-2");
        
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            
            boolean loginValid = DatabaseConnection.validateLogin(username, password);
            if (loginValid) {
                try {
                    MainScene.showMainScreen(mainStage, username); // Call the showMainScreen method
                } catch (FileNotFoundException | MalformedURLException ex) {
                    // Handle the FileNotFoundException and MalformedURLException (e.g., show an error message)
                    ex.printStackTrace();
                    showAlert("File Not Found", "The file required to proceed was not found.");
                } catch (Exception ex) {
                    // Handle any other Exception
                    ex.printStackTrace();
                    showAlert("Error", "An unexpected error occurred: " + ex.getMessage());
                }
            } else {
                // Display error message if login is invalid
                CustomAlert.showCustomAlert(AlertType.ERROR, "Login Failed", "Invalid username or password");

            }
        });
 
        // Load images
        Image haomIcon = new Image(LoginScene.class.getResourceAsStream("/PicAsset/charHaomOpen.png"));
        ImageView imageView1 = new ImageView(haomIcon);
        Image haomIconCloseyes = new Image(LoginScene.class.getResourceAsStream("/PicAsset/charHaomClose.png"));
        ImageView imageView2 = new ImageView(haomIconCloseyes);

        // Layout using GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Add elements to GridPane
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(loginButton, 1, 2);

        // Create a VBox to center the GridPane
        VBox loginBox = new VBox(gridPane);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setSpacing(10);
        loginBox.setPadding(new Insets(150, 0, 0, 0)); // Adjust top padding to move the VBox down

        // Create rectangle
        Rectangle rectangle = new Rectangle(420, 200); // Set dimensions
        rectangle.setArcWidth(20); // Set corner radius
        rectangle.setArcHeight(20);
        rectangle.setStroke(Color.web("#272726")); // Set stroke color
        rectangle.setStrokeWidth(17); // Set stroke width
        rectangle.setFill(Color.TRANSPARENT); // Set fill color to transparent

        Circle circle1 = new Circle(250, Color.web("#6a0dad")); // Create purple circle
        Circle circle2 = new Circle(250, Color.web("#6a0dad")); // Create purple circle
        Circle circle3 = new Circle(150, Color.web("#6a0dad")); // Create purple circle

        Text dontHaveAccountText = new Text("Don't have an account?                here");
        dontHaveAccountText.setFill(Color.WHITE);
        // Create clickable hyperlink for "Register"
        Hyperlink registerLink = new Hyperlink("Register");
        registerLink.setOnAction(e -> {
            try {
                RegisterScene.showRegisterScreen(mainStage); // Call the showRegisterScreen method
            } catch (FileNotFoundException ex) {
                // Handle the FileNotFoundException (e.g., show an error message)
                ex.printStackTrace();
            }
        });
        registerLink.getStyleClass().add("login-register-link");

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> Platform.exit());
        exitButton.setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
        usernameField.setOnAction(e -> passwordField.requestFocus());
        passwordField.setOnAction(e -> loginButton.fire());
        // StackPane to stack the loginBox and rectangle
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(haomText1, haomText2, rectangle, loginBox, imageView1, loginText, circle1, circle2, circle3, dontHaveAccountText, registerLink,exitButton);
        StackPane.setMargin(haomText1, new Insets(0, 1250, 720, 0));
        StackPane.setMargin(haomText2, new Insets(0, 1250, 660, 0));
        StackPane.setMargin(rectangle, new Insets(140, 0, 0, 0));
        StackPane.setMargin(imageView1, new Insets(0, 0, 269, 0));
        StackPane.setMargin(imageView2, new Insets(0, 9, 269, 0));
        StackPane.setMargin(loginText, new Insets(0, 0, 330, -150));
        StackPane.setMargin(circle1, new Insets(900, 0, 330, -1025 - 60));
        StackPane.setMargin(circle2, new Insets(0, 0, 530, 1000));
        StackPane.setMargin(circle3, new Insets(390, 0, 280, -1000 - 60));
        StackPane.setMargin(dontHaveAccountText, new Insets(400, 0, 0, 0));
        StackPane.setMargin(registerLink, new Insets(401, -100, 0, 0));
        StackPane.setMargin(exitButton, new Insets(550, -865, 0, 0)); 

        passwordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> {
                    if (!newValue.isEmpty()) {
                        if (!stackPane.getChildren().contains(imageView2)) {
                            stackPane.getChildren().remove(imageView1);
                            stackPane.getChildren().add(imageView2);
                        }
                    } else {
                        if (!stackPane.getChildren().contains(imageView1)) {
                            stackPane.getChildren().remove(imageView2);
                            stackPane.getChildren().add(imageView1);
                        }
                    }
                    loginText.toFront();
                });
            }
        });

        // Add hover event to the password field
        passwordField.setOnMouseEntered(event -> {
            Platform.runLater(() -> {
                if (!stackPane.getChildren().contains(imageView2)) {
                    stackPane.getChildren().remove(imageView1);
                    stackPane.getChildren().add(imageView2);
                    loginText.toFront();
                }
            });
        });

        passwordField.setOnMouseExited(event -> {
            Platform.runLater(() -> {
                if (!stackPane.getChildren().contains(imageView1)) {
                    stackPane.getChildren().remove(imageView2);
                    stackPane.getChildren().add(imageView1);
                    loginText.toFront();
                }
            });
        });

        // Set up the Scene
        Scene scene = new Scene(stackPane);
        scene.getStylesheets().add(LoginScene.class.getResource("/style.css").toExternalForm());

        // Configure and show the Stage
        mainStage.setTitle("Login Screen");
        mainStage.setScene(scene);
        mainStage.show();
    }

    private static void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
