package by.epam.cafe.service.encryption;

import by.epam.cafe.entity.db.impl.User;

public interface ApplicationEncrypt {
    String calcRegistrationToken(User user);

    String calcUserPasswordHash(String password);
}
