package by.epam.task11.service.sorting.impl;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Component;
import by.epam.task11.entities.Composite;
import by.epam.task11.service.FileInformationReader;
import by.epam.task11.service.finder.ComponentsByTypeFinder;
import by.epam.task11.service.finder.ComponentsByTypeFinderImpl;
import by.epam.task11.service.finder.ChildFinder;
import by.epam.task11.service.sorting.CompositeSort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

public class SortByChildSize implements CompositeSort {

    private static final Logger log = LogManager.getLogger(SortByChildSize.class);


    public final ComponentsByTypeFinder finder = new ComponentsByTypeFinderImpl();
    private final ChildFinder childFinder = new ChildFinder();

    @Override
    public void sort(Composite composite, CompType type) {

        log.info("sort: composite = {}, type = {}", composite, type);

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
