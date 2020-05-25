package by.epam.cafe.service.parser.full.impl;

import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.entity.struct.OptionalNullable;
import by.epam.cafe.service.db.UserService;
import by.epam.cafe.service.db.impl.UserServiceImpl;
import by.epam.cafe.service.encryption.ApplicationEncrypt;
import by.epam.cafe.service.encryption.impl.ApplicationEncryptImpl;
import by.epam.cafe.service.parser.helper.ValidateAndPutter;
import by.epam.cafe.service.parser.helper.impl.ValidateAndPutterImpl;
import by.epam.cafe.service.parser.parts.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Map;

public class UserParserImpl implements by.epam.cafe.service.parser.full.UserParser {


    private static final Logger log = LogManager.getLogger(UserParserImpl.class);
    private static final String BLOCKED = "blocked";

    private final ValidateAndPutter validateAndPutter = ValidateAndPutterImpl.getInstance();
    private final ApplicationEncrypt applicationEncrypt = ApplicationEncryptImpl.getInstance();
    private final EmailParser emailParser = EmailParser.getInstance();
    private final FloorParser floorParser = FloorParser.getInstance();
    private final HouseParserUser houseParserOrder = HouseParserUser.getInstance();
    private final NameParser nameParser = NameParser.getInstance();
    // make one more password parser nullable. This used
    private final PasswordParser passwordParser = PasswordParser.getInstance();
    private final PhoneParser phoneParser = PhoneParser.getInstance();
    private final PorchParser porchParser = PorchParser.getInstance();
    private final RoleParser roleParser = RoleParser.getInstance();
    private final RoomParser roomParser = RoomParser.getInstance();
    private final StreetParserUser streetParserOrder = StreetParserUser.getInstance();
    private final UsernameParser usernameParser = UsernameParser.getInstance();
    private final SurnameParser surnameParser = SurnameParser.getInstance();
    private final IdParser idParser = IdParser.getInstance();
    private final BooleanParser booleanParser = BooleanParser.getInstance();

