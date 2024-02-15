package personnummer;

import java.util.regex.Pattern;

public class OrganizationNumber extends CheckableNumber {

    /**
     * Checks if the number has a valid format.
     * We assume that numbers with or without a hyphen are valid.
     * @return true if the number has a valid format
     */
    protected boolean hasValidFormat() {
        Pattern formatPattern = Pattern.compile("^(16)?\\d{6}-?\\d{4}$");
        return formatPattern.matcher(this.number).matches();
    }

    OrganizationNumber(String number) {
        super(number);
    }

    /**
     * Check that the middle two numbers are at least 20.
     * @return true if the number is valid
     */
    @Override
    protected boolean hasValidRange() {
        String middle = this.numberSequence.subSequence(2, 4).toString();
        return Integer.parseInt(middle) >= 20;
    }
}
