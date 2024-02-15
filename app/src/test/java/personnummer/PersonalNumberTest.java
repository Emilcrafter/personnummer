package personnummer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonalNumberTest {

    @Test
    public void test10digitValidPersonalNumber() {
        String[] numbers = {
                "811218-9876",
                "8112189876",
                "0011088317",
                "001108-8317"
        };
        for (String number : numbers) {
            PersonalNumber personalNumber = new PersonalNumber(number);
            assertTrue(personalNumber.isValid(), number + " should be a valid personal number");
        }
    }

    @Test
    public void test10digitInvalidPersonalNumber() {
        String[] numbers = {
                "800102-2385",
                "8001022385",
                "0011088318",
                "001108-8318",
                "001108-831",
                "001108831",
                "001108-83171",
                "00110883171",
                "001108_8317"
        };

        for (String number : numbers) {
            PersonalNumber personalNumber = new PersonalNumber(number);
            assertFalse(personalNumber.isValid(), number + " should not be a valid personal number");
        }
    }

    @Test
    public void test12digitValidPersonalNumber() {
        String[] numbers = {
                "198001262388",
                "19800126-2388",
                "200001102383",
                "20000110-2383"
        };
        for (String number : numbers) {
            PersonalNumber personalNumber = new PersonalNumber(number);
            assertTrue(personalNumber.isValid(), number + " should be a valid personal number");
        }
    }

    @Test
    public void test12digitInvalidPersonalNumber() {
        String[] numbers = {
                "198001262387",
                "198001262387",
                "200001102382",
                "20000110-2382",
                "20000110-238",
                "20000110238",
                "20000110-23831",
                "2000011023831",
                "20000110_2383"
        };
        for (String number : numbers) {
            PersonalNumber personalNumber = new PersonalNumber(number);
            assertFalse(personalNumber.isValid(), number + " should not be a valid personal number");
        }
    }

}
