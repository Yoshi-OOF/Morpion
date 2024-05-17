package com.td.morpion;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.ArrayList;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
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

    @FXML
    protected void setScoreText(String player1Name, String player2Name, int player1Score, int player2Score) {
        ScoreText.setText(player1Name + " : " + player1Score + " | " + player2Name + " : " + player2Score);
    }

    public static final LinearGradient paint = new LinearGradient(
            0.4006, 0.0, 1.0, 1.0, true, CycleMethod.NO_CYCLE,
            new Stop(0.0, new Color(1.0, 0.0, 0.9969, 1.0)),
            new Stop(1.0, new Color(0.0592, 0.0, 1.0, 1.0)));

    @FXML
    public void initialize() {
        ScoreText.setStroke(paint);
    }

}
