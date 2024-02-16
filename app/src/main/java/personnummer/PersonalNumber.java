package personnummer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class PersonalNumber extends CheckableNumber{

    public PersonalNumber(String number) {
        super(number);
        addCheck(new FormatCheck(Pattern.compile("^(\\d{6}[-+]?\\d{4})|(\\d{8}[-+]?\\d{4})$")));
        addCheck(new PersonalNumberRangeCheck());
    }

}

/**
 * Check that the personal number refers to a valid date.
 */
class PersonalNumberRangeCheck extends ValidityCheck {

    @Override
    protected void failMessage(CheckableNumber number) {
        System.out.printf("%s does not describe a valid birth date\n", number.number);
    }

    @Override
    public boolean isValid(CheckableNumber number) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        format.setLenient(false);

        CharSequence relevantPart = number.numberSequence.subSequence(0,6);

        try {
            format.parse("19" + relevantPart);
        } catch (ParseException e) {
            try {
                format.parse("20" + relevantPart);
            } catch (ParseException e1) {
                return false;
            }
        }
        return true;
    }
}
