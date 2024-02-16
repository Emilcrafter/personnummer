package personnummer;

/**
 * The number classifier checks the type of given number and returns it as a string.
 */
public class NumberClassifier {
    /**
     * Classifies the given number and returns the type as a string.
     * @param number the number to classify
     * @return the type of the number as a string, either "personal", "coordination", "organization" or "invalid"
     */
    public static String classify(String number) {
        if (new PersonalNumber(number).isValid()) {
            return "personal";
        }  else if (new CoordinationNumber(number).isValid()) {
            return "coordination";
        } else if (new OrganizationNumber(number).isValid()) {
            return "organization";
        } else {
            return "invalid";
        }
    }
}
