package by.epam.demo_threads.test.test;

import java.util.ArrayList;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.start();

        myClass.interrupt();
//        myClass.interrupt();
    }
}

class MyClass extends Thread {

    @Override
    public void run() {
        Random random = new Random();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            for (int j = 1; j < 1_000_000; j++) {
                list.add(Integer.toString(random.nextInt(j)));
            }
            list.sort(String::compareTo);
        }
    }

}
