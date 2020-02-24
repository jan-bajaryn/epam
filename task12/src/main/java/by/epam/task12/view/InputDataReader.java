package by.epam.task12.view;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class InputDataReader {

    private static final Logger log = LogManager.getLogger(InputDataReader.class);

    private Scanner sc = new Scanner(System.in);

    public String readFileName(String product) {
        System.out.println("Please enter fileName with extension from what you want to take the "+product);
        String answer = sc.nextLine();
        log.info("readFileName: answer = {}", answer);
        return answer;
    }

    public Character readLetter() {
        System.out.println("Please enter letter using what you want to sort the text");
        String s = sc.nextLine();
        log.info("readLetter: s = {}", s);
        if (s.length() != 1) {
            return null;
        }
        return s.charAt(0);
    }
}
