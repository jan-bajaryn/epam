package by.epam.bus.parser;

import by.epam.bus.parser.exception.IllegalFormatIntegerException;

public class TrackBusParser {
    public int parseTrackValue(String trackSt) throws IllegalFormatIntegerException {
        try {
            return Integer.parseInt(trackSt);
        } catch (NumberFormatException e) {
            throw new IllegalFormatIntegerException();
        }
    }

    public int parseYear(String yearSt) throws IllegalFormatIntegerException {
        try {
            return Integer.parseInt(yearSt);
        } catch (NumberFormatException e) {
            throw new IllegalFormatIntegerException();
        }
    }

    public int parseMillage(String millageSt) throws IllegalFormatIntegerException {
        try {
            return Integer.parseInt(millageSt);
        } catch (NumberFormatException e) {
            throw new IllegalFormatIntegerException();
        }
    }
}
