package by.epam.task10.calendar.controller;

import by.epam.task10.calendar.dao.Calendar;
import by.epam.task10.calendar.dao.XMLFileReader;
import by.epam.task10.calendar.dao.XMLFileWriter;
import by.epam.task10.calendar.entity.FreeCelebrity;
import by.epam.task10.calendar.entity.SpecDate;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
//        LocalDate now = LocalDate.now();
//        now = now.plus();
        XMLFileWriter xmlFileWriter = new XMLFileWriter();
        Calendar calendar = new Calendar();
        calendar.addSpecDay(new SpecDate(true,
                true,
                "this is very good selebrity",
                FreeCelebrity.NONE, LocalDate.now()));
        xmlFileWriter.writeCalendarToXML(calendar, "myxml.xml");

        XMLFileReader xmlFileReader = new XMLFileReader();
        Calendar myCalendar = xmlFileReader.xmlToCalendar("myxml.xml");
        Map<LocalDate, List<FreeCelebrity>> localDateListMap = myCalendar.calcDays(LocalDate.now().minus(Period.of(0, 0, 5)),
                LocalDate.now().plus(Period.of(0,0,2)));
        System.out.println(localDateListMap);

    }
}
