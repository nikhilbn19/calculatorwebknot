package org.example;

import java.util.Stack;
import java.util.Scanner;

public class Main {
    // Method to define the precedence of operators
    private int precedence(char op) {
        if (op == '(' || op == ')') return 0; // Parentheses have the lowest precedence
        else if (op == '+' || op == '-') return 1; // Addition and subtraction have lower precedence
        else return 2; // Multiplication and division have higher precedence
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter an expression (e.g., 5+3*2-4): ");
        String expression = sc.nextLine(); // Read user input

        Main sol = new Main(); // Create an object of the Main class
        int result = sol.calculate(expression); // Call the calculate method
        System.out.println("Result: " + result); // Print the result

        sc.close(); // Close the scanner
    }
}