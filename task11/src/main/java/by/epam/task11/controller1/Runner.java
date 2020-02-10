package by.epam.task11.controller1;

import by.epam.task11.entities.Composite;
import by.epam.task11.service.impl.chain.impl.ParagraphHandler;
import by.epam.task11.service.impl.chain.impl.SentenceHandler;
import by.epam.task11.service.impl.chain.impl.TokenHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Runner {
    private static final Logger log = LogManager.getLogger(Runner.class);

    public static void main(String[] args) throws IOException {

        log.info("info");
        log.error("error");

        ParagraphHandler abstractHandler = new ParagraphHandler();
        SentenceHandler sentenceHandler = new SentenceHandler();
        TokenHandler tokenHandler = new TokenHandler();
        abstractHandler.setNextHandler(sentenceHandler);
        sentenceHandler.setNextHandler(tokenHandler);


        String s = new String(Files.readAllBytes(Paths.get("data/text.txt")));
        System.out.println(s);
        Composite chain = abstractHandler.chain(s);
        String operation = chain.operation();
        System.out.println(
                operation
        );
//        System.out.println(s.split("(\\.{3}|[.?!])").length);


    }
}
