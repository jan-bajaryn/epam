package by.epam.demo_threads.example09;

public class Runner {
    public static void main(String[] args) {

        Store store = new Store();
        new Producer(store).start();
        new Consumer(store).start();
    }
}
