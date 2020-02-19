package by.epam.demo_threads.example10a;

public class Store {
    private int product = 0;

    public synchronized void put(Producer producer) {
        while (product >= 5) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        product++;
        System.out.println("Производитель " + producer.calcName() + " добавил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notifyAll();
    }

    public synchronized void get(Consumer consumer) {
        while (product < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        product--;
        System.out.println("Покупатель " + consumer.calcName() + " купил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notifyAll();
    }

}

