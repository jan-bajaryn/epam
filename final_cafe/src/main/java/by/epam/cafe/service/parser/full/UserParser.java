package by.epam.cafe.service.parser.full;

import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.parser.helper.ValidateAndPutter;
import by.epam.cafe.service.parser.parts.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public class UserParser {


    private static final Logger log = LogManager.getLogger(UserParser.class);

    private final ValidateAndPutter validateAndPutter = ValidateAndPutter.getInstance();

    private final EmailParser emailParser = new EmailParser();
    private final FloorParser floorParser = new FloorParser();
    private final HouseParser houseParser = new HouseParser();
    private final NameParser nameParser = new NameParser();
    private final PasswordParser passwordParser = new PasswordParser();
    private final PhoneParser phoneParser = new PhoneParser();
    private final PorchParser porchParser = new PorchParser();
    private final RoleParser roleParser = new RoleParser();
    private final RoomParser roomParser = new RoomParser();
    private final StreetParser streetParser = new StreetParser();
    private final UsernameParser usernameParser = new UsernameParser();
    private final SurnameParser surnameParser = new SurnameParser();
    private final IdParser idParser = new IdParser();


    public User parseUser(Map<String, String> redirect, String usernameParam, String passwordParam, String roleParam, String nameParam, String surnameParam, String houseParam, String roomParam, String porchParam, String floorParam, String phoneParam, String emailParam, String streetParam) {
        Optional<String> username = usernameParser.parse(usernameParam);
        Optional<String> password = passwordParser.parse(passwordParam);
        Optional<Role> role = roleParser.parse(roleParam);
        Optional<String> name = nameParser.parse(nameParam);
        Optional<String> surname = surnameParser.parse(surnameParam);
        Optional<String> house = houseParser.parse(houseParam);
        Optional<String> room = roomParser.parse(roomParam);
        Optional<Integer> porch = porchParser.parse(porchParam);
        Optional<Integer> floor = floorParser.parse(floorParam);
        Optional<String> phone = phoneParser.parse(phoneParam);
        Optional<String> email = emailParser.parse(emailParam);
        Optional<String> street = streetParser.parse(streetParam);

        boolean result = validateAndPutter.validateAndPut(redirect, username, "username", usernameParam) &
                validateAndPutter.validateAndPut(redirect, password, "password", passwordParam) &
                validateAndPutter.validateAndPut(redirect, role, "role", roleParam) &
                validateAndPutter.validateAndPut(redirect, name, "name", nameParam) &
                validateAndPutter.validateAndPut(redirect, surname, "surname", surnameParam) &
                validateAndPutter.validateAndPut(redirect, house, "house", houseParam) &
                validateAndPutter.validateAndPut(redirect, room, "room", roomParam) &
                validateAndPutter.validateAndPut(redirect, porch, "porch", porchParam) &
                validateAndPutter.validateAndPut(redirect, floor, "floor", floorParam) &
                validateAndPutter.validateAndPut(redirect, phone, "phone", phoneParam) &
                validateAndPutter.validateAndPut(redirect, email, "email", emailParam) &
                validateAndPutter.validateAndPut(redirect, street, "street", streetParam);

        if (result) {
            return User.newBuilder()
                    .username(username.get())
                    .password(password.get())
                    .role(role.get())
                    .name(name.get())
                    .surname(surname.get())
                    .house(house.get())
                    .room(room.get())
                    .porch(porch.get())
                    .floor(floor.get())
                    .phone(phone.get())
                    .email(email.get())
                    .creation(LocalDateTime.now())
                    .isBlocked(false)
                    .street(street.get())
                    .build();
        } else {
            return null;
        }
    }


    public User parseUserWithId(Map<String, String> redirect, String id, String usernameParam, String passwordParam, String roleParam, String nameParam, String surnameParam, String houseParam, String roomParam, String porchParam, String floorParam, String phoneParam, String emailParam, String streetParam) {
        User user = parseUser(redirect, usernameParam, passwordParam, roleParam, nameParam, surnameParam, houseParam, roomParam, porchParam, floorParam, phoneParam, emailParam, streetParam);
        Optional<Integer> idOpt = idParser.parse(id);
        boolean idCheck = validateAndPutter.validateAndPut(redirect, idOpt, "id", id);
        if (idCheck) {
            user.setId(idOpt.get());
            return user;
        } else {
            return null;
        }
    }


}
