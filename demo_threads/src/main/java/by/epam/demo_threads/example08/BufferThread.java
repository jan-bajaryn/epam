package by.epam.demo_threads.example08;

import java.util.concurrent.TimeUnit;

public class BufferThread {
    static int counter = 0;
    static final StringBuffer s = new StringBuffer(); // заменить на StringBuilder

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (s) {
                while (BufferThread.counter++ < 3) {
                    s.append("A");
                    System.out.print("> " + counter + " ");
                    System.out.println(s);
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } // конец synchronized-блока
        }).start();
        TimeUnit.MILLISECONDS.sleep(100);
        while (BufferThread.counter++ < 6) {
            System.out.print("< " + counter + " ");
// в этом месте поток main будет ждать освобождения блокировки объекта s
            s.append("B");
            System.out.println(s);
        }
    }
}
