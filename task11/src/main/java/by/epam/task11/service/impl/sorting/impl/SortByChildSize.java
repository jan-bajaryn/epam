package by.epam.task11.service.impl.sorting.impl;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Component;
import by.epam.task11.entities.Composite;
import by.epam.task11.service.impl.finder.ChildFinder;
import by.epam.task11.service.impl.finder.ComponentsByTypeFinder;
import by.epam.task11.service.impl.finder.ComponentsByTypeFinderImpl;
import by.epam.task11.service.impl.sorting.CompositeSort;

import java.util.Comparator;
import java.util.List;

public class SortByChildSize implements CompositeSort {
    public final ComponentsByTypeFinder finder = new ComponentsByTypeFinderImpl();
    private final ChildFinder childFinder = new ChildFinder();

    @Override
    public void sort(Composite composite, CompType type) {
        List<Component> components = finder.find(type, composite);
        for (Component component : components) {
            sortComponent(component);
        }
    }

    private void sortComponent(Component component) {
        List<Component> components = childFinder.childList(component);
        components.sort(Comparator.comparingInt(Component::size));
        removeAll(component, components);
        addAll(component, components);
    }

    private void addAll(Component component, List<Component> components) {
        for (Component value : components) {
            component.add(value);
        }
    }

    private void removeAll(Component component, List<Component> components) {
        for (Component value : components) {
            component.remove(value);
        }
    }
}
