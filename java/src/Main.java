import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/introduction.fxml"));
        Parent root = loader.load();
        IntroductionController introductionController = loader.getController();
        primaryStage.setTitle("DFS Visualization");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
