package haom;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CustomAlert extends Alert {

    public CustomAlert(AlertType alertType) {
        super(alertType);
        applyCustomStyling();
    }

    private void applyCustomStyling() {
        DialogPane dialogPane = this.getDialogPane();
        Scene scene = dialogPane.getScene();
        scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());

        Image icon = new Image(getClass().getResourceAsStream("/PicAsset/Icon.png"));

        // Set the graphic (icon) on the dialog pane
        dialogPane.setGraphic(new ImageView(icon));
    }
    public void setAlertIcon(Image icon) {
        Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
        stage.getIcons().add(icon);
    }
    public static void showCustomAlert(AlertType alertType, String title, String message) {
        CustomAlert alert = new CustomAlert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Image icon = new Image(CustomAlert.class.getResourceAsStream("/PicAsset/Icon.png"));
        alert.setAlertIcon(icon);
        alert.showAndWait();
    }
}
