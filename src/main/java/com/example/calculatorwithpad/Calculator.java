package com.example.calculatorwithpad;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.LinkedList;

public class Calculator {
    final private VBox container = new VBox();

    Calculator() {
        GridPane root = new GridPane();
        for (int i = 0; i < 4; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(25);
            root.getColumnConstraints().add(column);
        }

//        root.setPrefSize(540, 540);
//        test gap and padding
//        root.setHgap(0);
//        root.setVgap(0);
//        root.setPadding(Insets.EMPTY);


        for (int i = 0; i < 6; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(16.66);
            root.getRowConstraints().add(row);
        }
        container.setAlignment(Pos.CENTER);
        container.getChildren().add(root);


        TextField resultDisplay = new TextField();
        resultDisplay.setPromptText("0");
        resultDisplay.setEditable(false);

        resultDisplay.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setHgrow(resultDisplay, javafx.scene.layout.Priority.ALWAYS);
        GridPane.setVgrow(resultDisplay, javafx.scene.layout.Priority.ALWAYS);
        resultDisplay.setFont(new Font("Arial", 28));

        root.add(resultDisplay, 0, 0, 4, 1);


        createButton("" + 7, resultDisplay, root, 1, 0);
        createButton("" + 8, resultDisplay, root, 1, 1);
        createButton("" + 9, resultDisplay, root, 1, 2);
        createButton("+", resultDisplay, root, 1, 3);

        createButton("" + 4, resultDisplay, root, 2, 0);
        createButton("" + 5, resultDisplay, root, 2, 1);
        createButton("" + 6, resultDisplay, root, 2, 2);
        createButton("-", resultDisplay, root, 2, 3);

        createButton("1", resultDisplay, root, 3, 0);
        createButton("2", resultDisplay, root, 3, 1);
        createButton("3", resultDisplay, root, 3, 2);
        createButton("/", resultDisplay, root, 3, 3);

        createButton(".", resultDisplay, root, 4, 0);
        createButton("" + 0, resultDisplay, root, 4, 1);
        createButton("=", resultDisplay, root, 4, 2);
        createButton("*", resultDisplay, root, 4, 3);

        Button clearButton = new Button("Clear");
        clearButton.setPrefSize(360, 90);
        GridPane.setColumnSpan(clearButton, 4);
        clearButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        clearButton.setOnAction(event -> {
            resultDisplay.clear();
            resultDisplay.setPromptText("0");
        });
        GridPane.setHgrow(clearButton, Priority.ALWAYS);
        GridPane.setVgrow(clearButton, Priority.ALWAYS);

        root.add(clearButton, 0, 5);
    }

    private void createButton(String symbol, TextField display, GridPane grid, int row, int col) {
        Button btn = new Button(symbol);
        addBtnAction(btn, display, symbol);
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn.setFont(new Font("Arial", 24));
//        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setHgrow(btn, Priority.ALWAYS);
        GridPane.setVgrow(btn, Priority.ALWAYS);
        grid.add(btn, col, row);
    }

    private void addBtnAction(Button button, TextField display, String symbol) {
        if (symbol.equals("=")) {
            button.setOnAction(e -> numParse(display));
            return;
        }
        button.setOnAction(e -> display.setText(display.getText() + symbol));
    }

//    private void displayAnalysis(TextField display) {
////        LinkedList<Double> results = new LinkedList<>();
//        String value = display.getText();
//        double sum = 0;
//        int indexMultiply = value.indexOf('*');
//        int indexDivide = value.indexOf('/');
//        int indexPlus = value.indexOf('+');
//        int indexMinus = value.indexOf('-');
//        int indexEqual = value.indexOf('=');
//        int indexDot = value.indexOf('.');
//        int currentIndex = 0;
//        if (indexMultiply == -1 && indexDivide == -1) {
//            for (int i = 0; i <= value.length(); i++) {
//                if (i == value.length() || value.charAt(i) == '+') {
//                    String number = value.substring(currentIndex, i).trim();
//                    if (!number.isEmpty()) {
//                        sum += Double.parseDouble(number);
//                    }
//                    currentIndex = i + 1;
//                }
//            }
//

