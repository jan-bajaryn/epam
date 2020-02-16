package by.epam.task11.service.finder;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Component;
import by.epam.task11.entities.Composite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComponentsByTypeFinderImpl implements ComponentsByTypeFinder {

    private static final Logger log = LogManager.getLogger(ComponentsByTypeFinderImpl.class);


    ChildFinder childFinder = new ChildFinder();

    @Override
    public List<Component> find(CompType type, Composite composite) {

        log.info("type = {}, composite = {}", type, composite);
        List<Component> x = check(type, composite);
        log.info("x = {}", x);
        if (x != null) {
            return x;
        }

        return findAllByType(composite, type);
    }

    private List<Component> findAllByType(Component component, CompType compType) {
        List<Component> temp = new ArrayList<>();
        List<Component> components = childFinder.childList(component);
        for (Component current : components) {
            int compare = compareTypes(current.type(), compType);
            if (compare == 0) {
                temp.add(current);
            }
            if (compare > 0) {
                temp.addAll(findAllByType(current, compType));
            }
        }
        log.info("temp.size = {}", temp.size());
        return temp;
    }

    private List<Component> check(CompType type, Composite composite) {
        if (composite == null || composite.size() == 0 || type == null) {
            return new ArrayList<>();
        }
        if (composite.type() == type) {
            return new ArrayList<>(Collections.singletonList(composite));
        }
        if (compareTypes(composite.type(), type) < 0) {
            return new ArrayList<>();
        }
        return null;
    }

    private int compareTypes(CompType first, CompType second) {
        return Integer.compare(first.ordinal(), second.ordinal());
    }

}
