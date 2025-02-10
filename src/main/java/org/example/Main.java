package org.example;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // Taking input from the user
            System.out.println("Enter first number: ");
            double num1 = sc.nextDouble();

            System.out.println("Enter operator (+, -, *, /): ");
            char op = sc.next().charAt(0);

            System.out.println("Enter second number: ");
            double num2 = sc.nextDouble();

        }
        catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
