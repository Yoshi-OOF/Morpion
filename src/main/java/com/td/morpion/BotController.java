package com.td.morpion;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;

public class BotController {
    static int botDifficulty = 2; // 1 = easy, 2 = hard

    @FXML
    private RadioButton DifficultyEasyRadio;

    @FXML
    private RadioButton DifficultyHardRadio;

    @FXML
    protected void onRadioButtonClicked(Event event) {
        RadioButton button = (RadioButton) event.getSource();
        if (button.getId().equals("DifficultyEasyRadio")) {
            botDifficulty = 1;
        } else if (button.getId().equals("DifficultyHardRadio")) {
            botDifficulty = 2;
        }
        CheckSelectedRadioButton(botDifficulty);
    }

    private void CheckSelectedRadioButton(int botDifficulty) {
        if (botDifficulty == 1) {
            DifficultyEasyRadio.setSelected(true);
            DifficultyHardRadio.setSelected(false);
        } else if (botDifficulty == 2) {
            DifficultyEasyRadio.setSelected(false);
            DifficultyHardRadio.setSelected(true);
        }
    }

    @FXML
    protected void onActiveBot(Event event) {
        ToggleButton button = (ToggleButton) event.getSource();
        MorpionController.player2IsAI = button.isSelected();
    }

    protected static void RandomPlay(ArrayList<Button> buttons) {
        int[] move = new int[2];
        do {
            move[0] = (int)(Math.random() * 3);
            move[1] = (int)(Math.random() * 3);
        } while (MorpionController.gameBoard[move[0]][move[1]] != 0);
        MorpionController.gameBoard[move[0]][move[1]] = 2;
        buttons.get(move[0] * 3 + move[1]).setText("O");
        MorpionController.PlayerTurn = 1;
    }

    public static void PlayBot(ArrayList<Button> buttons) {
        if (botDifficulty == 1) {
            RandomPlay(buttons);
        } else if (botDifficulty == 2) {
            // Le bot joue en utilisant l'algorithme minimax
            int[] bestMove = getBestMove(MorpionController.gameBoard, 2);
            int row = bestMove[0];
            int col = bestMove[1];

            if (row < 0 || row >= 3 || col < 0 || col >= 3) {
                return;
            }

            MorpionController.gameBoard[row][col] = 2;
            buttons.get(row * 3 + col).setText("O");
            MorpionController.PlayerTurn = 1;
        }
    }

    // Fonction getBestMove qui va parcourir le tableau de jeu et retourner le meilleur coup possible pour le bot
    // en utilisant l'algorithme minimax
    private static int[] getBestMove(int[][] board, int player) {
        int bestValue = (player == 2) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int moveValue;
        int bestRow = -1;
        int bestCol = -1;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    board[row][col] = player;
                    moveValue = minimax(board, false);
                    board[row][col] = 0;
                    if ((player == 2 && moveValue > bestValue) || (player == 1 && moveValue < bestValue)) {
                        bestValue = moveValue;
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }
        return new int[]{bestRow, bestCol};
    }

    // Fonction minimax qui va simuler tous les coups possibles pour les deux joueurs et retourner le meilleur score (score maximal ou minimal)
    // Lorsque c'est le tour du bot, on cherche le score le plus grand possible
    // Lorsque c'est le tour du joueur, on cherche le score le plus petit possible
    private static int minimax(int[][] board, boolean isMaximizing) {
        if (MorpionController.CheckWin(board)) {
            return isMaximizing ? -1 : 1;
        }
        if (!isMovesLeft(board)) {
            return 0;
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentPlayer = isMaximizing ? 2 : 1;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    board[row][col] = currentPlayer;
                    int score = minimax(board, !isMaximizing);
                    board[row][col] = 0;
                    bestScore = isMaximizing ? Math.max(bestScore, score) : Math.min(bestScore, score);
                }
            }
        }
        return bestScore;
    }

    private static boolean isMovesLeft(int[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
