package by.epam.learn;

public class Third30 {

    private double resistance(double r1, double r2, double r3){
        if (r1<=0 || r2 <=0 || r3 <=0)
            throw new IllegalArgumentException("resistance can't be less or equal 0");
        return  1/(1/r1 + 1/r2 + 1/r3);
    }

    public static void main(String[] args) {
        Third30 third30 = new Third30();
        System.out.println(third30.resistance(4,5,2.4));
    }
}
