package personnummer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class PersonalNumber extends CheckableNumber{


    public PersonalNumber(String number) {
        super(number);
    }

    @Override
    protected boolean hasValidFormat(String number) {
        Pattern formatPattern = Pattern.compile("^(\\d{6}[-+]?\\d{4})|(\\d{8}-?\\d{4})$");
        return formatPattern.matcher(number).matches();
    }

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
