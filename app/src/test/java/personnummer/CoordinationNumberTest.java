package personnummer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoordinationNumberTest {

    @Test
    public void test10digitValidCoordinationNumber() {
        String[] numbers = {
                "2212732396",
                "221273-2396",
                "221273+2396",
                "0910799824",
                "091079-9824",
                "091079+9824"
        };
        for (String number : numbers) {
            CoordinationNumber coordinationNumber = new CoordinationNumber(number);
            assertTrue(coordinationNumber.isValid(), number + " should be a valid coordination number");
        }
    }

    @Test
    public void test12digitValidCoordinationNumber() {
        String[] numbers = {
                "202212732396",
                "20221273-2396",
                "190910799824",
                "19091079-9824"
        };
        for (String number : numbers) {
            CoordinationNumber coordinationNumber = new CoordinationNumber(number);
            assertTrue(coordinationNumber.isValid(), number + " should be a valid coordination number");
        }
    }



    @Test
    public void test10digitInvalidCoordinationNumber() {
        String[] numbers = {
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
                "091079_9824"
        };
        for (String number : numbers) {
            CoordinationNumber coordinationNumber = new CoordinationNumber(number);
            assertFalse(coordinationNumber.isValid(), number + " should not be a valid coordination number");
        }
    }


}
