package by.epam.task11.service.impl.chain.impl;

import by.epam.task11.entities.CompType;
import by.epam.task11.service.AbstractHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SentenceHandler extends AbstractHandler {
    @Override
    public List<String> handleRequest(String text) {
        return Arrays.stream(text.split("(?<=(\\.{3}|[.?!]))"))
                .collect(Collectors.toList());
    }

    @Override
    public CompType type() {
        return CompType.SENTENCE;
    }
}
