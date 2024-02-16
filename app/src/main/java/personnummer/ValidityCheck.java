package personnummer;

public interface ValidityCheck {
    void failMessage(CheckableNumber number);
    boolean isValid(CheckableNumber number);
    default boolean runCheck(CheckableNumber number) {
        boolean result = isValid(number);
        if (!result) {
            failMessage(number);
        }
        return result;
    }
}

