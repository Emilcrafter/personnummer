package personnummer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CoordinationNumber extends PersonalNumber {

    CoordinationNumber(String number) {
        super(number);
    }


    @Override
    public boolean hasValidRange() {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        format.setLenient(false);
        CharSequence year = this.numberSequence.subSequence(0, 2);
        CharSequence month = this.numberSequence.subSequence(2, 4);
        CharSequence day = this.numberSequence.subSequence(4, 6);
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
