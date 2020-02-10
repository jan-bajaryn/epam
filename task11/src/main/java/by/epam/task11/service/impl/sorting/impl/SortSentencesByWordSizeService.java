package by.epam.task11.service.impl.sorting.impl;

import by.epam.task11.entities.Component;
import by.epam.task11.entities.Composite;
import by.epam.task11.service.impl.sorting.CompositeSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortSentencesByWordSizeService implements CompositeSort {
    public void sort(Composite composite) {
        List<Component> paragraphs = levelDown(composite);

        removeParagraphs(composite, paragraphs);

        for (Component paragraph : paragraphs) {
            List<Component> sentences = levelDown(paragraph);
            for (Component sentence : sentences) {
                paragraph.remove(sentence);
            }
            sentences.sort(Comparator.comparingInt(Component::size));
            for (Component sentence : sentences) {
                paragraph.add(sentence);
            }
        }

        addParagraphs(composite, paragraphs);
    }

    private void addParagraphs(Composite composite, List<Component> paragraphs) {
        for (Component paragraph : paragraphs) {
            composite.add(paragraph);
        }
    }

    private void removeParagraphs(Composite composite, List<Component> paragraphs) {
        for (Component paragraph : paragraphs) {
            composite.remove(paragraph);
        }
    }


    private List<Component> levelDown(Component composite) {
        List<Component> components = new ArrayList<>();
        for (int i = 0; i < composite.size(); i++) {
            components.add(composite.getChild(i));
        }
        return components;
    }
}
