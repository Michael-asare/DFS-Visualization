package error_checking.first_name.formats;

import error_checking.first_name.FirstNameFormat;

public class StandardFirstNameFormat extends FirstNameFormat {
    public StandardFirstNameFormat(String input) {
        this.input = input;
    }

    @Override
    public boolean check() {
        if(input.indexOf(" ") != -1)
            return false;
        return true;
    }
}
