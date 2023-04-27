module com.example.telephonebook {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens com.example.telephonebook to javafx.fxml;
    exports com.example.telephonebook;
}