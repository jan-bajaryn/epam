package by.epam.learn.cycles;

public class Forth40 {

    private void getNumbers(int n) {
        for (int i = n - 1; i > 0; i--) {
            System.out.println("input is = " + i);
            if (isDevidedFunc(i))
                System.out.println(i);
        }
    }

    private boolean isDevidedFunc(int num) {
        return isDevided(num, Integer.toString(num).toCharArray(), 0);
    }
    
    private boolean isDevided(int value, char[] num, int count) {
        if (num.length == count) return true;
        int temp = Character.getNumericValue(num[count++]);
        if (temp == 0) return false;
        return (value % temp == 0) && isDevided(value, num, count);
    }

    public static void main(String[] args) {
        Forth40 forth40 = new Forth40();
        forth40.getNumbers(40);
    }
}
