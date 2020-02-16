package by.epam.task11.service.finder;

import by.epam.task11.entities.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ChildFinder {
    private static final Logger log = LogManager.getLogger(ChildFinder.class);


    public List<Component> childList(Component composite) {
        log.info("composite = {}", composite);
        List<Component> components = new ArrayList<>();
        for (int i = 0; i < composite.size(); i++) {
            components.add(composite.getChild(i));
        }
        return components;
    }
}
