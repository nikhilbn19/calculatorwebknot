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


            // Calculating result using a function
            double result = calculate(num1, num2, op);

            // Displaying the result
            System.out.println("Result: " + num1 + " " + op + " " + num2 + " = " + result);
        }
        catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Invalid input! Please enter valid numbers and operators.");
        } finally {
            sc.close(); // Closing scanner
        }
    }

    public static double calculate(double num1, double num2, char op) throws ArithmeticException {
        switch (op) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/':
                if (num2 == 0) throw new ArithmeticException("Division by zero is not allowed!");
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operator! Use +, -, *, /");
        }
    }
}
