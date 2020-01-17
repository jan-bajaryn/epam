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
import java.time.Period;

public class XMLFileWriter {
    public void writeCalendarToXML(Calendar calendar, String fileName) throws FileNotFoundException {
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
            e.setPersistenceDelegate(Period.class,
                    new PersistenceDelegate() {
                        @Override
                        protected Expression instantiate(Object period, Encoder out) {
                            return new Expression(period,
                                    Period.class,
                                    "parse",
                                    new Object[]{period.toString()});
                        }
                    });
            e.writeObject(calendar);
        }
    }
}
