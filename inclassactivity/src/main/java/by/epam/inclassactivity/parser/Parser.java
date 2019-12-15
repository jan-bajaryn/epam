package by.epam.inclassactivity.parser;

public class Parser {
    public int[] parseStrArrToIntArr(String [] strArr){
        int[] intArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }
}
