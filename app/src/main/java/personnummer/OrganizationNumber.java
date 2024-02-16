package personnummer;

import java.util.regex.Pattern;

public class OrganizationNumber extends CheckableNumber {


    OrganizationNumber(String number) {
        super(number);
        addCheck(new FormatCheck(Pattern.compile("^(16)?\\d{6}-?\\d{4}$")));
        addCheck(new OrganizationNumberRangeCheck());
    }
}

/**
 * Check that the middle two numbers are at least 20.
 **/
class OrganizationNumberRangeCheck implements ValidityCheck {

    @Override
    public void failMessage(CheckableNumber number) {
        System.out.printf("%s does not have middle two numbers >= 20\n", number.number);
    }
    @Override
    public boolean isValid(CheckableNumber number) {
        CharSequence middle = number.numberSequence.subSequence(2, 4);
        return Integer.parseInt(middle.toString()) >= 20;
    }
}