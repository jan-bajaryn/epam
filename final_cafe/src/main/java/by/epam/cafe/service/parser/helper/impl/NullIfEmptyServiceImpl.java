package by.epam.cafe.service.parser.helper.impl;

import by.epam.cafe.service.parser.helper.NullIfEmptyService;

public class NullIfEmptyServiceImpl implements NullIfEmptyService {
    @Override
    public String nullIfEmptyString(String param) {
        return param == null || param.isEmpty() ? null : param;
    }

    @Override
    public Integer nullIfEmptyInteger(String param) {
        return param == null || param.isEmpty() ? null : Integer.valueOf(param);
    }
}
