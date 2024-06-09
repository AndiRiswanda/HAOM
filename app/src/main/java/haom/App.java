package haom;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import config.DatabaseConnection;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/* Update dan Perbaikan:

1. Memperbaiki Format Hyperlink pada Login dan Register Scene
2. Perbaikan atau Pemindahan Posisi Tombol Logout pada Profile Scene agar lebih tersembunyi. 
3. Perbaikan UI. Tombol Delete Profile (Warna Merah) dan Tombol Change Profile (Warna Biru). 
4. Konfirmasi untuk Delete Profile Image dan Logout. 
5. Perbaikan atau Pemindahan Posisi Tombol Back pada Profile Scene. 
6. Rename variable button 1,2,3 menjadi nama yang lebih mudah di identifikasi. 
7. Menambahkan Scroll Bar pada Leaderboard Scene. 
8. Penambahan Fitur Help Point.
9. Menambahkan Fitur Post yang ter urut berdasarkan jumlah Help Point.*/

public class App extends Application {
    public static void main(String[] args) {
        DatabaseConnection.createNewTable();
        launch();
    }


    @Override
    public void start(Stage PrimaryStage) throws FileNotFoundException, MalformedURLException{
        PrimaryStage.setHeight(630);
        PrimaryStage.setWidth(940);
        PrimaryStage.setResizable(false);
        Image icon = new Image(getClass().getResourceAsStream("/PicAsset/Icon.png"));
        PrimaryStage.getIcons().add(icon);
        LoginScene.showLoginScreen(PrimaryStage);
    }

}
