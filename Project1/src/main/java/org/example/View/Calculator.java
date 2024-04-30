package org.example.View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JPanel calcPanel;
    private JButton integrateInput1;
    private JButton differentiateInput2;
    private JButton additionBut;
    private JButton divisionBut;
    private JButton subtractionBut;
    private JButton multiplicationBut;
    private  JTextField resultField;
    private  JTextField input2Field;
    private  JTextField input1Field;

    public Calculator() {
        setDimension(600, 500);
    }

    public void setDimension(int w, int h) {
        add(calcPanel);
        setBounds(580, 400, w, h);
        this.pack();
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public String getInput1Field() {return input1Field.getText();}
    public String getInput2Field() {return input2Field.getText();}
    public void setResultField(String str) {resultField.setText(str);}

    public void addintegrateInput1Listener(ActionListener listener) {this.integrateInput1.addActionListener(listener);}
    public void adddifferentiateInput2Listener(ActionListener listener) {this.differentiateInput2.addActionListener(listener);}
    public void addadditionButListener(ActionListener listener) {this.additionBut.addActionListener(listener);}
    public void adddivisionButListener(ActionListener listener) {this.divisionBut.addActionListener(listener);}
    public void addsubtractionButListener(ActionListener listener) {this.subtractionBut.addActionListener(listener);}
    public void addmultiplicationButListener(ActionListener listener) {this.multiplicationBut.addActionListener(listener);}

}
