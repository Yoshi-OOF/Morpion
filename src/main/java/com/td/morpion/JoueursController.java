package com.td.morpion;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class JoueursController {
    @FXML
    private TextField player1NameField;

    @FXML
    private TextField player2NameField;

    @FXML
    private Button ValidateButton;

    @FXML
    private void changePlayerName() {
        MorpionController.player1Name = player1NameField.getText();
        MorpionController.player2Name = player2NameField.getText();
    }
}
