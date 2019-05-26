package com.aca.chatbot.calculator;

import com.aca.chatbot.ui.CommandLineUserInterface;

public class Calculator {

    CommandLineUserInterface commandLineUserInterface = new CommandLineUserInterface();
    private static Calculator calculatorInstance = new Calculator();

    public static Calculator getCalculatorInstance() {
        return calculatorInstance;
    }

    private Calculator() {
    }

    public void startCalculator() {
        commandLineUserInterface.output("Please enter the arithmetic expression: ");
        String expr = commandLineUserInterface.readStr();
        String result = Calculator.getCalculatorInstance().calculateExpretion(expr);
        commandLineUserInterface.output(expr + " = " + result);
    }

    public String calculateExpretion(String expr) {
        // if the expression looks like this: expr = 2 * (4 / ((5 - 4) + 7)) + 5
        String[] exprParts = expr.split("\\s+");
        for (int i = exprParts.length / 2; i > 0; i--) {
            // find the most prioritySubExpression then replace it by it's result in the main expression
            String prioritySubExpression = findPriorityExpression(expr);
            // 5 - 4 = 1.0
            String prioritySubExprResult = calculateFromString(prioritySubExpression);
            // first iteration result:  2 * (4 / (1.0 + 7)) + 5
            // second iteration result: 2 * (4 / 8.0) + 5

            // ... last iteration result: expr = 1.0 + 5 = 6.0
            expr = expr.replace(prioritySubExpression, prioritySubExprResult);
        }
        return expr;
    }

    public String calculateFromString(String expr) {
        expr = expr.replace("(", "");
        expr = expr.replace(")", "");
        String parts[] = expr.split("\\s+");
        return "" + calculate(Double.parseDouble(parts[0]), parts[1], Double.parseDouble(parts[2]));
    }

    public double calculate(double a, String operation, double b) {
        switch (operation) {
            case "*":
                return a * b;
            case "/":
                if (b != 0) {
                    return a / b;
                } else
                    throw new IllegalArgumentException("Trying to divide by Zero.");
            case "-":
                return a - b;
            case "+":
                return a + b;
            default:
                throw new IllegalArgumentException("Please use *; /; -; + operations.");
        }
    }

    public String findPriorityExpression(String expr) {
        int openIndex = 0;
        int closeIndex = 0;
        // Find the nested priority brackets of expr and get it.
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') {
                openIndex = i;
            }
            if (expr.charAt(i) == ')') {
                closeIndex = i;
                return expr.substring(openIndex, closeIndex + 1);
            }
        }

        String[] parts = expr.split("\\s+");
        if (expr.contains("*") || expr.contains("/")) {
            for (int i = 1; i < parts.length - 1; i++) {
                if ("*/".contains(parts[i])) {
                    return parts[i - 1] + " " + parts[i] + " " + parts[i + 1];
                }
            }
        }
        if (expr.contains("-") || expr.contains("+"))
            for (int i = 1; i < parts.length - 1; i++) {
                if ("-+".contains(parts[i])) {
                    return parts[i - 1] + " " + parts[i] + " " + parts[i + 1];
                }
            }
        return expr;
    }
}
