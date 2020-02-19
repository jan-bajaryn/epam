package by.epam.demo_threads.example10a;

public class Runner {
    public static void main(String[] args) {

        Store store = new Store();
        int countProducts = 20;
        for (int i = 0; i < 4; i++) {
            new Producer(store, countProducts, "Producer " + i).start();
            new Consumer(store, countProducts, "Consumer " + i).start();
        }


    }


}
