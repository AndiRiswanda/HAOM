package hoamsrc;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage PrimaryStage) throws FileNotFoundException, MalformedURLException{
        
        
        PrimaryStage.setHeight(625);
        PrimaryStage.setWidth(1000);
        PrimaryStage.setResizable(false);
        LoginScene.showloginscreen(PrimaryStage);


    }

}
