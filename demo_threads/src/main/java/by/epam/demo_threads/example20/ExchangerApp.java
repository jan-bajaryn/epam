package by.epam.demo_threads.example20;

import java.util.concurrent.Exchanger;

public class ExchangerApp {

    private static final int NMB = 20;

    public static void main(String[] args) {

        Exchanger<String> ex = new Exchanger<>();

        for (int i = 1; i <= NMB; i++) {

            Thread t1 = new Thread(new PutThread(ex));
            Thread t2 = new Thread(new GetThread(ex));

            t1.setName("Поток Put " + i);
            t2.setName("Поток Get " + i);

            t1.start();
            t2.start();

        }
    }

}

