package by.epam.demo_threads.example02;

public class Runner {
    public static void main(String[] args) {
        RunnablePerson p1 = new RunnablePerson("Alice");
        Thread t1 = new Thread(p1);
        t1.start();
        RunnablePerson p2 = new RunnablePerson("Alice");
        Thread t2 = new Thread(p2);
        t2.start();
    }
}
