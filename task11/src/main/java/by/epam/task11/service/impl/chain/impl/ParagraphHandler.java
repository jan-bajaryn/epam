package by.epam.task11.service.impl.chain.impl;

import by.epam.task11.entities.CompType;
import by.epam.task11.service.impl.chain.AbstractHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParagraphHandler extends AbstractHandler {
    public ParagraphHandler(AbstractHandler nextHandler) {
        super(nextHandler);
    }

    public ParagraphHandler() {
    }

    @Override
    public List<String> handleRequest(String text) {
        return Arrays.stream(text.trim().split("[\n\r]+\\s{2}"))
                .collect(Collectors.toList());
    }

    @Override
    public CompType type() {
        return CompType.PARAGRAPH;
    }
}
