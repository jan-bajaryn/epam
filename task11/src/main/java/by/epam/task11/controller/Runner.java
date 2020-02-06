package by.epam.task11.controller;

import by.epam.task11.entities.Composite;
import by.epam.task11.entities.impl.Paragraph;
import by.epam.task11.service.AbstractHandler;
import by.epam.task11.service.impl.ParagraphHandler;
import by.epam.task11.service.impl.SentenceHandler;
import by.epam.task11.service.impl.TokenHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Runner {
    public static void main(String[] args) throws IOException {
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
