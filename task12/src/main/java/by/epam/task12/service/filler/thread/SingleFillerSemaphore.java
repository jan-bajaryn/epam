package by.epam.task12.service.filler.thread;

import by.epam.task12.dao.ElementsPoolSemaphore;
import by.epam.task12.entity.Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

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
        while (!elementsPoolSemaphore.isEmpty() && !Thread.interrupted()) {
            Element element = elementsPoolSemaphore.takeElement();
            if (element != null) {
                sleepHavingElement(element);
                element.setValue(value);
                elementsPoolSemaphore.putElement(element);
            } else {
                sleepWithout();
            }
        }
    }

    private void sleepWithout() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.info("Interrupted having null");
            Thread.currentThread().interrupt();
        }
    }

    private void sleepHavingElement(Element element) {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.info("Interrupted element = {}", element);
            elementsPoolSemaphore.putElement(element);
            Thread.currentThread().interrupt();
        }
    }

}
