package by.epam.task12.dao;

import by.epam.task12.entity.impl.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ElementsPool {

    private static final ElementsPool ELEMENTS_POOL = new ElementsPool();

    public static ElementsPool getInstance() {
        return ELEMENTS_POOL;
    }

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private List<Element> emptyElements = new ArrayList<>();
    private List<Element> elementCopies = new ArrayList<>();

    public ElementsPool() {
    }

    public Element takeElement() {
        try {
            lock.lock();

            Element remove = null;
            if (!emptyElements.isEmpty()) {
                remove = emptyElements.remove(0);
            }
            return remove;
        } finally {
            lock.unlock();
            condition.signalAll();
        }
    }

    public void putElement(Element element) {
        try {
            lock.lock();
            if (element != null && element.getValue() != null) {
                if (element.getValue() == 0) {
                    emptyElements.add(element);
                    Element result = containsCopyElement(element);
                    if (result == null) {
                        elementCopies.add(element);
                    }
                } else {
                    Element el = containsCopyElement(element);
                    if (el != null) {
                        removeByIdentity(el);
                    }
                }
            }
        } finally {
            lock.unlock();
            condition.signalAll();
        }
    }

    private void removeByIdentity(Element el) {
        for (int i = 0; i < elementCopies.size(); i++) {
            if (elementCopies.get(i) == el) {
                elementCopies.remove(i);
                return;
            }
        }
    }

    private Element containsCopyElement(Element element) {
        return elementCopies.stream()
                .filter(e -> e == element)
                .findAny().orElse(null);
    }

    public void clear() {
        emptyElements.clear();
        elementCopies.clear();
    }

    public boolean isEmpty() {
        return emptyElements.isEmpty();
    }

}
