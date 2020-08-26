import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;

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
    public TextArea errorArea;

    @FXML
    public void handleVisualizePress(ActionEvent actionEvent) {
        InputChecker inputChecker = new InputChecker(firstName, lastName, startDate, endDate);
        String response = inputChecker.checkInput();
        errorArea.setText(response);
        if(response.isEmpty()) {
            Visualizer visualizer = new Visualizer(firstName.getText(), lastName.getText(),
                    startDate.getValue().toString(), endDate.getValue().toString());
            visualizer.createData("points", "date");
            String imageFilename = visualizer.createGraphImage();
        }
    }

}
