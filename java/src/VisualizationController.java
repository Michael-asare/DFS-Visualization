import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class VisualizationController {
    private String filename;

    @FXML
    private Button quitButton;

    @FXML
    private ImageView displayImage;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @FXML
    public void handleQuitButtonPress(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) quitButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/introduction.fxml"));
        Parent introductionScreenRoot = loader.load();
        primaryStage.setScene(new Scene(introductionScreenRoot));
    }
}
