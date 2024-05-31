package haom;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import org.sqlite.SQLiteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.DatabaseConnection;
import haom.MainScene.BaseScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
// LeaderBoard class
public class LeaderBoard extends BaseScene {

    public LeaderBoard(Stage stage, String username) {
        super(stage, username);
    }

    @Override
    public void show() throws SQLException {
        Label titleLabel = new Label("HAOM LEADERBOARD");
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
                MainScene.showMainScreen(stage, username);
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
