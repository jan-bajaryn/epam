package by.epam.task11.service.impl.finder;

import by.epam.task11.entities.Component;

import java.util.ArrayList;
import java.util.List;

public class ChildFinder {
    public List<Component> childList(Component composite) {
        List<Component> components = new ArrayList<>();
        for (int i = 0; i < composite.size(); i++) {
            components.add(composite.getChild(i));
        }
        return components;
    }
}
