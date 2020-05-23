package by.epam.cafe.service.parser.helper.impl;

import by.epam.cafe.service.parser.helper.NullIfEmptyService;

public class NullIfEmptyServiceImpl implements NullIfEmptyService {
    private static NullIfEmptyServiceImpl INSTANCE = new NullIfEmptyServiceImpl();

    public static NullIfEmptyServiceImpl getInstance() {
        return INSTANCE;
    }

    private NullIfEmptyServiceImpl() {
    }
    /**
     * @param param parameter to parse
     * @return String value of input
     * if input is empty, returns null
     */
    @Override
    public String nullIfEmptyString(String param) {
        return param == null || param.isEmpty() ? null : param;
    }


    /**
     * @param param parameter to parse
     * @return Integer value of param if it can be parse to Integer.
     * Or if input is empty returns null
     * @throws NumberFormatException if param can't be parse to Integer and it isn't empty
     */
    @Override
    public Integer nullIfEmptyInteger(String param) {
        return param == null || param.isEmpty() ? null : Integer.valueOf(param);
    }
}
