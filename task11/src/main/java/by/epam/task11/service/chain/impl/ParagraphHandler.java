package by.epam.task11.service.chain.impl;

import by.epam.task11.entities.CompType;
import by.epam.task11.service.chain.AbstractHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParagraphHandler extends AbstractHandler {
    private static final Logger log = LogManager.getLogger(ParagraphHandler.class);
    public static final String REGEX = "[\n\r]+\\s{2}";

    public ParagraphHandler(AbstractHandler nextHandler) {
        super(nextHandler);
    }

    public ParagraphHandler() {
    }

    @Override
    public List<String> handleRequest(String text) {
        log.info("running with text = {}", text);
        return Arrays.stream(text.trim().split(REGEX))
                .collect(Collectors.toList());
    }

    @Override
    public CompType type() {
        return CompType.PARAGRAPH;
    }
}
