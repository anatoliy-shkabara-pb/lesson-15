package s03javafx.fxml2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class ChatWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Paths.get("src/s03javafx/fxml2/fxml_chat_window.fxml").toUri().toURL());

        Scene scene = new Scene(root);

        stage.setTitle("Chat");
        stage.setScene(scene);
        stage.show();
    }
}
