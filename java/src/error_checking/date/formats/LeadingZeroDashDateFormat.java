package error_checking.date.formats;

import error_checking.date.DateFormat;

public class LeadingZeroDashDateFormat extends DateFormat {
    public LeadingZeroDashDateFormat(String input) {
        this.input = input;
    }

    @Override
    public boolean check() {
        return false;
    }
}
