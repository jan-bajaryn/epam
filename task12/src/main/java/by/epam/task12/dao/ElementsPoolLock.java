package by.epam.task12.dao;

import by.epam.task12.entity.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ElementsPoolLock {

    public static final ElementsPoolLock INSTANCE = new ElementsPoolLock();

    private ReentrantLock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();

    private List<Element> emptyElements = new ArrayList<>();
    private List<Element> elementCopies = new ArrayList<>();

    public ElementsPoolLock() {
    }

    public Element takeElement() {
        lock.lock();
        try {
            Element remove = null;
            if (!emptyElements.isEmpty()) {
                remove = emptyElements.remove(0);
            }
            return remove;
        } finally {
            condition.signalAll();
            lock.unlock();
        }
    }

    public void putElement(Element element) {
        lock.lock();
        try {
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
            condition.signalAll();
            lock.unlock();
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
        return emptyElements.isEmpty() && elementCopies.isEmpty();
    }

    public Condition getCondition() {
        return condition;
    }
}
