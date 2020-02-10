package by.epam.task11.service.impl.sorting.impl;

import by.epam.task11.entities.Component;
import by.epam.task11.entities.Composite;
import by.epam.task11.service.impl.sorting.CompositeSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortParagraphsBySentService {
    public void sort(Composite composite) {
        List<Component> components = new ArrayList<>();
        takeParagraphs(composite, components);
        for (Component component : components) {
            composite.remove(component);
        }
        components.sort(Comparator.comparingInt(Component::size));
        for (Component component : components) {
            composite.add(component);
        }
    }

    private void takeParagraphs(Composite composite, List<Component> components) {
        for (int i = 0; i < composite.size(); i++) {
            components.add(composite.getChild(i));
        }
    }
}
