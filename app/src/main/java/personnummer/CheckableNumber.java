package personnummer;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Abstract class used to represent one of several types of id numbers
 * used by the Swedish government. Contains implementations of the checks that
 * are common to all types of numbers, such as the Luhn algorithm.
 */
public abstract class CheckableNumber {

    protected String number;

    protected String normalizedNumber;

    protected CharSequence numberSequence;

    Integer checkDigit;


    protected List<ValidityCheck> validityCheckList = new java.util.ArrayList<>();

    CheckableNumber(String number) {
        this.number = number;
        this.normalizeNumber();
        this.disassembleNumber();
        this.addCheck(new LuhnCheck());
    }

    protected void addCheck(ValidityCheck validityCheck) {
        this.validityCheckList.add(validityCheck);
    }

    /**
     * Removes non-numeric characters from the number, such as hyphens or plus signs.
     */
    private void normalizeNumber() {
        this.normalizedNumber = this.number.replaceAll("[^0-9]", "");
    }


    /**
     * Separates the significant sequence of numbers from the check digit,
     * depending on the length of the number.
     */
    private void disassembleNumber() {
        if(this.normalizedNumber.length() == 12) {
            this.numberSequence = this.normalizedNumber.subSequence(2, 11);
            this.checkDigit = Integer.parseInt(this.normalizedNumber.subSequence(11, 12).toString());
            return;
        } else if (this.normalizedNumber.length() != 10) {
            this.normalizedNumber = "0000000000";
        }
        this.numberSequence = this.normalizedNumber.subSequence(0, 9);
        this.checkDigit = this.normalizedNumber.charAt(9) - '0';
    }

    /**
     * Runs all the checks on a number to determine if it is a valid instance of the given child class
     * @return true if the number is valid, false otherwise
     */
    public final boolean isValid(){
        System.out.printf("Checking if number %s is a valid %s\n", this.number, this.getClass().toString().split("\\.")[1]);
        boolean didPass = true;
        for (ValidityCheck check : this.validityCheckList) {
            if (!check.runCheck(this)){
                didPass = false;
            }
        }
        return didPass;
    }
}
/**
 * Computes the correct check digit using the Luhn algorithm,
 * and then compares it to the present check digit.
 */
class LuhnCheck extends ValidityCheck {

    private int luhnSum(CharSequence number) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = number.charAt(i) - '0';
            if (i % 2 == 0) {
                digit *= 2;
            }
            if (digit > 9) {
                digit = digit - 9;
            }
            sum += digit;
        }
        return (10 - (sum % 10)) % 10;
    }
    @Override
    public boolean isValid(CheckableNumber number) {
        return luhnSum(number.numberSequence) == number.checkDigit;
    }

    @Override
    protected void failMessage(CheckableNumber number) {
        System.out.printf("Number %s does not pass the Luhn check\n", number.number);
    }
}

class FormatCheck extends ValidityCheck {
    private final Pattern format;
    FormatCheck(Pattern format) {
        this.format = format;
    }
    @Override
    public boolean isValid(CheckableNumber number) {
        return format.matcher(number.number).matches();
    }

    @Override
    protected void failMessage(CheckableNumber number) {
        System.out.printf("Number %s does not match format %s\n", number.number, format);
    }
}
