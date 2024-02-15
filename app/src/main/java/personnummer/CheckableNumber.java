package personnummer;

/**
 * Abstract class used to represent one of several types of id numbers
 * used by the Swedish government. Contains implementations of the checks that
 * are common to all types of numbers, such as the Luhn algorithm.
 */
public abstract class CheckableNumber {

    protected String number;
    protected CharSequence numberSequence;
    private Integer checkDigit;

    CheckableNumber(String number) {
        this.number = number;
    }
    abstract boolean hasValidFormat();
    protected abstract boolean hasValidRange();

    /**
     * Removes non-numeric characters from the number, such as hyphens or plus signs.
     */
    private void normalizeNumber() {
        this.number = this.number.replaceAll("[^0-9]", "");
    }

    /**
     * Computes the digit sum of a number n, given that 0 <= n <= 19.
     */
    private int digitSum(int digit) {
        if(digit <= 9) {
            return digit;
        }
        return digit - 9;
    }

    /**
     * Computes the correct check digit using the Luhn algorithm,
     * and then compares it to the present check digit.
     * @return true if the check digit is valid, false otherwise
     */
    private boolean hasValidCheckDigit() {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = numberSequence.charAt(i) - '0';
            if (i % 2 == 0) {
                digit *= 2;
            }
            sum += digitSum(digit);
        }
        // Subtracting from nearest multiple of 10
        return (10 - (sum % 10)) % 10 == checkDigit;
    }

    /**
     * Separates the significant sequence of numbers from the check digit,
     * depending on the length of the number.
     */
    private void disassembleNumber() {
        if(this.number.length() == 12) {
            this.numberSequence = this.number.subSequence(2, 11);
            this.checkDigit = Integer.parseInt(this.number.subSequence(11, 12).toString());
            return;
        }
        this.numberSequence = this.number.subSequence(0, 9);
        this.checkDigit = Integer.parseInt(this.number.substring(9, 10));
    }

    /**
     * Runs all the checks on a number to determine if it is a valid instance of the given child class
     * @return true if the number is valid, false otherwise
     */
    public boolean isValid() {
        if (!hasValidFormat()) {
            return false;
        }
        normalizeNumber();
        disassembleNumber();
        boolean validRange = hasValidRange();
        return validRange && hasValidCheckDigit();
    }








}