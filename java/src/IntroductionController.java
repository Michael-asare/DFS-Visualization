import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
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

    /**
     * Handles the logic for when the user clicks the visualize control
     * @param actionEvent The action of pressing visualize
     * @throws IOException
     */
    @FXML
    public void handleVisualizePress(ActionEvent actionEvent) throws IOException {
        /*
        Check the input given from the GUI
        Place error message in error box if an error is given
         */
        InputChecker inputChecker = new InputChecker(firstName, lastName, startDate, endDate);
        String response = inputChecker.checkInput();
        errorArea.setText(response);

        /*
        If there is no error
        Create the data for visualization
        Create the graph for visualization
        Load & Swap the stage over to the visualization pane
         */
        if(response.isEmpty()) {
            Visualizer visualizer = new Visualizer(firstName.getText(), lastName.getText(),
                    startDate.getValue().toString(), endDate.getValue().toString());
            visualizer.createData();
            String imageFilename = visualizer.createGraphImage("points", "date");
            Stage primaryStage = (Stage) prompt.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/visualization.fxml"));
            Parent visualizationRoot = loader.load();
            VisualizationController visualizationController = loader.getController();
            visualizationController.setFilename(imageFilename);
            primaryStage.setScene(new Scene(visualizationRoot));

        }
    }

}
