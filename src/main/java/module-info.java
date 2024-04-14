module com.td.morpion {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.td.morpion to javafx.fxml;
    exports com.td.morpion;
}