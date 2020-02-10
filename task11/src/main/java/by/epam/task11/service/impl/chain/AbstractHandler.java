package by.epam.task11.service.impl.chain;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Composite;
import by.epam.task11.entities.factory.CompositeFactory;
import by.epam.task11.entities.impl.Leaf;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public abstract class AbstractHandler {

    public AbstractHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public AbstractHandler() {
    }

    private CompositeFactory compositeFactory = new CompositeFactory();

    private AbstractHandler nextHandler = DefaultHandler.getDefaultHandler();

    public void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract List<String> handleRequest(String text);

    public abstract CompType type();

    public Composite chain(String text) {
        List<String> strings = handleRequest(text);
        Composite composite = compositeFactory.create(type());
        List<Composite> collect = strings.stream()
                .map(s -> nextHandler.chain(s))
                .collect(Collectors.toList());

        for (Composite c : collect) {
            composite.add(c);
        }

        return composite;
    }

    private static class DefaultHandler extends AbstractHandler {
        private CompositeFactory compositeFactory = new CompositeFactory();

        private static AbstractHandler defaultHandler = new DefaultHandler();

        public static AbstractHandler getDefaultHandler() {
            return defaultHandler;
        }

        @Override
        public Composite chain(String text) {
            Composite composite = compositeFactory.create(type());
            List<Leaf> collect = text.chars().mapToObj(i -> (char) i)
                    .map(Leaf::new)
                    .collect(Collectors.toList());
            for (Leaf leaf : collect) {
                composite.add(leaf);
            }
            return composite;
        }

        @Override
        public List<String> handleRequest(String text) {
            return new ArrayList<>();
        }

        @Override
        public CompType type() {
            return CompType.LETTER;
        }
    }
}
