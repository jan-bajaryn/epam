package by.epam.task11.service.chain;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Composite;
import by.epam.task11.entities.factory.CompositeFactory;
import by.epam.task11.entities.impl.Leaf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public abstract class AbstractHandler {

    private static final Logger log = LogManager.getLogger(AbstractHandler.class);


    public AbstractHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public AbstractHandler() {
    }

    private CompositeFactory compositeFactory = new CompositeFactory();

    private AbstractHandler nextHandler = DefaultHandler.getDefHandler();

    public void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract List<String> handleRequest(String text);

    public abstract CompType type();

    public Composite chain(String text) {

        log.info("chain running with text = {}", text);
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
        private static final Logger log = LogManager.getLogger(DefaultHandler.class);

        private CompositeFactory compositeFactory = new CompositeFactory();

        private static AbstractHandler defHandler = new DefaultHandler();

        public static AbstractHandler getDefHandler() {
            return defHandler;
        }

        @Override
        public Composite chain(String text) {
            log.info("Default chain running with text = {}", text);

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
