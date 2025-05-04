module com.example.calculatorwithpad {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.calculatorwithpad to javafx.fxml;
    exports com.example.calculatorwithpad;
}