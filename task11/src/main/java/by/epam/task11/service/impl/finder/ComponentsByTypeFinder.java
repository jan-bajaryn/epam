package by.epam.task11.service.impl.finder;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Component;
import by.epam.task11.entities.Composite;

import java.util.List;

public interface ComponentsByTypeFinder {
    List<Component> find(CompType type, Composite composite);

}
