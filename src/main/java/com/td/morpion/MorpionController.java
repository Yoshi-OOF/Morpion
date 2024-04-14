package com.td.morpion;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class MorpionController {
    private Stage modalDialog;
    private Stage botDialog;

    String player1Name = "Player 1";
    String player2Name = "Player 2";
    static boolean player2IsAI = false;
    static int PlayerWhoStart = 3;
    static int PlayerTurn = 1;
    static int GameStatus = 0;
    static int[][] gameBoard = new int[3][3];
    ArrayList<Button> buttonsCache = new ArrayList<Button>();

    public void setModalDialog(Stage modal) {
        this.modalDialog = modal;
    }

    public void setBotDialog(Stage bot) {
        this.botDialog = bot;
    }

    @FXML
    private RadioButton FirstPlayerRadio;

    @FXML
    private RadioButton SecondPlayerRadio;

    @FXML
    private RadioButton RandomPlayerRadio;

    @FXML
    private Label StatusLabel;

    private void ResetGameBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = 0;
            }
        }

        if (!buttonsCache.isEmpty()) {
            buttonsCache.forEach(button -> button.setText(""));
        }
    }

    private void StartGame() {
        System.out.println("Starting game");
        GameStatus = 1;
        StatusLabel.setText("C'est parti !");
        PlayerTurn = PlayerWhoStart;
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

        if (PlayerTurn == 3) {
            PlayerTurn = (int) (Math.random() * 2) + 1;
        }

        String ButtonIdString = theButton.getId().substring(6);
        int ButtonId = Integer.parseInt(ButtonIdString);
        int x = ButtonId / 3;
        int y = ButtonId % 3;

        System.out.println("Button clicked: " + x + " " + y);
        System.out.println("Player turn: " + PlayerTurn);
        System.out.println("Second player is AI: " + player2IsAI);

        if (player2IsAI && PlayerTurn == 2) {
            return;
        }

        if (PlayerTurn == 1) {
            theButton.setText("X");
            gameBoard[x][y] = 1;
            PlayerTurn = 2;

            if (player2IsAI) {
                buttonsCache = new ArrayList<Button>();
                for (int i = 0; i < 9; i++) {
                    Button button = (Button) theButton.getScene().lookup("#Button" + i);
                    buttonsCache.add(button);
                }
                BotController.PlayBot(buttonsCache);
            }
        } else {
            theButton.setText("O");
            gameBoard[x][y] = 2;
            PlayerTurn = 1;
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
        modalDialog.show();
    }
    @FXML
    protected void ChangePlayerName() {

    }
    @FXML
    protected void PlayAgainstAI() {
        botDialog.show();
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