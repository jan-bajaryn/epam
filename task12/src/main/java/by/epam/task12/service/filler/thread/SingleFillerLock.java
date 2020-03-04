package by.epam.task12.service.filler.thread;

import by.epam.task12.dao.ElementsPoolLock;
import by.epam.task12.entity.Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class SingleFillerLock extends Thread {
    private ElementsPoolLock elementsPoolLock = ElementsPoolLock.INSTANCE;
    private static final Logger log = LogManager.getLogger(SingleFillerLock.class);


    private int value;

    public SingleFillerLock(int value) {
        this.value = value;
        setName(Integer.toString(value));
    }

    @Override
    public void run() {
        while (!elementsPoolLock.isEmpty() && !Thread.interrupted()) {
            Element element = elementsPoolLock.takeElement();
            if (element != null) {
                if (sleepHavingElement(element)) {
                    break;
                }
                element.setValue(value);
                elementsPoolLock.putElement(element);
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

    private boolean sleepHavingElement(Element element) {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.info("Interrupted element = {}", element);
            elementsPoolLock.putElement(element);
            Thread.currentThread().interrupt();
            return true;
        }
        return false;
    }
}
