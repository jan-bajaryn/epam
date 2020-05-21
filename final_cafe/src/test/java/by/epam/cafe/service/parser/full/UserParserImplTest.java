package by.epam.cafe.service.parser.full;

import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.service.parser.full.impl.UserParserImpl;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static by.epam.cafe.service.parser.helper.impl.ValidateAndPutter.POSTFIX;
import static org.testng.Assert.*;

public class UserParserImplTest {
    private final UserParser userParser = new UserParserImpl();

    @Test
    public void testParseUser() {
        Map<String, String> redirect = new HashMap<>();
        String usernameParam = "valodia";
        String passwordParam = "aA4k8TV$#";
        String roleParam = Role.CLIENT.name();
        String nameParam = "Володя";
        String surnameParam = "Петров";
        String houseParam = "2";
        String roomParam = "3";
        String porchParam = "";
        String floorParam = "2";
        String phoneParam = "111222333";
        String emailParam = "abc@gmail.com";
        String streetParam = "Ленина";

        User user = userParser.parseUser(redirect, usernameParam, passwordParam, roleParam, nameParam, surnameParam, houseParam, roomParam, porchParam, floorParam, phoneParam, emailParam, streetParam);

        System.out.println(redirect);
        assertNotNull(user);

    }


    @Test
    public void testParseUserNegative() {
        Map<String, String> redirect = new HashMap<>();
        String usernameParam = "valodia";
        String passwordParam = "aA4k8TV$#";
        String roleParam = Role.CLIENT.name();
        String nameParam = "Володя";
        String surnameParam = "Петров";
        String houseParam = "2";
        String roomParam = "3";
        String porchParam = "";
        String floorParam = "asd";
        String phoneParam = "111222333";
        String emailParam = "abc@gmail.com";
        String streetParam = "Ленина";

        User user = userParser.parseUser(redirect, usernameParam, passwordParam, roleParam, nameParam, surnameParam, houseParam, roomParam, porchParam, floorParam, phoneParam, emailParam, streetParam);

        System.out.println(redirect);
        assertNull(user);

    }

    @Test
    public void testParseUserErrMsg() {
        Map<String, String> redirect = new HashMap<>();
        String usernameParam = "valodia";
        String passwordParam = "aA4k8TV$#";
        String roleParam = Role.CLIENT.name();
        String nameParam = "Володя";
        String surnameParam = "Петров";
        String houseParam = "2";
        String roomParam = "3";
        String porchParam = "";
        String floorParam = "asd";
        String phoneParam = "111222333";
        String emailParam = "abc@gmail.com";
        String streetParam = "Ленина";

        userParser.parseUser(redirect, usernameParam, passwordParam, roleParam, nameParam, surnameParam, houseParam, roomParam, porchParam, floorParam, phoneParam, emailParam, streetParam);

        System.out.println(redirect);

        assertTrue(redirect.containsKey("floor" + POSTFIX));
    }

    @Test
    public void testParseUserMsg() {
        Map<String, String> redirect = new HashMap<>();
        String usernameParam = "valodia";
        String passwordParam = "aA4k8TV$#";
        String roleParam = Role.CLIENT.name();
        String nameParam = "Володя";
        String surnameParam = "Петров";
        String houseParam = "2";
        String roomParam = "3";
        String porchParam = "";
        String floorParam = "1";
        String phoneParam = "111222333";
        String emailParam = "abc@gmail.com";
        String streetParam = "Ленина";

        userParser.parseUser(redirect, usernameParam, passwordParam, roleParam, nameParam, surnameParam, houseParam, roomParam, porchParam, floorParam, phoneParam, emailParam, streetParam);

        System.out.println(redirect);

        assertTrue(redirect.containsKey("floor"));
    }

    @Test
    public void testParseUserWithId() {
        Map<String, String> redirect = new HashMap<>();
        String usernameParam = "valodia";
        String passwordParam = "aA4k8TV$#";
        String roleParam = Role.CLIENT.name();
        String nameParam = "Володя";
        String surnameParam = "Петров";
        String houseParam = "2";
        String roomParam = "3";
        String porchParam = "";
        String floorParam = "2";
        String phoneParam = "111222333";
        String emailParam = "abc@gmail.com";
        String streetParam = "Ленина";
        String id = "3";
        String isBlocked = "";
        User user = userParser.parseUserWithId(redirect, id, usernameParam, passwordParam, roleParam, nameParam, surnameParam, houseParam, roomParam, porchParam, floorParam, phoneParam, emailParam, streetParam, isBlocked);

        System.out.println(redirect);
        assertNotNull(user);

    }

    @Test
    public void testParseUserWithIdNegative() {
        Map<String, String> redirect = new HashMap<>();
        String usernameParam = "valodia";
        String passwordParam = "aA4k8TV$#";
        String roleParam = Role.CLIENT.name();
        String nameParam = "Володя";
        String surnameParam = "Петров";
        String houseParam = "2";
        String roomParam = "3";
        String porchParam = "";
        String floorParam = "asd";
        String phoneParam = "111222333";
        String emailParam = "abc@gmail.com";
        String streetParam = "Ленина";
        String id = "3";
        String isBlocked = "";
        User user = userParser.parseUserWithId(redirect, id, usernameParam, passwordParam, roleParam, nameParam, surnameParam, houseParam, roomParam, porchParam, floorParam, phoneParam, emailParam, streetParam, isBlocked);

        System.out.println(redirect);
        assertNull(user);

    }

