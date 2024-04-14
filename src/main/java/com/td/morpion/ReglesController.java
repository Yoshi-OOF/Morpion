package com.td.morpion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReglesController {
    @FXML
    private void onRulesAccept(MouseEvent event) {
        Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
