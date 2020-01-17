package by.epam.task10.calendar.controller;

import by.epam.task10.calendar.controller.command.AddAllCalendarFile;
import by.epam.task10.calendar.dao.XMLFileReader;
import by.epam.task10.calendar.dao.XMLFileWriter;
import by.epam.task10.calendar.entity.Calendar;
import by.epam.task10.calendar.entity.FreeCelebrity;
import by.epam.task10.calendar.entity.factory.CalendarFactory;
import by.epam.task10.calendar.entity.factory.exception.IllegalCalendarParamsException;
import by.epam.task10.calendar.entity.factory.exception.IllegalRegularDayParamsException;
import by.epam.task10.calendar.entity.impl.CatholicEaster;
import by.epam.task10.calendar.entity.impl.RegularFreeCelebrity;
import by.epam.task10.calendar.entity.impl.SpecDate;
import by.epam.task10.calendar.parser.FormatRespList;
import by.epam.task10.calendar.view.communication.Request;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class Runner {

    public static void main(String[] args) throws FileNotFoundException, IllegalRegularDayParamsException, IllegalCalendarParamsException {
//

//        Calendar calendar = new Calendar();
////        calendar.addSpecDay(new SpecDate(true,
////                true,
////                "this is very good selebrity",
////                FreeCelebrity.NONE, LocalDate.now()));
//
//        XMLFileReader xmlFileReader = new XMLFileReader();
//        CalendarFactory calendarFactory = new CalendarFactory();
//        Calendar myCalendar = calendarFactory.create("new", new HashSet<>(), new HashSet<>(), new HashSet<>());
//
//        myCalendar.addRegularDay(RegularFreeCelebrity.builder()
//                .beginDate(LocalDate.of(2020, 1, 5))
//                .delta(Period.of(0, 0, 7))
//                .description("Regular sunday weekend")
//                .direction(RegularFreeCelebrity.Direction.TWO_WAYS)
//                .celebrity(false)
//                .free(true)
//                .name("Sunday weekend")
//                .build());
//
//        myCalendar.addRegularDay(RegularFreeCelebrity.builder()
//                .beginDate(LocalDate.of(2020, 1, 4))
//                .delta(Period.of(0, 0, 7))
//                .description("Regular saturday weekend")
//                .direction(RegularFreeCelebrity.Direction.TWO_WAYS)
//                .celebrity(false)
//                .free(true)
//                .name("Saturday weekend")
//                .build());
//
//        myCalendar.addRegularDay(RegularFreeCelebrity.builder()
//                .beginDate(LocalDate.of(2019, 1, 1))
//                .delta(Period.of(1, 0, 0))
//                .celebrity(true)
//                .description("New year celebration for all people!")
//                .direction(RegularFreeCelebrity.Direction.TWO_WAYS)
//                .free(true)
//                .name("New Year")
//                .build());
//
//        myCalendar.addSpecDay(new SpecDate(false,
//                true, "Boss gave me that weekend",
//                "Boss weekend", LocalDate.of(2020, 1, 18)));
//        myCalendar.addIrregularDay(new CatholicEaster());
//        XMLFileWriter xmlFileWriter = new XMLFileWriter();
//        xmlFileWriter.writeCalendarToXML(myCalendar, "myxml.xml");
//
//        myCalendar = xmlFileReader.xmlToCalendar("myxml.xml");
//
//        Map<LocalDate, List<FreeCelebrity>> localDateListMap = myCalendar.
//                calcDays(LocalDate.of(2018, 1, 1),
//                        LocalDate.of(2020, 7, 2));
//
//
//        FormatRespList formatRespList = new FormatRespList();
//        System.out.println(formatRespList.parse(localDateListMap));


//        LocalDate localDate = LocalDate.of(2004, 2, 29);
//        System.out.println(localDate);
//        System.out.println("localDate.plusDays(1) = " + localDate.plusDays(1));
//        System.out.println("localDate.minusDays(2).plusDays(2) = " + localDate.minusDays(2).plusDays(2));
//        System.out.println("localDate.plusYears(1).plusYears(1).plusYears(1).plusYears(1 ) = " + localDate.plusYears(1).plusYears(1).plusYears(1).plusYears(1));
//        Period of = Period.of(1, 2, 3);
//        System.out.println("of.getDays() = " + of.getDays());


//        Period period= Period.of(3,4,5);
//        System.out.println("period.toString() = " + period.toString());
//        System.out.println("Period.parse(period.toString()) = " + Period.parse(period.toString()));


//        RegularFreeCelebrity build = RegularFreeCelebrity.builder()
//                .beginDate(LocalDate.of(2020, 1, 5))
//                .delta(Period.of(0, 0, 7))
//                .description("Regular sunday weekend")
//                .direction(RegularFreeCelebrity.Direction.TWO_WAYS)
//                .celebrity(false)
//                .free(true)
//                .name("Sunday weekend")
//                .build();
//        build.isThatDay(LocalDate.of(2019, 12, 1));





        Controller controller = new Controller();
        controller.run();
//        Request request = new Request();
//        request.setFileName("myxml.xml");
//        new AddAllCalendarFile().execute(request);
    }
}
