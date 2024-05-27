package haom;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import config.DatabaseConnection;
import javafx.application.Application;
import javafx.stage.Stage;

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
        LoginScene.showLoginScreen(PrimaryStage);
    }

}
