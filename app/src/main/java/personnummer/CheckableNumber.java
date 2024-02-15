package personnummer;

import java.util.regex.Pattern;

public abstract class CheckableNumber {

    private String number;
    private CharSequence extraNumber;
    protected CharSequence numberSequence;
    private Integer checkDigit;
    protected Pattern formatPattern;

    CheckableNumber(String number) {
        this.number = number;
    }
    abstract boolean hasValidFormat(String number);
    protected abstract boolean hasValidRange();

    /**
     * Removes eventual characters from the number, such as hyphens or plus signs.
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

    private void disassembleNumber() {
        if(this.number.length() == 12) {
            this.extraNumber = this.number.subSequence(0, 2);
            this.numberSequence = this.number.subSequence(2, 11);
            this.checkDigit = Integer.parseInt(this.number.subSequence(11, 12).toString());
            return;
        }
        this.numberSequence = this.number.subSequence(0, 9);
        this.checkDigit = Integer.parseInt(this.number.substring(9, 10));
    }

    public boolean isValid() {
        if (!hasValidFormat(this.number)) {
            return false;
        }
        normalizeNumber();
        disassembleNumber();
        boolean validRange = hasValidRange();
        return validRange && hasValidCheckDigit();
    }








}