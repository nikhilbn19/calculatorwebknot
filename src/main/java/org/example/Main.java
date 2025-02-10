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

    private int applyOperand(char op, int a, int b) {
        switch (op) {
            case '+': return a + b; // Addition
            case '-': return a - b; // Subtraction
            case '*': return a * b; // Multiplication
            case '/':
                if (b == 0) throw new ArithmeticException("Division by zero is not allowed!"); // Handle division by zero
                return a / b; // Division
            default: throw new IllegalArgumentException("Invalid operator: " + op); // Handle invalid operators
        }
    }

    // Method to evaluate a mathematical expression in string format
    public int calculate(String s) {
        Stack<Integer> nums = new Stack<>(); // Stack to store numbers
        Stack<Character> ops = new Stack<>(); // Stack to store operators

        try {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') continue; // Ignore spaces

                if (Character.isDigit(s.charAt(i))) { // If the character is a number
                    int num = 0;
                    while (i < s.length() && Character.isDigit(s.charAt(i))) { // Extract full number
                        num = num * 10 + s.charAt(i) - '0';
                        i++;
                    }
                    nums.push(num); // Push number to stack
                    i--; // Move back one step since loop increments
                }
                else if (s.charAt(i) == '(') { // If it's an opening bracket, push it to operator stack
                    ops.push(s.charAt(i));
                }
                else if (s.charAt(i) == ')') { // If it's a closing bracket, solve inside brackets
                    while (ops.peek() != '(') { // Until we find opening bracket
                        int b = nums.pop();
                        int a = nums.pop();
                        nums.push(applyOperand(ops.pop(), a, b)); // Apply operator and push result
                    }
                    ops.pop(); // Remove the opening bracket '('
                }
                else { // It's an operator (+, -, *, /)
                    // Process operators in the stack with higher or equal precedence
                    while (!ops.empty() && ops.peek() != '(' && this.precedence(ops.peek()) >= this.precedence(s.charAt(i))) {
                        int b = nums.pop();
                        int a = nums.pop();
                        nums.push(this.applyOperand(ops.pop(), a, b)); // Apply operator and push result
                    }
                    ops.push(s.charAt(i)); // Push current operator
                }
            }

            // Process remaining operators in the stack
            while (!ops.empty()) {
                int b = nums.pop();
                int a = nums.pop();
                nums.push(this.applyOperand(ops.pop(), a, b));
            }

            return nums.peek(); // Return the final result
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage()); // Handle division by zero
        } catch (Exception e) {
            System.out.println("Invalid expression! Please enter a valid mathematical expression."); // Handle invalid expressions
        }

        return 0; // Return 0 in case of error
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