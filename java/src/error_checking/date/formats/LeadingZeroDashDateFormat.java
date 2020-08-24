package error_checking.date.formats;

import error_checking.date.DateFormat;

import java.util.regex.Pattern;

public class LeadingZeroDashDateFormat extends DateFormat {
    public LeadingZeroDashDateFormat(String input) {
        this.input = input;
    }

    @Override
    public boolean check() {
        Pattern pattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
        if(!pattern.matcher(input).matches()) {
            return false;
        }
        String [] ls = input.split("-");
        day = Integer.parseInt(ls[0], 10);
        month = Integer.parseInt(ls[1], 10);
        year = Integer.parseInt(ls[2], 10);
        if(validDay() && validMonth() && validYear())
            return true;
        return false;
    }
}
