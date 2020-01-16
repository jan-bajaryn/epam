package by.epam.task10.calendar.dao;

import by.epam.task10.calendar.entity.Calendar;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class XMLFileReader {
    public Calendar xmlToCalendar(String fileName) throws FileNotFoundException {
        try (XMLDecoder d = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(fileName)))) {
            return (Calendar) d.readObject();
        }
    }
}
