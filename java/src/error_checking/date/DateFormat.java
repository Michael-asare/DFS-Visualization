package error_checking.date;

import error_checking.Format;

public abstract class DateFormat implements Format {
    protected String input;
    protected int day = -1;
    protected int month = -1;
    protected int year = -1;
}
