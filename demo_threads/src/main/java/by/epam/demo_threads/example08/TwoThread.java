package by.epam.demo_threads.example08;

public class TwoThread {
    public static int counter = 0;

    public static void main(String[] args) {
        final StringBuilder s = new StringBuilder();
        new Thread(() -> {
            synchronized (s) {
                do {
                    s.append("A");
                    System.out.println(s);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.err.print(e);
                    }
                } while (TwoThread.counter++ < 2);
            } // конец synchronized
        }).start();
        new Thread(() -> {
            synchronized (s) {
                while (TwoThread.counter++ < 6) {
                    s.append("B");
                    System.out.println(s);
                }
            } // конец synchronized
        }).start();
    }
}
