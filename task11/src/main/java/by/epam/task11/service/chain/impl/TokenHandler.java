package by.epam.task11.service.chain.impl;

import by.epam.task11.entities.CompType;
import by.epam.task11.service.chain.AbstractHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TokenHandler extends AbstractHandler {

    public static final String REGEX = "\\s+";
    private static final Logger log = LogManager.getLogger(TokenHandler.class);


    public TokenHandler(AbstractHandler nextHandler) {
        super(nextHandler);
    }

    public TokenHandler() {
    }

    @Override
    public List<String> handleRequest(String text) {
        log.info("text = {}", text);
        return Arrays.stream(text.split(REGEX))
                .collect(Collectors.toList());
    }

    @Override
    public CompType type() {
        return CompType.TOKEN;
    }
}
