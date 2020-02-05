package by.epam.task11.entities;

public interface Component {

    CompType calcType();

    void add(Component c);

    Component getChild(int index);

    String operation();

    void remove(Component c);

    int size();
}
