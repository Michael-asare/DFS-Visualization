package error_checking.date.formats;

import error_checking.date.DateFormat;

public class LeadingZeroSlashDateFormat extends DateFormat {
    public LeadingZeroSlashDateFormat(String input) {
        this.input = input;
    }

    @Override
    public boolean check() {
        return false;
    }
}
