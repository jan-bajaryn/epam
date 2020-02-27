package by.epam.demo_threads.example03a;

public class PriorityRunner {
    public static void main(String[] args) {
        PriorThread min = new PriorThread("1");
        PriorThread max = new PriorThread("10");
        PriorThread norm = new PriorThread("5");
        min.setPriority(1); // 1
        max.setPriority(10); // 10
        norm.setPriority(5); // 5
        min.start();
        norm.start();
        max.start();
    }
}

