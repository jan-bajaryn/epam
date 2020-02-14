package by.epam.task11.service.sorting.impl;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Component;
import by.epam.task11.entities.Composite;
import by.epam.task11.entities.impl.Leaf;
import by.epam.task11.service.finder.ChildFinder;
import by.epam.task11.service.finder.ComponentsByTypeFinder;
import by.epam.task11.service.finder.ComponentsByTypeFinderImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SortTokenByLetterContains {

    private ComponentsByTypeFinder componentsByTypeFinder = new ComponentsByTypeFinderImpl();
    private ChildFinder childFinder = new ChildFinder();

    public void sort(Composite composite, Character element) {
        List<Component> components = componentsByTypeFinder.find(CompType.TOKEN, composite);
        int[] arr = new int[components.size()];
        List<Component> children = new ArrayList<>();

        readAndDeleteChildren(components, arr, children);
        sortChildren(element, children);
        returnBackChildren(components, arr, children);

    }

    private void returnBackChildren(List<Component> components, int[] arr, List<Component> children) {
        Iterator<Component> it = children.iterator();
        for (int i = 0; i < arr.length; i++) {
            Component current = components.get(i);
            for (int j = 0; j < arr[i]; j++) {
                current.add(it.next());
            }
        }
    }

    private void sortChildren(Character element, List<Component> children) {
        children.sort((f, s) -> {
            int result = containsSize(element, f) - containsSize(element, s);
            if (result == 0) {
                return f.operation().compareTo(s.operation());
            } else {
                return result;
            }
        });
    }

    private void readAndDeleteChildren(List<Component> components, int[] arr, List<Component> children) {
        for (int i = 0; i < components.size(); i++) {
            Component current = components.get(i);
            List<Component> find = childFinder.childList(current);
            children.addAll(find);
            arr[i] = find.size();
            removeAll(current, find);
        }
    }

    private void removeAll(Component current, List<Component> find) {
        for (Component component : find) {
            current.remove(component);
        }
    }

    private int containsSize(Character element, Component component) {
        int counter = 0;
        for (int i = 0; i < component.size(); i++) {
            Leaf leaf = (Leaf) component.getChild(i);
            if (element.equals(leaf.getElement())) {
                counter++;
            }
        }
        return counter;
    }
}
