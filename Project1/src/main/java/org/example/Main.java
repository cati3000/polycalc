package org.example;

import org.example.Controller.CalculatorController;
import org.example.View.Calculator;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        CalculatorController calculatorController = new CalculatorController(calculator);
        calculator.setVisible(true);
    }
}