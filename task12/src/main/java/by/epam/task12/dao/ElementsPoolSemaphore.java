package by.epam.task12.dao;

import by.epam.task12.entity.impl.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ElementsPoolSemaphore {

    private static final ElementsPoolSemaphore ELEMENTS_POOL = new ElementsPoolSemaphore();

    public static ElementsPoolSemaphore getInstance() {
        return ELEMENTS_POOL;
    }

    private Semaphore semaphore = new Semaphore(1,true);

    private List<Element> emptyElements = new ArrayList<>();
    private List<Element> elementCopies = new ArrayList<>();

    public ElementsPoolSemaphore() {
    }

    public Element takeElement() {
        try {
            Element remove = null;
            semaphore.acquire();
            if (!emptyElements.isEmpty()) {
                remove = emptyElements.remove(0);
            }
            return remove;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        return null;
    }

    public void putElement(Element element) {
        try {
            semaphore.acquire();
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
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
}
