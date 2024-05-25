package com.td.morpion;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.Objects;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class JoueursController {
    @FXML
    private TextField player1NameField;

    @FXML
    private TextField player2NameField;

    @FXML
    private Button ValidateButton;

    /**
     * Fonction qui permet de changer le nom des joueurs et de fermer la fenêtre
     * @param event
     */
    @FXML
    private void changePlayerName(Event event) {
        MorpionController.player1Name = Objects.equals(player1NameField.getText(), "") ? "Player 1" : player1NameField.getText();
        MorpionController.player2Name = Objects.equals(player2NameField.getText(), "") ? "Player 2" : player2NameField.getText();
        Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
