package personnummer;

import java.util.regex.Pattern;

public class OrganizationNumber extends CheckableNumber {

    /**
     * Checks if the number has a valid format.
     * We assume that numbers with or without a hyphen are valid.
     * @param number the number to check
     * @return true if the number has a valid format
     */
    protected boolean hasValidFormat(String number) {
        Pattern formatPattern = Pattern.compile("^(16)?\\d{6}-?\\d{4}$");
        return formatPattern.matcher(number).matches();
    }

    OrganizationNumber(String number) {
        super(number);
    }

    /**
     * Always returns true, since there is no need to check dates
     * for organization numbers.
     * @return true
     */
    @Override
    protected boolean hasValidRange() {
        return true;
    }
}
