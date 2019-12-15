package by.epam.learn.task2.branching.example2;

public class Runner {

    //find square by 3 sides of triangle


    public static void main(String[] args) {
        Triangle triangle = new Triangle(3, 4, 5);
        Triangle triangle1 = new Triangle(4, 5, 6);
        System.out.println(triangle.compareTo(triangle1));
        System.out.println(triangle.compareTo(triangle));
    }
}
