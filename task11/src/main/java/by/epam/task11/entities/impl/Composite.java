package by.epam.task11.entities.impl;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Component;

import java.util.ArrayList;

public class Composite implements Component {

    private final CompType type;
    private ArrayList<Component> components = new ArrayList<>();

    public Composite(CompType type) {
        this.type = type;
    }

    @Override
    public CompType calcType() {
        return type;
    }

    @Override
    public void add(Component c) {
        components.add(c);
    }

    @Override
    public Component getChild(int index) {
        return components.get(index);
    }

    @Override
    public String operation() {
        return components.stream()
                .reduce("", (a, b) -> a + b.operation(), (a, b) -> a + b);
    }

    @Override
    public void remove(Component c) {
        components.remove(c);
    }

    @Override
    public int size() {
        return components.size();
    }
}
