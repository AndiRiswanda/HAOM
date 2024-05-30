package haom;

import haom.MainScene.BaseScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PostDisplayScene extends BaseScene {

    private ObservableList<PostInfo> postsList;

    public PostDisplayScene(Stage stage, String username) {
        super(stage, username);
        this.postsList = FXCollections.observableArrayList();
    }

    @Override
    public void show() {
        Label titleLabel = new Label("Posts");
        titleLabel.setStyle("-fx-font-size: 40px; -fx-padding: 25px; -fx-text-fill: #6a0dad;");

        ListView<PostInfo> postsListView = new ListView<>(postsList);
        postsListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(PostInfo item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Create a custom HBox to represent the post
                    HBox postBox = new HBox(10);
                    postBox.setAlignment(Pos.CENTER_LEFT);

                    // Display post details
                    ImageView imageView = new ImageView(new Image(item.getImageURL()));
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);

                    Label titleLabel = new Label(item.getTitle());
                    Label genreLabel = new Label("Genre: " + item.getGenre());
                    Label descriptionLabel = new Label("Description: " + item.getDescription());

                    VBox textInfo = new VBox(5);
                    textInfo.getChildren().addAll(titleLabel, genreLabel, descriptionLabel);

                    postBox.getChildren().addAll(imageView, textInfo);
                    setGraphic(postBox);
                }
            }
        });

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #6a0dad; -fx-text-fill: white;");
        backButton.setOnAction(e -> {
            try {
                MainScene.showMainScreen(stage, username);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(titleLabel, postsListView, backButton);
        layout.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(layout);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void addPost(PostInfo post) {
        postsList.add(post);
    }
}