    @Test
    public void testParseUserWithIdErrMsg() {
        Map<String, String> redirect = new HashMap<>();
        String usernameParam = "valodia";
        String passwordParam = "aA4k8TV$#";
        String roleParam = Role.CLIENT.name();
        String nameParam = "Володя";
        String surnameParam = "Петров";
        String houseParam = "2";
        String roomParam = "3";
        String porchParam = "";
        String floorParam = "asd";
        String phoneParam = "111222333";
        String emailParam = "abc@gmail.com";
        String streetParam = "Ленина";
        String id = "3";
        String isBlocked = "";
        userParser.parseUserWithId(redirect, id, usernameParam, passwordParam, roleParam, nameParam, surnameParam, houseParam, roomParam, porchParam, floorParam, phoneParam, emailParam, streetParam, isBlocked);

        System.out.println(redirect);
        assertTrue(redirect.containsKey("floor" + POSTFIX));

    }

    @Test
    public void testParseUserWithIdMsg() {
        Map<String, String> redirect = new HashMap<>();
        String usernameParam = "valodia";
        String passwordParam = "aA4k8TV$#";
        String roleParam = Role.CLIENT.name();
        String nameParam = "Володя";
        String surnameParam = "Петров";
        String houseParam = "2";
        String roomParam = "3";
        String porchParam = "";
        String floorParam = "1";
        String phoneParam = "111222333";
        String emailParam = "abc@gmail.com";
        String streetParam = "Ленина";
        String id = "3";
        String isBlocked = "";
        userParser.parseUserWithId(redirect, id, usernameParam, passwordParam, roleParam, nameParam, surnameParam, houseParam, roomParam, porchParam, floorParam, phoneParam, emailParam, streetParam, isBlocked);

        System.out.println(redirect);
        assertTrue(redirect.containsKey("floor"));

    }

    @Test
    public void testParseRegistrationUser() {
        Map<String, String> redirect = new HashMap<>();
        String usernameParam = "valodia";
        String passwordParam = "aA4k8TV$#";
        String nameParam = "Володя";
        String surnameParam = "Петров";
        String houseParam = "2";
        String roomParam = "3";
        String porchParam = "";
        String floorParam = "2";
        String phoneParam = "111222333";
        String emailParam = "abc@gmail.com";
        String streetParam = "Ленина";
        User user = userParser.parseRegistrationUser(redirect, emailParam, phoneParam, usernameParam, passwordParam, nameParam, surnameParam, streetParam, houseParam, roomParam, porchParam, floorParam);

        System.out.println(redirect);
        assertNotNull(user);
    }

    @Test
    public void testParseRegistrationUserNegative() {
        Map<String, String> redirect = new HashMap<>();
        String usernameParam = "valodia";
        String passwordParam = "aA4k8TV$#";
        String nameParam = "Володя";
        String surnameParam = "Петров";
        String houseParam = "2";
        String roomParam = "3";
        String porchParam = "";
        String floorParam = "asd";
        String phoneParam = "111222333";
        String emailParam = "abc@gmail.com";
        String streetParam = "Ленина";
        User user = userParser.parseRegistrationUser(redirect, emailParam, phoneParam, usernameParam, passwordParam, nameParam, surnameParam, streetParam, houseParam, roomParam, porchParam, floorParam);

        System.out.println(redirect);
        assertNull(user);
    }

    @Test
    public void testParseRegistrationUserErrMsg() {
        Map<String, String> redirect = new HashMap<>();
        String usernameParam = "valodia";
        String passwordParam = "aA4k8TV$#";
        String nameParam = "Володя";
        String surnameParam = "Петров";
        String houseParam = "2";
        String roomParam = "3";
        String porchParam = "";
        String floorParam = "asd";
        String phoneParam = "111222333";
        String emailParam = "abc@gmail.com";
        String streetParam = "Ленина";
        userParser.parseRegistrationUser(redirect, emailParam, phoneParam, usernameParam, passwordParam, nameParam, surnameParam, streetParam, houseParam, roomParam, porchParam, floorParam);

        System.out.println(redirect);
        assertTrue(redirect.containsKey("floor" + POSTFIX));

    }

    @Test
    public void testParseRegistrationUserMsg() {
        Map<String, String> redirect = new HashMap<>();
        String usernameParam = "valodia";
        String passwordParam = "aA4k8TV$#";
        String nameParam = "Володя";
        String surnameParam = "Петров";
        String houseParam = "2";
        String roomParam = "3";
        String porchParam = "";
        String floorParam = "asd";
        String phoneParam = "111222333";
        String emailParam = "abc@gmail.com";
        String streetParam = "Ленина";
        userParser.parseRegistrationUser(redirect, emailParam, phoneParam, usernameParam, passwordParam, nameParam, surnameParam, streetParam, houseParam, roomParam, porchParam, floorParam);

        System.out.println(redirect);
        assertTrue(redirect.containsKey("floor"));

    }
}