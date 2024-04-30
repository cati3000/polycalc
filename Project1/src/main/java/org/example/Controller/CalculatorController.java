package org.example.Controller;
import org.example.Model.Polynomial;
import org.example.View.Calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
    private Calculator calcView;
    public CalculatorController(Calculator calcView){
        this.calcView = calcView;
        calcView.addadditionButListener(new Addition());
        calcView.addsubtractionButListener(new Subtraction());
        calcView.addmultiplicationButListener(new Multiplication());
        calcView.adddivisionButListener(new Division());
        calcView.addintegrateInput1Listener(new Integration());
        calcView.adddifferentiateInput2Listener(new Differentiation());
    }

    public class Addition implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String p1, p2;
            p1 = calcView.getInput1Field();
            p2 = calcView.getInput2Field();
            if(Polynomial.validateInput(p1) && Polynomial.validateInput(p2)){
                String result = Polynomial.add(p1, p2);
                calcView.setResultField(result);
            }
            else{
                JOptionPane.showMessageDialog(calcView, "The input does not match a polynomial","Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public class Subtraction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String p1, p2;
            p1 = calcView.getInput1Field();
            p2 = calcView.getInput2Field();
            if(Polynomial.validateInput(p1) && Polynomial.validateInput(p2)){
                String result = Polynomial.subtract(p1, p2);
                calcView.setResultField(result);
            }
            else{
                JOptionPane.showMessageDialog(calcView, "The input does not match a polynomial","Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
    public class Multiplication implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String p1, p2;
            p1 = calcView.getInput1Field();
            p2 = calcView.getInput2Field();
            if(Polynomial.validateInput(p1) && Polynomial.validateInput(p2)){
                String result = Polynomial.multiply(p1, p2);
                calcView.setResultField(result);
            }
            else{
                JOptionPane.showMessageDialog(calcView, "The input does not match a polynomial","Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public class Division implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String p1, p2;
            p1 = calcView.getInput1Field();
            p2 = calcView.getInput2Field();
            if(Polynomial.validateInput(p1) && Polynomial.validateInput(p2)){
                String[] result = Polynomial.division(p1, p2);

                if(result==null){
                    JOptionPane.showMessageDialog(calcView, "Cannot divide to 0","Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    calcView.setResultField("Quotioent: " + result[0] + " Remainder:" + result[1]);
                }
            }
            else{
                JOptionPane.showMessageDialog(calcView, "The input does not match a polynomial","Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
    public class Integration implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String p;
            p = calcView.getInput1Field();
            if(Polynomial.validateInput(p)){
                String result = Polynomial.integrate(p);
                calcView.setResultField(result);
            }
            else{
                JOptionPane.showMessageDialog(calcView, "The input does not match a polynomial","Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public class Differentiation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String p;
            p = calcView.getInput2Field();
            if(Polynomial.validateInput(p)){
                String result = Polynomial.differentiate(p);
                calcView.setResultField(result);
            }
            else{
                JOptionPane.showMessageDialog(calcView, "The input does not match a polynomial","Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
