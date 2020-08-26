import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

import java.time.LocalDate;

public class IntroductionController {
    @FXML
    public TitledPane prompt;

    @FXML
    public Button visualize;

    @FXML
    public TextField firstName;

    @FXML
    public TextField lastName;

    @FXML
    public DatePicker startDate;

    @FXML
    public DatePicker endDate;

    @FXML
    public void handleVisualizePress(ActionEvent actionEvent) {
    }

}
