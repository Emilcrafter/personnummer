package personnummer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class PersonalNumber extends CheckableNumber{


    public PersonalNumber(String number) {
        super(number);
    }
    /**
     * Checks if the number has a valid format.
     * We assume that numbers with or without a hyphen are valid.
     * Plus signs are allowed when the date is only 6 digits.
     * @return true if the number has a valid format
     */
    @Override
    protected boolean hasValidFormat() {
        Pattern formatPattern = Pattern.compile("^(\\d{6}[-+]?\\d{4})|(\\d{8}-?\\d{4})$");
        return formatPattern.matcher(this.number).matches();
    }

    /**
     * Check that the personal number refers to a valid date.
     * @return true if the number is valid
     */
    @Override
    public boolean hasValidRange() {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        format.setLenient(false);

        CharSequence relevantPart = this.numberSequence.subSequence(0,6);

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
