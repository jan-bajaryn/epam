package by.epam.task10.calendar.dao;

import by.epam.task10.calendar.entity.Calendar;

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;

public class XMLFileWriter {
    public void writeCalendarToXML(Calendar calendar, String fileName) {
        try (XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            e.setPersistenceDelegate(LocalDate.class,
                    new PersistenceDelegate() {
                        @Override
                        protected Expression instantiate(Object localDate, Encoder encdr) {
                            return new Expression(localDate,
                                    LocalDate.class,
                                    "parse",
                                    new Object[]{localDate.toString()});
                        }
                    });
            e.writeObject(calendar);
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("ERROR: While Creating or Opening the File dvd.xml");
        }
    }
}
