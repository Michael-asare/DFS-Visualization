import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

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
        String response = "";
        if(!isValidFirstName()) {
            response += firstNameErrorMessage();
        }
        if(!isValidLastName()) {
            response += lastNameErrorMessage();
        }
        if(!isValidStartDate()) {
            response += startDateErrorMessage();
        }
        if(!isValidEndDate()) {
            response += endDateErrorMessage();
        }
        return response;
    }

    public boolean isValidFirstName() {
        String text = firstName.getText();
        return text.length() != 0 && !text.contains(" ");
    }

    public boolean isValidLastName() {
        String text = lastName.getText();
        return text.length() != 0;
    }

    public boolean isValidStartDate() {
        try {
            LocalDate localDate = startDate.getValue();
            String text = localDate.toString();
            return text.length() != 0;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean isValidEndDate() {
        try {
            LocalDate localDate = endDate.getValue();
            String text = localDate.toString();
            return text.length() != 0;
        } catch (Exception exception) {
            return false;
        }
    }

    public String firstNameErrorMessage() {
        return "Make sure to enter in a first name. \n" +
                "Also insure that the first name entered does not include any spaces. ";
    }

    public String lastNameErrorMessage() {
        return "Make sure to enter in a last name. \n" +
                "Also insure that the last name entered has no trailing spaces. \n" +
                "If there are any Jrs, III, etc., add that at the end of the last name. ";
    }

    public String startDateErrorMessage() {
        return "Make sure to enter a starting date. \n" +
                "The starting date should be in some form of a MM/DD/YYYY format, \n" +
                "if not selected from the calendar option. ";
    }

    public String endDateErrorMessage() {
        return "Make sure to enter an ending date. \n" +
                "The ending date should be in some form of a MM/DD/YYYY format, \n" +
                "if not selected from the calendar option. ";
    }
}
