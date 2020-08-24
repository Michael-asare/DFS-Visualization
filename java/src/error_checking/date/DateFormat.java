package error_checking.date;

import error_checking.Format;

public abstract class DateFormat implements Format {
    private String input;
    private int day;
    private int month;
    private int year;
}
