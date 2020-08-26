import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class InputChecker {
    private TextField firstName;
    private TextField lastName;
    private DatePicker startDate;
    private DatePicker endDate;

    public InputChecker(TextField firstName, TextField lastName, DatePicker startDate, DatePicker endDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String checkInput() {
        return null;
    }

    
}
