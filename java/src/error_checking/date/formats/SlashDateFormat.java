package error_checking.date.formats;

import error_checking.date.DateFormat;

import java.util.regex.Pattern;

public class SlashDateFormat extends DateFormat {
    public SlashDateFormat(String input) {
        this.input = input;
    }

    @Override
    public boolean check() {
        String [] ls = input.split("/");
        day = Integer.parseInt(ls[0]);
        month = Integer.parseInt(ls[1]);
        year = Integer.parseInt(ls[2]);
        return validDay() && validMonth() && validYear();
    }
}
