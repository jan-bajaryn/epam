package by.epam.task11.service.impl.finder;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Component;
import by.epam.task11.entities.Composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ComponentsByTypeFinderImpl implements ComponentsByTypeFinder {
    @Override
    public List<Component> find(CompType type, Composite composite) {
        List<Component> x = check(type, composite);
        if (x != null) {
            return x;
        }

        return findAllByType(composite, type);
    }

    private List<Component> findAllByType(Component component, CompType compType) {
        List<Component> temp = new ArrayList<>();
        List<Component> components = childList(component);
        for (Component current : components) {
            int compare = compareTypes(current.type(), compType);
            if (compare == 0) {
                temp.add(current);
            }
            if (compare > 0) {
                temp.addAll(findAllByType(current, compType));
            }
        }
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

    private List<Component> childList(Component composite) {
        List<Component> components = new ArrayList<>();
        for (int i = 0; i < composite.size(); i++) {
            components.add(composite.getChild(i));
        }
        return components;
    }
}
