package by.epam.demo_threads.example10a;

public class Producer extends Thread {

    private Store store;

    private int countProducts;
    private String name;

    public Producer(Store store, int countProducts, String name) {
        this.store = store;
        this.countProducts = countProducts;
        this.name = name;
    }

    public void run() {
        for (int i = 1; i < countProducts; i++) {
            store.put(this);
        }
    }

    public String calcName() {
        return name;
    }
}

