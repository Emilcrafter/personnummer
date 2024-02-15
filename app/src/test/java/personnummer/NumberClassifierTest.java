package personnummer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumberClassifierTest {

    @Test
    public void testPersonalNumberClassification() {
        String[] numbers = {
                "811218-9876",
                "8112189876",
                "0011088317",
                "001108-8317",
                "198001262388",
                "19800126-2388",
                "200001102383",
                "20000110-2383",
                "19021214-9819",
                "190910199827",
                "191006089807",
                "192109099180",
                "4607137454",
                "194510168885",
                "900118+9811",
                "189102279800",
                "189912299816"
        };
        for (String number : numbers) {
            String result = NumberClassifier.classify(number);
            assertEquals(result, "personal", number + " should be classified as a valid personal number, but was classified as " + result);
        }
    }

    @Test
    public void testOrganizationNumberClassification() {
        String[] numbers = {
                "556614-3185",
                "5566143185",
                "2620001111",
                "262000-1111",
                "8572027566",
                "857202-7566",
                "16556614-3185",
                "165566143185",
                "162620001111",
                "16262000-1111",
                "168572027566",
                "16857202-7566"
        };
        for (String number : numbers) {
            String result = NumberClassifier.classify(number);
            assertEquals(result, "organization", number + " should be classified as a valid organization number, but was classified as " + result);
        }
    }

    @Test
public void testCoordinationNumberClassification() {
    String[] numbers = {
            "2212732396",
            "221273-2396",
            "221273+2396",
            "0910799824",
            "091079-9824",
            "091079+9824",
            "202212732396",
            "20221273-2396",
            "190910799824",
            "19091079-9824"
    };
    for (String number : numbers) {
        String result = NumberClassifier.classify(number);
        assertEquals(result, "coordination", number + " should be classified as a valid coordination number, but was classified as " + result);
    }
}
@Test
public void testInvalidNumberClassification(){
    String[] numbers = {
            "811218-9875",
            "8112189875",
            "0011088316",
            "001108-8316",
            "198001262387",
            "19800126-2387",
            "200001102382",
            "20000110-2382",
            "556614-3184",
            "5566143184",
            "2620001110",
            "262000-1110",
            "8572027565",
            "857202-7565",
            "16556614-3184",
            "165566143184",
            "162620001110",
            "16262000-1110",
            "168572027565",
            "16857202-7565",
            "2212732395",
            "221273-2395",
            "221273+2395",
            "0910799823",
            "091079-9823",
            "091079+9823",
            "091079982",
            "091079-982",
            "091079+982",
            "09107998241",
            "091079-98241",
            "091079+98241",
            "091079_9824",
            "261000-1111",
    };
    for (String number : numbers) {
        String result = NumberClassifier.classify(number);
        assertEquals(result, "invalid", number + " should be classified as an invalid number, but was classified as " + result);
    }
}


}
