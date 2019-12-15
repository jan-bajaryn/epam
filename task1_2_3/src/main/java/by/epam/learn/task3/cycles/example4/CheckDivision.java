package by.epam.learn.task3.cycles.example4;

public class CheckDivision {

    public void getNumbers(int n) {
        for (int i = n - 1; i > 0; i--) {
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

}
