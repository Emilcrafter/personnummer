package personnummer;

public class NumberClassifier {
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
