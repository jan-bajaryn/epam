package by.epam.bus.parser;

import by.epam.bus.parser.exception.IllegalFormatIntegerException;

public class SizeParser {
    public int parseSize(String sizeSt) throws IllegalFormatIntegerException {
        try {
            return Integer.parseInt(sizeSt);
        } catch (NumberFormatException e) {
            throw new IllegalFormatIntegerException();
        }
    }
}
