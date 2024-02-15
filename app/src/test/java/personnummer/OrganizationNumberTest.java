package personnummer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrganizationNumberTest {

    @Test
    public void test10digitValidOrganizationNumber() {
        String[] numbers = {
                "556614-3185",
                "5566143185",
                "2620001111",
                "262000-1111",
                "8572027566",
                "857202-7566"
        };
        for (String number: numbers) {
            OrganizationNumber organizationNumber = new OrganizationNumber(number);
            assertTrue(organizationNumber.isValid(), number + " should be a valid organization number");
        }
    }

    @Test
    public void test10digitInvalidOrganizationNumber() {
        String[] numbers = {
                "556614-3184",
                "5566143184",
                "2620001110",
                "262000-1110",
                "8572027565",
                "857202-7565",
                "857202756",
                "857202-756",
                "85720275661",
                "857202-75661",
                "857202_7566"
        };
        for (String number: numbers) {
            OrganizationNumber organizationNumber = new OrganizationNumber(number);
            assertFalse(organizationNumber.isValid(), number + " should not be a valid organization number");
        }
    }

    @Test
    public void test12digitValidOrganizationNumber() {
        String[] numbers = {
                "16556614-3185",
                "165566143185",
                "162620001111",
                "16262000-1111",
                "168572027566",
                "16857202-7566"
        };
        for (String number: numbers) {
            OrganizationNumber organizationNumber = new OrganizationNumber(number);
            assertTrue(organizationNumber.isValid(), number + " should be a valid organization number");
        }
    }

    @Test
    public void test12digitInvalidOrganizationNumber() {
        String[] numbers = {
                "165614-3184",
                "1656143184",
                "1620001110",
                "162000-1110",
                "2272027565",
                "167202-7565",
                "147202756",
                "107202-756",
                "16720275661",
                "157202-75661",
                "167202_7566"
        };
        for (String number: numbers) {
            OrganizationNumber organizationNumber = new OrganizationNumber(number);
            assertFalse(organizationNumber.isValid(), number + " should not be a valid organization number");
        }
    }
}