    /// /                if(value.charAt(i) == '-') {
    /// /                    String digits = value.substring(currentIndex, i + 1);
    /// /                    currentIndex = i + 1;
    /// /                    sum -= Double.parseDouble(digits);
    /// /                }
//
//            display.setText(Double.toString(sum));
//            System.out.println(sum);
//        }
//
//    }

//    public static boolean isNumeric(String str) {
//        if (str == null || str.isEmpty()) return false;
//
//        for (char c : str.toCharArray()) {
//            if (!Character.isDigit(c)) return false;
//        }
//        return true;
//    }
    /*
    private void displayAnalysis(TextField display) {
        String value = display.getText();
        double sum = 0;
        int currentIndex = 0;
        LinkedList<Double> stack = new LinkedList<>();

        for (int i = 0; i <= value.length(); i++) {
            // Either end of string or next '+'
            if (i == value.length() || value.charAt(i) == '+') {
                String number = value.substring(currentIndex, i).trim();
//                String number = numParse(value,"+", currentIndex);
                if (!number.isEmpty()) {
//                    sum += Double.parseDouble(number);
                    stack.push(Double.parseDouble(number));
                }
                currentIndex = i + 1;
            } else if (value.charAt(i) == '-') {
                String number = value.substring(currentIndex, i).trim();

                currentIndex = i + 1;
            }
        }

        for (Double aDouble : stack) {
            sum += aDouble;
        }

        display.setText(Double.toString(sum));
    }

     */

    private void numParse(TextField display) {
        String value = display.getText().trim();
        if (value.isEmpty()) {
            display.setText("0");
            return;
        }

        LinkedList<Double> stack = new LinkedList<>();
        stack.add(0.0);
        char lastOperator = '+';
        int i = 0;

        while (i < value.length()) {
            char ch = value.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                int start = i;
                while (i < value.length() && (Character.isDigit(value.charAt(i)) || value.charAt(i) == '.')) {
                    i++;
                }
                double num = 0;
                try{
                num = Double.parseDouble(value.substring(start, i));
                } catch (NumberFormatException e) {
                    display.setText("Invalid input!");
                }
                calculationChain(lastOperator, stack, num);
                continue; //  move forward
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                lastOperator = ch;
            } else {
                display.setText("Invalid input! Please clear.");
                return;
            }
            i++;
        }

        double sum = 0;
        for (double num : stack) {
            sum += num;
        }

        display.setText(Double.toString(sum));
    }

    private void calculationChain(char operator, LinkedList<Double> stack, double num) {
        switch (operator) {
            case '+':
                stack.push(num);
                break;
            case '-':
                stack.push(-num);
                break;
            case '*':
                stack.push(stack.pop() * num);
                break;
            case '/':
                stack.push(stack.pop() / num);
                break;
        }
    }

    /*
        private void numParse(TextField display) {
            String value = display.getText().trim();
            if (value.isEmpty()) {
                display.setText("Invalid input!");
                return;
            }

            LinkedList<Double> stack = new LinkedList<>();
            int currentIndex = 0;

            for (int i = 1; i < value.length(); i++) {
                char ch = value.charAt(i);
                if (ch == '+' || ch == '-') {
                    String numStr = value.substring(currentIndex, i).trim();
                    if (!numStr.isEmpty()) {
                        try {
                            double num = Double.parseDouble(numStr);
                        } catch (NumberFormatException e) {
                            display.setText("Invalid input! Please clear!");
                        }
                        stack.add(Double.parseDouble(numStr));
                    }
                    currentIndex = i;
                } else if (ch == '*' || ch == '/') {
    //                String numStr = value.substring(currentIndex, i).trim();
    //                try {
    //                    double num1 = Double.parseDouble(value.substring(currentIndex, i).trim());
    //                    currentIndex = i + 1;
    //
    //                } catch (NumberFormatException e) {
    //                    display.setText("Invalid input! Please clear!");
    //                }
                    stack.push(multiply_divide(value,currentIndex, i));
                    currentIndex = i;
                }
            }

            String lastNumStr = value.substring(currentIndex).trim();
            if (!lastNumStr.isEmpty()) {
                stack.add(Double.parseDouble(lastNumStr));
            }

            double sum = 0;
            for (Double d : stack) {
                sum += d;
            }

            display.setText(Double.toString(sum));
        }

        private double multiply_divide(String display, int start, int end) {
            double num1 = Double.parseDouble(display.substring(start, end));
            double num2 = 0;
            for (int i = end; i < display.length(); i++) {
                char ch = display.charAt(i + 1);
                if(Character.isDigit(ch)) continue;
                try {
                    num2 = Double.parseDouble(display.substring(end + 1, i));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please clear!");
                }
            }

            return num1 * num2;
        }
    */

//    public GridPane getRoot() {
//        return root;
//    }

    public VBox getContainer() {
        return container;
    }

}
