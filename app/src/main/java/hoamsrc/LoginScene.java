package hoamsrc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
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
    
    static void showloginscreen(Stage mainStage) throws FileNotFoundException, MalformedURLException {
         // Create UI elements
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        Text haomText = new Text("HeY!\nLets Get Started\nHelping people");
        haomText.getStyleClass().add("haomText");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");


        //load image
        Image haomIcon = new Image(LoginScene.class.getResourceAsStream("/PicAsset/Asset 1.png"));
        ImageView imageView1 = new ImageView(haomIcon);
        Image haomIconCloseyes = new Image(LoginScene.class.getResourceAsStream("/PicAsset/Asset 3.png"));
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

        Circle circle1 = new Circle(200, Color.web("#6a0dad")); // Create purple circle
        Circle circle2 = new Circle(150, Color.web("#6a0dad")); // Create purple circle
        Circle circle3 = new Circle(80, Color.web("#6a0dad")); // Create purple circle

        Text dontHaveAccountText = new Text("Don't have an account?                here");
        // Create clickable hyperlink for "Register"
        Hyperlink registerLink = new Hyperlink("Register");
        registerLink.setOnAction(e -> {
            // Add action to perform when the link is clicked (e.g., open registration form)
            System.out.println("Register link clicked!");
        });
        registerLink.setStyle("-fx-text-fill: #9a4bff;"); // Set text color to purple

        // StackPane to stack the loginBox and rectangle
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, loginBox,imageView1,haomText, circle1, circle2,circle3, dontHaveAccountText,registerLink);
        StackPane.setMargin(rectangle, new Insets(140, 0,0, 0)); 
        StackPane.setMargin(imageView1, new Insets(0, 0,269, 0)); 
        StackPane.setMargin(imageView2, new Insets(0, 9,269, 0)); 
        StackPane.setMargin(haomText, new Insets(0, 0,330, -150)); 
        StackPane.setMargin(circle1, new Insets(900, 0,330, -900-60)); 
        StackPane.setMargin(circle2, new Insets(0, 0,530, 950)); 
        StackPane.setMargin(circle3, new Insets(400, 0,280, -1000-60)); 
        StackPane.setMargin(dontHaveAccountText, new Insets(400,0,0,0)); 
        StackPane.setMargin(registerLink, new Insets(400,-100,0,0)); 


        passwordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Check if the password field is not empty
                if (!newValue.isEmpty()) {
                    // If not empty, set imageView2 as the image
                    haomText.toFront();
                    stackPane.getChildren().remove(imageView1); // Remove the previous image
                    haomText.toFront();
                    stackPane.getChildren().add(imageView2); // Add the new image
                } else {
                    // If empty, set imageView1 as the image
                    haomText.toFront();
                    stackPane.getChildren().remove(imageView2); // Remove the previous image
                    haomText.toFront();
                    stackPane.getChildren().add(imageView1); // Add the new image
                    haomText.toFront();
                }
            }
        }); 

        // Add hover event to the password field
        passwordField.setOnMouseEntered(event -> {
            // Set imageView2 as the image when hovering over the password field
            haomText.toFront();
            stackPane.getChildren().remove(imageView1); // Remove the previous image
            haomText.toFront();
            stackPane.getChildren().add(imageView2); // Add the new image
            haomText.toFront();
        });

        passwordField.setOnMouseExited(event -> {
            // Set imageView1 as the image when mouse exits the password field
            haomText.toFront();
            stackPane.getChildren().remove(imageView2); // Remove the previous image
            haomText.toFront();
            stackPane.getChildren().add(imageView1); // Add the new image
            haomText.toFront();
        });

        // Set up the Scene
        Scene scene = new Scene(stackPane);
        
        scene.getStylesheets().add(LoginScene.class.getResource("/LoginStyle.css").toExternalForm());

        // Configure and show the Stage
        mainStage.setTitle("Login Screen");
        mainStage.setScene(scene);
        mainStage.show();
        }



}
