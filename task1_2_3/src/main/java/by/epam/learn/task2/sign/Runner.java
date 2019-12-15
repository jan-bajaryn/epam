package by.epam.learn.task2.sign;

public class Runner {
    public static void main(String[] args) {
        Runner runner = new Runner();
        System.out.println(runner.sign(-121));
        System.out.println(runner.sign(0));
        System.out.println(runner.sign(2323));
    }

    // аналоги Integer.compare(x,0)
    private int sign(int num) {
        return num < 0 ? -1 : num > 0 ? 1 : 0;
    }
}
