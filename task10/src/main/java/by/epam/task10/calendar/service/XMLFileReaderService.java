package by.epam.task10.calendar.service;

import by.epam.task10.calendar.dao.XMLFileReader;
import by.epam.task10.calendar.entity.Calendar;

import java.io.FileNotFoundException;

public class XMLFileReaderService {
    private XMLFileReader xmlFileReader = new XMLFileReader();

    public Calendar read(String fileName) throws FileNotFoundException {
        return xmlFileReader.xmlToCalendar(fileName);
    }
}
