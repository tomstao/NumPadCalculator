package com.example.calculatorwithpad;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Calculator {
    final private GridPane root = new GridPane();
    final private VBox container = new VBox();

    Calculator() {
        for (int i = 0; i < 4; i ++) {
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
        clearButton.setOnAction(event -> resultDisplay.setText("0"));
        GridPane.setHgrow(clearButton, Priority.ALWAYS);
        GridPane.setVgrow(clearButton, Priority.ALWAYS);

        root.add(clearButton, 0,5);
    }

    private void createButton(String symbol, TextField display, GridPane grid, int row, int col) {
        Button btn = new Button(symbol);
        btn.setOnAction(e -> display.setText(display.getText() + symbol));
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn.setFont(new Font("Arial", 24));
//        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setHgrow(btn, Priority.ALWAYS);
        GridPane.setVgrow(btn, Priority.ALWAYS);
        grid.add(btn, col, row);
    }




    public GridPane getRoot() {
        return root;
    }

    public VBox getContainer() {
        return container;
    }
}
