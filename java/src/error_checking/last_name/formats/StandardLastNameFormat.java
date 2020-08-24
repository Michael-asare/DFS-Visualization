package error_checking.last_name.formats;

import error_checking.last_name.LastNameFormat;

public class StandardLastNameFormat extends LastNameFormat {
    public StandardLastNameFormat(String input) {
        this.input = input;
    }

    @Override
    public boolean check() {
        if(input.indexOf(" ") != -1)
            return false;
        return true;
    }
}
