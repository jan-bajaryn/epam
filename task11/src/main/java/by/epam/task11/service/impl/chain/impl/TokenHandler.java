package by.epam.task11.service.impl.chain.impl;

import by.epam.task11.entities.CompType;
import by.epam.task11.service.impl.chain.AbstractHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TokenHandler extends AbstractHandler {
    public TokenHandler(AbstractHandler nextHandler) {
        super(nextHandler);
    }

    public TokenHandler() {
    }

    @Override
    public List<String> handleRequest(String text) {
        return Arrays.stream(text.split("\\s+"))
                .collect(Collectors.toList());
    }

    @Override
    public CompType type() {
        return CompType.TOKEN;
    }
}
