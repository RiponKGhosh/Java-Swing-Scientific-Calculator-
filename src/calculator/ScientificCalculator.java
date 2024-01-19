package calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField textField;
    private double firstOperand = 0;
    private double secondOperand = 0;
    private String operator = "";
    private boolean startNewInput = true;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new BorderLayout());
       // setBackground(color.black);
        

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        //textField.setSize(60, 50);
        textField.setEditable(false);
        textField.setBackground(Color.gray);
        add(textField, BorderLayout.NORTH);
   
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.gray);
        buttonPanel.setLayout(new GridLayout(8, 4));

        String[] buttonLabels = {
            "x^2", "sqrt","x/y", "x^y",
            "sin","cos", "tan","log",
            "M+", "n!","DEL","AC",
           "7", "8", "9","/", 
           "4","5","6", "*", 
            "1", "2", "3","-", 
            "0", ".", "=","+",
            
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.setForeground(Color.blue);
            button.setBackground(Color.white);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case ".":
                handleNumericInput(command);
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "x^y":
                handleOperator(command);
                break;
            case "x/y":
                handleOperator(command);
                break;
            case "=":
                calculateResult();
                break;
            case "sqrt":
                calculateSquareRoot();
                break;
            case "x^2":
                calculateSquare();
                break;
            case "sin":
                calculateSine();
                break;
            case "cos":
                calculateCosine();
                break;
            case "tan": 
                calculateTangent();
                break;
            case "n!":
                calculateFactorial();
                break;
            case "AC":
                clearInput();
                break;
            case "CE":
                clearEntry();
                break;
            case "DEL":
                deleteLastCharacter();
                break;
            case "log":
                calculateLogarithm();
                 break;
        }
    }

    private void handleNumericInput(String input) {
        if (startNewInput) {
            textField.setText(input);
            startNewInput = false;
        } else {
            textField.setText(textField.getText() + input);
        }
    }

    private void handleOperator(String operator) {
        if (!this.operator.isEmpty()) {
            calculateResult();
        }
        firstOperand = Double.parseDouble(textField.getText());
        this.operator = operator;
        startNewInput = true;
    }

    private void calculateResult() {
        double secondOperand = Double.parseDouble(textField.getText());
        double result = 0;

        switch (operator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    textField.setText("Infinity");
                    return;
                }
                break;
            case "x^y":
                result = Math.pow(firstOperand, secondOperand);
                break;
            case "x/y":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    textField.setText("Infinity");
                    return;
                }
                break;
        }

        textField.setText(String.valueOf(result));
        startNewInput = true;
        operator = "";
    }

    private void calculateSquareRoot() {
        double operand = Double.parseDouble(textField.getText());
        if (operand >= 0) {
            double result = Math.sqrt(operand);
            textField.setText(String.valueOf(result));
        } else {
            textField.setText("Math Error");
        }
        startNewInput = true;
    }

    private void calculateSquare() {
        double operand = Double.parseDouble(textField.getText());
        double result = Math.pow(operand, 2);
        textField.setText(String.valueOf(result));
        startNewInput = true;
    }
     private void calculateDividion() {
        double firstOperand = Double.parseDouble(textField.getText());
        double secondOperand = Double.parseDouble(textField.getText());
        double result = firstOperand/secondOperand;
        textField.setText(String.valueOf(result));
        startNewInput = true;
    }

    private void calculateSine() {
        double operand = Double.parseDouble(textField.getText());
        double result = Math.sin(Math.toRadians(operand));
        textField.setText(String.valueOf(result));
        startNewInput = true;
    }

    private void calculateCosine() {
        double operand = Double.parseDouble(textField.getText());
        double result = Math.cos(Math.toRadians(operand));
        textField.setText(String.valueOf(result));
        startNewInput = true;
    }
     private void calculateTangent() {
        double operand = Double.parseDouble(textField.getText());
        double result = Math.tan(Math.toRadians(operand));
        textField.setText(String.valueOf(result));
        startNewInput = true;
     }
     
     private void calculateLogarithm() {
    double operand = Double.parseDouble(textField.getText());
    if (operand > 0) {
        double result = Math.log10(operand);
        textField.setText(String.valueOf(result));
    } else {
        textField.setText("Math Error");
    }
    startNewInput = true;
    }
    private void clearEntry() {
    String currentText = textField.getText();
    if (!currentText.isEmpty()) {
        textField.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    private void clearInput() {
        textField.setText("");
        startNewInput = true;
        operator = "";
    }
    private void calculateFactorial() {
        try {
            int operand = Integer.parseInt(textField.getText());
            if (operand >= 0) {
                int result = 1;
                for (int i = 1; i <= operand; i++) {
                    result *= i;
                }
                textField.setText(String.valueOf(result));
            } else {
                textField.setText("Math Error");
            }
        } catch (NumberFormatException e) {
            textField.setText("Invalid Input");
        }
        startNewInput = true;
    }
    private void deleteLastCharacter() {
    String currentText = textField.getText();
    if (!currentText.isEmpty()) {
        textField.setText(currentText.substring(0, currentText.length() - 1));
        }
    }
    
    public class Calculator {
        
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScientificCalculator());
    }
  }
}

