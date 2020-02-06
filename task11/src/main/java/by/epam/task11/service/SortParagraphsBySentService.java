package by.epam.task11.service;

import by.epam.task11.entities.Component;
import by.epam.task11.entities.Composite;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortParagraphsBySentService {
    public void sort(Composite composite) {
        List<Component> components = new ArrayList<>();
        for (int i = 0; i < composite.size(); i++) {
            components.add(composite.getChild(i));
        }
        for (Component component : components) {
            composite.remove(component);
        }
        components.sort(new Comparator<Component>() {
            @Override
            public int compare(Component o1, Component o2) {
                return o1.size() - o2.size();
            }
        });
        for (Component component : components) {
            composite.add(component);
        }
    }
}
