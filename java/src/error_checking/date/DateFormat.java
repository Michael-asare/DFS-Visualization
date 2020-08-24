package error_checking.date;

import error_checking.Format;

public abstract class DateFormat implements Format {
    protected String input;
    protected int day = -1;
    protected int month = -1;
    protected int year = -1;

    protected boolean validYear() {
        if(year < 0) {
            return false;
        }
        return true;
    }

    protected boolean validMonth() {
        if(year > 12 || year < 1) {
            return false;
        }
        return true;
    }

    private boolean isLeapYear() {
        if(year % 4 != 0) {
            return false;
        } else if(year % 100 != 0) {
            return true;
        } else if(year % 400 != 0) {
            return false;
        }
        return true;
    }

    protected boolean validDay() {
        if(!validMonth()) {
            return false;
        }
        if(day < 1) {
            return false;
        }
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if(day <= 31)
                return true;
            return false;
        } else if(month == 4 || month == 6 || month == 9 || month == 11) {
            if(day <= 30)
                return true;
            return false;
        } else {
            if(isLeapYear()) {
                if(day <= 29)
                    return true;
            } else {
                if(day <= 28)
                    return true;
            }
            return false;
        }
    }

}
