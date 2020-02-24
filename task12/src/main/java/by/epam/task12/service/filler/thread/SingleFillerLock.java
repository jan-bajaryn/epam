package by.epam.task12.service.filler.thread;

import by.epam.task12.dao.ElementsPoolLock;
import by.epam.task12.entity.impl.Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SingleFillerLock extends Thread {
    private ElementsPoolLock elementsPoolLock = ElementsPoolLock.getInstance();
    private static final Logger log = LogManager.getLogger(SingleFillerLock.class);


    private int value;

    public SingleFillerLock(int value) {
        this.value = value;
        setName(Integer.toString(value));
    }

    @Override
    public void run() {
        while (!elementsPoolLock.isEmpty()) {
            Element element = elementsPoolLock.takeElement();
            if (element != null) {
                element.setValue(value);
                elementsPoolLock.putElement(element);
            }
        }
    }
}
