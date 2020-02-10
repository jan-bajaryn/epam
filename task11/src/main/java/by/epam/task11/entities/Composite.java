package by.epam.task11.entities;

import java.util.ArrayList;

public abstract class Composite implements Component {

    private ArrayList<Component> components = new ArrayList<>();

    public abstract String format(String data);

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
        return format(components.stream()
                .reduce("", (a, b) -> a + b.operation(), (a, b) -> a + b));
    }

    @Override
    public void remove(Component c) {
        components.remove(c);
    }

    @Override
    public int size() {
        return components.size();
    }

    public abstract CompType type();

}
