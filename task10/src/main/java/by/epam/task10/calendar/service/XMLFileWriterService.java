package by.epam.task10.calendar.service;

import by.epam.task10.calendar.dao.XMLFileWriter;
import by.epam.task10.calendar.entity.Calendar;

import java.io.FileNotFoundException;

public class XMLFileWriterService {
    private XMLFileWriter xmlFileWriter = new XMLFileWriter();

    public void write(Calendar calendar, String fileName) throws FileNotFoundException {
        xmlFileWriter.writeCalendarToXML(calendar, fileName);
    }
}
