package by.epam.languages.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.println("1 — английский /n 2 — белорусский \n любой — русский ");
            char c = 0;
//            try {
            c = sc.nextLine().toCharArray()[0];
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            String country = "";
            String language = "";
            switch (c) {
                case '1':
                    country = "ru";
                    language = "RU";
                    break;
                case '2':
                    country = "by";
                    language = "BY";
                    break;
            }
            Locale current = new Locale(language, country);
            ResourceBundle rb = ResourceBundle.getBundle("property.text", current);
            String s1 = rb.getString("str1");
            System.out.println(s1);
            String s2 = rb.getString("str2");
            System.out.println(s2);
        }
    }
}