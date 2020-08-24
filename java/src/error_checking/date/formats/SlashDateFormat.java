package error_checking.date.formats;

import error_checking.date.DateFormat;

import java.util.regex.Pattern;

public class SlashDateFormat extends DateFormat {
    public SlashDateFormat(String input) {
        this.input = input;
    }

    @Override
    public boolean check() {
        return false;
    }
}
