import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class VisualizationController {
    private String filename;

    @FXML
    private Button quitButton;

    @FXML
    private ImageView displayImage;

    public VisualizationController(String filename) {
        this.filename = filename;
    }

    @FXML
    public void handleQuitButtonPress(ActionEvent actionEvent) {
    }
}