    /**
     * @param redirect      Map to return what parameter is valid, and value with
     *                      what parameter was in input
     *                      First String in the map is the name of parameter
     *                      Second String in the map is value of input in parameter
     *                      or information about existing error in the map
     *                      For example {street, abcde} means that input for
     *                      parameter of name "street" was "abcde"
     *                      {street_error, true} means that in parameter
     *                      of name "street" was error.
     * @param usernameParam {@link User#getUsername()}
     * @param passwordParam {@link User#getPassword()}
     * @param roleParam     {@link User#getRole()}
     * @param nameParam     {@link User#getName()}
     * @param surnameParam  {@link User#getSurname()}
     * @param houseParam    {@link User#getHouse()}
     * @param roomParam     {@link User#getRoom()}
     * @param porchParam    {@link User#getPorch()}
     * @param floorParam    {@link User#getFloor()}
     * @param phoneParam    {@link User#getPhone()}
     * @param emailParam    {@link User#getEmail()}
     * @param streetParam   {@link User#getStreet()}
     * @return entity {@link User} with parsed parameter, or {@code null} if any parameter
     * is invalid
     */
    @Override
    public User parseUser(Map<String, String> redirect, String usernameParam, String passwordParam, String roleParam, String nameParam, String surnameParam, String houseParam, String roomParam, String porchParam, String floorParam, String phoneParam, String emailParam, String streetParam) {
        OptionalNullable<String> username = usernameParser.parse(usernameParam);
        OptionalNullable<String> password = passwordParser.parse(passwordParam);
        OptionalNullable<Role> role = roleParser.parse(roleParam);
        OptionalNullable<String> name = nameParser.parse(nameParam);
        OptionalNullable<String> surname = surnameParser.parse(surnameParam);
        OptionalNullable<String> house = houseParserOrder.parse(houseParam);
        OptionalNullable<String> room = roomParser.parse(roomParam);
        OptionalNullable<Integer> porch = porchParser.parse(porchParam);
        OptionalNullable<Integer> floor = floorParser.parse(floorParam);
        OptionalNullable<String> phone = phoneParser.parse(phoneParam);
        OptionalNullable<String> email = emailParser.parse(emailParam);
        OptionalNullable<String> street = streetParserOrder.parse(streetParam);

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

    /**
     * @param redirect      Map to return what parameter is valid, and value with
     *                      what parameter was in input
     *                      First String in the map is the name of parameter
     *                      Second String in the map is value of input in parameter
     *                      or information about existing error in the map
     *                      For example {street, abcde} means that input for
     *                      parameter of name "street" was "abcde"
     *                      {street_error, true} means that in parameter
     *                      of name "street" was error.
     * @param id            {@link User#getId()}
     * @param usernameParam {@link User#getUsername()}
     * @param passwordParam {@link User#getPassword()}
     * @param roleParam     {@link User#getRole()}
     * @param nameParam     {@link User#getName()}
     * @param surnameParam  {@link User#getSurname()}
     * @param houseParam    {@link User#getHouse()}
     * @param roomParam     {@link User#getRoom()}
     * @param porchParam    {@link User#getPorch()}
     * @param floorParam    {@link User#getFloor()}
     * @param phoneParam    {@link User#getPhone()}
     * @param emailParam    {@link User#getEmail()}
     * @param streetParam   {@link User#getStreet()}
     * @param isBlocked     {@link User#isBlocked()}
     * @return entity {@link User} with parsed parameter, or {@code null} if any parameter
     * is invalid
     */
    @Override
    public User parseUserWithId(Map<String, String> redirect, String id, String usernameParam, String passwordParam, String roleParam, String nameParam, String surnameParam, String houseParam, String roomParam, String porchParam, String floorParam, String phoneParam, String emailParam, String streetParam, String isBlocked) {
        User user = parseUser(redirect, usernameParam, passwordParam, roleParam, nameParam, surnameParam, houseParam, roomParam, porchParam, floorParam, phoneParam, emailParam, streetParam);
        OptionalNullable<Integer> idOpt = idParser.parse(id);
        OptionalNullable<Boolean> isBlockedOpt = booleanParser.parse(isBlocked);
        boolean idCheck = validateAndPutter.validateAndPut(redirect, idOpt, "id", id) &
                validateAndPutter.validateAndPut(redirect, isBlockedOpt, BLOCKED, isBlocked);

        if (user != null) {
            if (idCheck) {
                user.setId(idOpt.get());
                user.setBlocked(isBlockedOpt.get());
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * @param redirect      Map to return what parameter is valid, and value with
     *                      what parameter was in input
     *                      First String in the map is the name of parameter
     *                      Second String in the map is value of input in parameter
     *                      or information about existing error in the map
     *                      For example {street, abcde} means that input for
     *                      parameter of name "street" was "abcde"
     *                      {street_error, true} means that in parameter
     *                      of name "street" was error.
     * @param emailParam    {@link User#getEmail()}
     * @param phoneParam    {@link User#getPhone()}
     * @param usernameParam {@link User#getUsername()}
     * @param passwordParam {@link User#getPassword()}
     * @param nameParam     {@link User#getName()}
     * @param surnameParam  {@link User#getSurname()}
     * @param streetParam   {@link User#getStreet()}
     * @param houseParam    {@link User#getHouse()}
     * @param roomParam     {@link User#getRoom()}
     * @param porchParam    {@link User#getPorch()}
     * @param floorParam    {@link User#getFloor()}
     * @return entity {@link User} with parsed parameter, or {@code null} if any parameter
     * is invalid
     */
    @Override
    public User parseRegistrationUser(Map<String, String> redirect, String emailParam, String phoneParam, String usernameParam, String passwordParam, String nameParam, String surnameParam, String streetParam, String houseParam, String roomParam, String porchParam, String floorParam) {
        OptionalNullable<String> username = usernameParser.parse(usernameParam);
        OptionalNullable<String> password = passwordParser.parse(passwordParam);
        OptionalNullable<String> name = nameParser.parse(nameParam);
        OptionalNullable<String> surname = surnameParser.parse(surnameParam);
        OptionalNullable<String> house = houseParserOrder.parse(houseParam);
        OptionalNullable<String> room = roomParser.parse(roomParam);
        OptionalNullable<Integer> porch = porchParser.parse(porchParam);
        OptionalNullable<Integer> floor = floorParser.parse(floorParam);
        OptionalNullable<String> phone = phoneParser.parse(phoneParam);
        OptionalNullable<String> email = emailParser.parse(emailParam);
        OptionalNullable<String> street = streetParserOrder.parse(streetParam);

        boolean result = validateAndPutter.validateAndPut(redirect, username, "username", usernameParam) &
                validateAndPutter.validateAndPut(redirect, password, "password", passwordParam) &
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
                    .role(Role.CLIENT)
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

    @Override
    public User parseRegistrationUserWithToken(Map<String, String> redirect, String emailParam, String phoneParam, String usernameParam, String passwordParam, String nameParam, String surnameParam, String streetParam, String houseParam, String roomParam, String porchParam, String floorParam, String token) {
        User user = parseRegistrationUser(redirect, emailParam, phoneParam, usernameParam, passwordParam, nameParam, surnameParam, streetParam, houseParam, roomParam, porchParam, floorParam);
        log.debug("parseRegistrationUserWithToken: user = {}", user);
        log.debug("redirect = {}", redirect);
        OptionalNullable<String> tokenOpt = OptionalNullable.empty();
        if (user != null && applicationEncrypt.calcRegistrationToken(user).equals(token)) {
            tokenOpt = OptionalNullable.of(token);
        }
        boolean tokenResult = validateAndPutter.validateAndPut(redirect, tokenOpt, "token", token);

        if (user != null && tokenResult) {
            return user;
        }
        return null;
    }

    @Override
    public boolean parseWithBaseSelfChange(Map<String, String> redirect, User base, String nameParam, String surnameParam, String houseParam, String roomParam, String porchParam, String floorParam, String phoneParam, String streetParam) {
        OptionalNullable<String> name = nameParser.parse(nameParam);
        OptionalNullable<String> surname = surnameParser.parse(surnameParam);
        OptionalNullable<String> house = houseParserOrder.parse(houseParam);
        OptionalNullable<String> room = roomParser.parse(roomParam);
        OptionalNullable<Integer> porch = porchParser.parse(porchParam);
        OptionalNullable<Integer> floor = floorParser.parse(floorParam);
        OptionalNullable<String> phone = phoneParser.parse(phoneParam);
        OptionalNullable<String> street = streetParserOrder.parse(streetParam);

        boolean result =
                validateAndPutter.validateAndPut(redirect, name, "name", nameParam) &
                        validateAndPutter.validateAndPut(redirect, surname, "surname", surnameParam) &
                        validateAndPutter.validateAndPut(redirect, house, "house", houseParam) &
                        validateAndPutter.validateAndPut(redirect, room, "room", roomParam) &
                        validateAndPutter.validateAndPut(redirect, porch, "porch", porchParam) &
                        validateAndPutter.validateAndPut(redirect, floor, "floor", floorParam) &
                        validateAndPutter.validateAndPut(redirect, phone, "phone", phoneParam) &
                        validateAndPutter.validateAndPut(redirect, street, "street", streetParam);

        if (result) {
            base.setName(name.get());
            base.setSurname(surname.get());
            base.setHouse(house.get());
            base.setRoom(room.get());
            base.setPorch(porch.get());
            base.setFloor(floor.get());
            base.setPhone(phone.get());
            base.setStreet(street.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean parseChangePassword(Map<String, String> redirect, User base, String passwordOld, String passwordNew) {
        String password = base.getPassword();
//        if (password.equals(applicationEncrypt.calcUserPasswordHash(passwordOld))){
        if (password.equals(passwordOld)) {
            OptionalNullable<String> parse = passwordParser.parse(passwordNew);
            if (parse.isPresent()) {
                base.setPassword(parse.get());
                return true;
            } else {
                redirect.put("new_password_error", "true");
            }
        } else {
            redirect.put("old_password_error", "true");
        }
        return false;
    }
}