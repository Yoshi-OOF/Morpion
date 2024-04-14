package com.td.morpion;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class BotController {
    @FXML
    protected void onActiveBot(Event event) {
        MorpionController.player2IsAI = true;
    }

    public static void PlayBot(ArrayList<Button> buttons) {
        int[] bestMove = getBestMove(MorpionController.gameBoard, 2);
        int row = bestMove[0];
        int col = bestMove[1];
        MorpionController.gameBoard[row][col] = 2;
        buttons.get(row * 3 + col).setText("O");
        MorpionController.PlayerTurn = 1;
    }

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
