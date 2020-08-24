package error_checking.date;

import error_checking.Format;

public abstract class DateFormat implements Format {
    protected String input;
    protected int day;
    protected int month;
    protected int year;
}
