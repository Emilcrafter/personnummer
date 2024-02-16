package personnummer;

public abstract class ValidityCheck {
    protected abstract void failMessage(CheckableNumber number);
    abstract boolean isValid(CheckableNumber number);
    final boolean runCheck(CheckableNumber number) {
        boolean result = isValid(number);
        if (!result) {
            failMessage(number);
        }
        return result;
    }
}

