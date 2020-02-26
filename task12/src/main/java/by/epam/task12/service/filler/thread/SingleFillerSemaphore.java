package by.epam.task12.service.filler.thread;

import by.epam.task12.dao.ElementsPoolSemaphore;
import by.epam.task12.entity.Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SingleFillerSemaphore extends Thread {
    private ElementsPoolSemaphore elementsPoolSemaphore = ElementsPoolSemaphore.getInstance();

    private static final Logger log = LogManager.getLogger(SingleFillerLock.class);


    private int value;

    public SingleFillerSemaphore(int value) {
        this.value = value;
        setName(Integer.toString(value));
    }

    @Override
    public void run() {
        while (!elementsPoolSemaphore.isEmpty()) {
            Element element = elementsPoolSemaphore.takeElement();
            if (element != null) {
                element.setValue(value);
                elementsPoolSemaphore.putElement(element);
            }
        }
    }
}
