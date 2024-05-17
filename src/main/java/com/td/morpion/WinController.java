package com.td.morpion;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.ArrayList;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinController {
    @FXML
    private Button RestartButton;

    @FXML
    private Text StatusText;

    @FXML
    private Text ScoreText;

    public void AnnounceWinner(String winner) {
        StatusText.setText(winner + " a gagné !");
    }

    @FXML
    protected void onRestartGame(Event event) {
        MorpionController.ResetGameBoard();
        Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
