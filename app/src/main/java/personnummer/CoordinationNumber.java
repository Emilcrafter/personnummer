package personnummer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class CoordinationNumber extends CheckableNumber {

    CoordinationNumber(String number) {
        super(number);
        addCheck(new FormatCheck(Pattern.compile("^(\\d{6}[-+]?\\d{4})|(\\d{8}[-+]?\\d{4})$")));
        addCheck(new CoordinationNumberRangeCheck());
    }

}

/**
 * Check that the coordination number refers to a valid date (after subtracting 60 from the day).
 */
class CoordinationNumberRangeCheck implements ValidityCheck {

    @Override
    public void failMessage(CheckableNumber number) {
        System.out.printf("%s does not describe a valid birth date\n", number.number);
    }

    @Override
    public boolean isValid(CheckableNumber number) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        format.setLenient(false);
        CharSequence sequence = number.numberSequence;
        CharSequence year = sequence.subSequence(0, 2);
        CharSequence month = sequence.subSequence(2, 4);
        CharSequence day = sequence.subSequence(4, 6);
        int adaptedDay = Integer.parseInt(day.toString()) - 60;
        String adaptedSequence = String.format("%s%s%d", year, month, adaptedDay);
        try {
            format.parse("19" + adaptedSequence);
        } catch (ParseException e) {
            try {
                format.parse("20" + adaptedSequence);
            } catch (ParseException e1) {
                return false;
            }
            return false;
        }
        return true;
    }
}
