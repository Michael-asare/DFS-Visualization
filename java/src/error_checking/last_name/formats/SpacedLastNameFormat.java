package error_checking.last_name.formats;

import error_checking.last_name.LastNameFormat;

public class SpacedLastNameFormat extends LastNameFormat {
    public SpacedLastNameFormat(String input) {
        this.input = input;
    }

    @Override
    public boolean check() {
        return true;
    }
}
