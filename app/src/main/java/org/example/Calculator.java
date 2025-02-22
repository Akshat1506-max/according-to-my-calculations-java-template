package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Calculator {

    // Map to store operator precedence
    private static final Map<Character, Integer> PRECEDENCE = new HashMap<>();

    static {
        PRECEDENCE.put('+', 1);
        PRECEDENCE.put('-', 1);
        PRECEDENCE.put('*', 2);
        PRECEDENCE.put('/', 2);
    }

    // Method to evaluate infix expression
    public double evaluate(String expression) {
        String postfix = infixToPostfix(expression);
        return evaluatePostfix(postfix);
    }

    // Shunting Yard Algorithm to convert infix to postfix
    private String infixToPostfix(String expression) {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // Skip whitespace
            if (c == ' ') {
                continue;
            }

            // If the character is a digit, read the full number
            if (Character.isDigit(c)) {
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    output.append(expression.charAt(i));
                    i++;
                }
                output.append(' '); // Add space to separate numbers
                i--; // Adjust index after reading the full number
            } else if (c == '(') {
                stack.push(c); // Push '(' to stack
            } else if (c == ')') {
                // Pop from stack until '(' is found
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop()).append(' ');
                }
                stack.pop(); // Remove '(' from stack
            } else if (PRECEDENCE.containsKey(c)) {
                // Pop higher precedence operators from stack
                while (!stack.isEmpty() && stack.peek() != '(' && PRECEDENCE.get(stack.peek()) >= PRECEDENCE.get(c)) {
                    output.append(stack.pop()).append(' ');
                }
                stack.push(c); // Push current operator to stack
            }
        }

        // Pop remaining operators from stack
        while (!stack.isEmpty()) {
            output.append(stack.pop()).append(' ');
        }

        return output.toString().trim(); // Remove trailing space
    }

    // Evaluate postfix expression using a stack
    private double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        StringBuilder numberBuilder = new StringBuilder();

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                numberBuilder.append(c); // Build the number
            } else if (c == ' ') {
                // End of number, push to stack
                if (numberBuilder.length() > 0) {
                    stack.push(Double.parseDouble(numberBuilder.toString()));
                    numberBuilder.setLength(0); // Reset the builder
                }
            } else {
                // Operator: pop two operands and perform the operation
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                switch (c) {
                    case '+':
                        stack.push(operand1 + operand2);
                        break;
                    case '-':
                        stack.push(operand1 - operand2);
                        break;
                    case '*':
                        stack.push(operand1 * operand2);
                        break;
                    case '/':
                        stack.push(operand1 / operand2);
                        break;
                }
            }
        }

        return stack.pop(); // Final result
    }
}