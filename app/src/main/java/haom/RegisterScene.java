package haom;

import java.io.FileNotFoundException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Import UserController
import controller.UserController;

public class RegisterScene {

    public static boolean isUsernameValid(String username) {
        String usernameRegex = "^[a-zA-Z0-9_]{5,20}$";
        Pattern pattern = Pattern.compile(usernameRegex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    // Method to validate email
    public static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Method to validate password
    public static boolean isPasswordValid(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


    public static void showRegisterScreen(Stage mainStage) throws FileNotFoundException {
        // UI Elements

        
        Label usernameLabel = new Label("Pick a Username :");
        usernameLabel.getStyleClass().add("username-label-register");
        TextField usernameField = new TextField();
        
        Label emailLabel = new Label("Your Email           :");
        emailLabel.getStyleClass().add("email-label-register");
        TextField emailField = new TextField();
        
        Label passwordLabel = new Label("Password             :");
        passwordLabel.getStyleClass().add("password-label-register");
        PasswordField passwordField = new PasswordField();

        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                CustomAlert.showCustomAlert(AlertType.ERROR, "Form Error!", "Please fill in all fields.");
            } else if (!isUsernameValid(username)) {
                CustomAlert.showCustomAlert(AlertType.ERROR, "Invalid Username", "Username must be 5-20 characters");
            } else if (!isEmailValid(email)) {
                CustomAlert.showCustomAlert(AlertType.ERROR, "Invalid Email", "Please enter a valid email address.");
            } else if (!isPasswordValid(password)) {
                CustomAlert.showCustomAlert(AlertType.ERROR, "Invalid Password", "Password must be at least 8\ncharacters long,lowercase,\nuppercase letter, and digit.");
            } else {
                boolean success = UserController.registerUser(username, email, password);
                if (success) {
                    CustomAlert.showCustomAlert(AlertType.INFORMATION, "Registration Successful", "User registered successfully!");
                    LoginScene.showLoginScreen(mainStage);
                } else {
                    CustomAlert.showCustomAlert(AlertType.ERROR, "Registration Failed", "An error occurred while registering the user.");
                }
            }
        });
        usernameField.setOnAction(e -> emailField.requestFocus());
        emailField.setOnAction(e -> passwordField.requestFocus());
        passwordField.setOnAction(e -> registerButton.fire());

        // Images
        Image haomIcon = new Image(RegisterScene.class.getResourceAsStream("/PicAsset/charHaomOpen.png"));
        ImageView imageView1 = new ImageView(haomIcon);
        Image haomIconCloseyes = new Image(RegisterScene.class.getResourceAsStream("/PicAsset/charHaomClose.png"));
        ImageView imageView2 = new ImageView(haomIconCloseyes);

        // Text
        Text registerText = new Text("REGISTER NOW !");
        registerText.getStyleClass().add("register-text"); // Assuming you have a CSS class for styling

        Text haomText1 = new Text("HAOM");
        haomText1.getStyleClass().add("haom-text-1");
        
        Text haomText2 = new Text("Help Act Out Mission");
        haomText2.getStyleClass().add("haom-text-2");

        // Layout (GridPane for form elements)
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(emailLabel, 0, 1);
        gridPane.add(emailField, 1, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(passwordField, 1, 2);
        gridPane.add(registerButton, 1, 3);

        // VBox to center the GridPane
        VBox registerBox = new VBox(gridPane);
        registerBox.setAlignment(Pos.CENTER);
        registerBox.setSpacing(10);
        registerBox.setPadding(new Insets(150, 0, 0, 0)); // Top padding for visual adjustment

        // Background Rectangle and Decorative Circles
        Rectangle rectangle = new Rectangle(420, 250); // Adjusted height for email field
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.setStroke(Color.web("#272726"));
        rectangle.setStrokeWidth(17);
        rectangle.setFill(Color.TRANSPARENT);


        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> Platform.exit());
        exitButton.setStyle("-fx-background-color: #333333; -fx-text-fill: white;");

        HBox exitButtonBox = new HBox(exitButton);
        exitButtonBox.setAlignment(Pos.BOTTOM_RIGHT);
        exitButtonBox.setPadding(new Insets(10));


        Circle circle1 = new Circle(250, Color.web("#6a0dad")); // Create purple circle
        Circle circle2 = new Circle(250, Color.web("#6a0dad")); // Create purple circle
        Circle circle3 = new Circle(150, Color.web("#6a0dad")); // Create purple circle

        Text alreadyHaveAnAccount = new Text("Already have an account?           here");
        alreadyHaveAnAccount.setFill(Color.WHITE);
        // Create clickable hyperlink for "Login"
        Hyperlink loginLink = new Hyperlink(" Login");
        loginLink.setOnAction(e -> { 
            LoginScene.showLoginScreen(mainStage); // Call the showloginscreen method
        });
        loginLink.getStyleClass().add("login-register-link"); 
        // StackPane for Layering (background, form, images, text)
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(haomText1, haomText2, rectangle, registerBox, imageView1, registerText, circle1, circle2, circle3, alreadyHaveAnAccount, loginLink,exitButton);
        // ... (Set margins for elements in StackPane as needed)
        StackPane.setMargin(haomText1, new Insets(0, 1250, 720, 0));
        StackPane.setMargin(haomText2, new Insets(0, 1250, 660, 0));
        StackPane.setMargin(rectangle, new Insets(140, 0, 0, 0)); 
        StackPane.setMargin(imageView1, new Insets(0, 0, 320, 0)); 
        StackPane.setMargin(imageView2, new Insets(0, 0, 320, 0)); 
        StackPane.setMargin(registerText, new Insets(0, 0, 330, -150)); 
        StackPane.setMargin(circle1, new Insets(900, 0, 330, -1025 - 60));
        StackPane.setMargin(circle2, new Insets(0, 0, 530, 1000));
        StackPane.setMargin(circle3, new Insets(390, 0, 280, -1000 - 60));
        StackPane.setMargin(alreadyHaveAnAccount, new Insets(450, 0, 0, 0)); 
        StackPane.setMargin(loginLink, new Insets(450, -110, 0, 0)); 
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
                    registerText.toFront();
                });
            }
        });

        // Add hover event to the password field
        passwordField.setOnMouseEntered(event -> {
            Platform.runLater(() -> {
                if (!stackPane.getChildren().contains(imageView2)) {
                    stackPane.getChildren().remove(imageView1);
                    stackPane.getChildren().add(imageView2);
                    registerText.toFront();
                }
            });
        });

        passwordField.setOnMouseExited(event -> {
            Platform.runLater(() -> {
                if (!stackPane.getChildren().contains(imageView1)) {
                    stackPane.getChildren().remove(imageView2);
                    stackPane.getChildren().add(imageView1);
                    registerText.toFront();
                }
            });
        });        

        // Scene and Stage Setup
        Scene scene = new Scene(stackPane);
        scene.getStylesheets().add(RegisterScene.class.getResource("/style.css").toExternalForm());

        mainStage.setTitle("Register Screen");
        mainStage.setScene(scene);
        mainStage.show();
    }

}
