package com.td.morpion;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.ArrayList;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

public class WinController {
    @FXML
    private Button RestartButton;

    @FXML
    private Text StatusText;

    @FXML
    protected void onRestartGame() {
        MorpionController.ResetGameBoard();
    }

    @FXML
    public void initialize() {
        StatusText.setText(MorpionController.Winner + " a gagné !");
    }
}
