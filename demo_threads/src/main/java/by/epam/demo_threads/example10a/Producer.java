package by.epam.demo_threads.example10;

public class Producer extends Thread {

    Store store;

    private int countProducts;
    private String name;

    public Producer(Store store, int countProducts, String name) {
        this.store = store;
        this.countProducts = countProducts;
        this.name = name;
    }

    public void run() {
        for (int i = 1; i < countProducts; i++) {
            store.put();
        }
    }

}

