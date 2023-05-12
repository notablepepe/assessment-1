package iss;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome.");
        Scanner sc = new Scanner(System.in);
        String cal = "";
        Double last = 0.0;
        cal = sc.nextLine();

        while(!cal.equals("exit")) {
            String[] str = cal.split(" ");
            Double num1 = str[0].equals("last") ? last : Double.parseDouble(str[0]);
            Double num2 = str[2].equals("last") ? last : Double.parseDouble(str[2]);
            String op = str[1];

            if(op.equals("+")) {
                last = num1 + num2;
                System.out.println(last);
            }
            if(op.equals("-")) {
                last = num1 - num2;
                System.out.println(last);
            }
            if(op.equals("*")) {
                last = num1 * num2;
                System.out.println(last);
            }
            if(op.equals("/")) {
                last = num1 / num2;
                System.out.println(last);
            }

            cal = sc.nextLine();

        }

        System.out.println("Bye Bye");
        
    }
}
