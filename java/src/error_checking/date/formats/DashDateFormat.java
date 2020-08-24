package error_checking.date.formats;

import error_checking.date.DateFormat;

public class DashDateFormat extends DateFormat {
    public DashDateFormat(String input) {
        this.input = input;
    }

    @Override
    public boolean check() {
        String [] ls = input.split("-");

        return false;
    }
}
