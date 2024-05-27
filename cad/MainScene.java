package gradlehoam;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScene {
    
    static void showMainScreen(Stage mainStage, String username) throws FileNotFoundException, MalformedURLException {
        // Create UI elements

        // Membuat label untuk judul homescreen
        Label titleLabel = new Label("Selamat Datang di Aplikasi Saya");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-padding: 20px;");

        // Membuat beberapa tombol
        Button btn1 = new Button("Tombol 1");
        Button btn2 = new Button("Tombol 2");
        Button btn3 = new Button("Tombol 3");
        Button btn4 = new Button("Tombol 3");
        Button btn5 = new Button("Tombol 3");
        Button btn6 = new Button("Tombol 3");
        Button btn7 = new Button("Tombol 3");
        Button btn8 = new Button("Tombol 3");
        Button btn9 = new Button("Tombol 3");
        Button btn10 = new Button("Tombol 3");
        Button btn11 = new Button("Tombol 3");
        Button btn12 = new Button("Tombol 3");
        Button btn13 = new Button("Tombol 3");
        Button btn14 = new Button("Tombol 3");
        Button btn15 = new Button("Tombol 3");
        Button btn16 = new Button("Tombol 3");
        Button btn17 = new Button("Tombol 3");
        Button btn18 = new Button("Tombol 3");
        Button btn19 = new Button("Tombol 3");
        Button btn20 = new Button("Tombol 3");
        Button btn21 = new Button("Tombol 3");
        Button btn22 = new Button("Tombol 3");
        Button btn23 = new Button("Tombol 3");

        // Menyusun tombol-tombol dalam VBox
        VBox buttonBox = new VBox(10, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20, btn21, btn22, btn23);
        buttonBox.setStyle("-fx-padding: 20px;");

        // Membuat layout utama menggunakan BorderPane
        BorderPane root = new BorderPane();
        root.setId("main-border-root");
        root.setTop(titleLabel); 
        
        // Membuat ScrollPane dan menambahkan VBox sebagai konten
        ScrollPane scrollPane = new ScrollPane(buttonBox);
        scrollPane.setFitToWidth(true); // Biarkan lebar konten menyesuaikan lebar scroll pane
        scrollPane.setFitToHeight(true); // Biarkan tinggi konten menyesuaikan tinggi scroll pane
        root.setCenter(scrollPane);

        // Membuat Scene dan menambahkan layout utama ke dalamnya
        Scene scene = new Scene(root);

        scene.getStylesheets().add( MainScene.class.getResource("/style.css").toExternalForm());

        // Mengatur judul window utama dan scene
        mainStage.setTitle("Home Screen App");
        mainStage.setScene(scene);

        // Menampilkan window utama
        mainStage.show();
    }
}

