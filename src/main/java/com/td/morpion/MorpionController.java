package com.td.morpion;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.Event;

public class MorpionController {
    String player1Name = "Player 1";
    String player2Name = "Player 2";
    int PlayerWhoStart = 3;
    int PlayerTurn = 1;
    int GameStatus = 0;
    int[][] gameBoard = new int[3][3];

    private void ResetGameBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = 0;
            }
        }
    }


    @FXML
    private RadioButton FirstPlayerRadio;

    @FXML
    private RadioButton SecondPlayerRadio;

    @FXML
    private RadioButton RandomPlayerRadio;

    @FXML
    private Label StatusLabel;

    private void StartGame() {
        System.out.println("Starting game");
        GameStatus = 1;
        StatusLabel.setText("C'est parti !");
    }

    @FXML
    protected void onBoxButtonClicked(Event event) {
        Button button = (Button) event.getSource();
        handleKey(button);
    }

    @FXML
    protected void onRadioButtonClicked(Event event) {
        RadioButton button = (RadioButton) event.getSource();
        if (button.getId().equals("FirstPlayerRadio")) {
            PlayerWhoStart = 1;
        } else if (button.getId().equals("SecondPlayerRadio")) {
            PlayerWhoStart = 2;
        } else if (button.getId().equals("RandomPlayerRadio")) {
            PlayerWhoStart = 3;
        }
        CheckSelectedRadioButton(PlayerWhoStart);
    }

    private boolean CheckWin(int[][] board) {
        // Vérifier les lignes
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != 0) {
                return true;
            }
        }

        // Vérifier les colonnes
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != 0) {
                return true;
            }
        }

        // Vérifier les diagonales
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0) {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != 0) {
            return true;
        }

        return false;
    }

    private void handleKey(Button theButton) {
        if (GameStatus == 2) {
            return;
        }

        if (GameStatus == 0) {
            StartGame();
        }

        String ButtonIdString = theButton.getId().substring(6);
        int ButtonId = Integer.parseInt(ButtonIdString);
        int x = ButtonId / 3;
        int y = ButtonId % 3;

        if (PlayerTurn == 1) {
            theButton.setText("X");
            PlayerTurn = 2;
            gameBoard[x][y] = 1;
        } else {
            theButton.setText("O");
            PlayerTurn = 1;
            gameBoard[x][y] = 2;
        }

        if (CheckWin(gameBoard)) {
            if (PlayerTurn == 1) {
                StatusLabel.setText(player2Name + " a gagné !");
            } else {
                StatusLabel.setText(player1Name + " a gagné !");
            }
            GameStatus = 2;
        } else {
            StatusLabel.setText("C'est à " + (PlayerTurn == 1 ? player1Name : player2Name));
        }

    }

    @FXML
    protected void ResetGame() {
        System.out.println("Resetting game");
        GameStatus = 0;
        ResetGameBoard();
        StatusLabel.setText("Prêt ?");
    }

    @FXML
    protected void ShowRules() {

    }
    @FXML
    protected void ChangePlayerName() {

    }
    @FXML
    protected void PlayAgainstAI() {

    }

    @FXML
    protected void initialize() {
        CheckSelectedRadioButton(PlayerWhoStart);
        ResetGameBoard();
    }

    @FXML
    protected void CheckSelectedRadioButton(int selectedRadioButton) {
        if (selectedRadioButton == 1) {
            SecondPlayerRadio.setSelected(false);
            RandomPlayerRadio.setSelected(false);
        } else if (selectedRadioButton == 2) {
            FirstPlayerRadio.setSelected(false);
            RandomPlayerRadio.setSelected(false);
        } else if (selectedRadioButton == 3) {
            FirstPlayerRadio.setSelected(false);
            SecondPlayerRadio.setSelected(false);
        }
    }
}