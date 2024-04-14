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
        int[] bestMove = GetBestMove(MorpionController.gameBoard, 2);
        int row = bestMove[0];
        int col = bestMove[1];
        MorpionController.gameBoard[row][col] = 2;
        buttons.get(row * 3 + col).setText("O");
        MorpionController.PlayerTurn = 1;
    }

    private static int[] GetBestMove(int[][] board, int player) {
        int bestScore = (player == 2) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    board[row][col] = player;
                    if (player == 2) {
                        currentScore = minimax(board, 0, false);
                        if (currentScore > bestScore) {
                            bestScore = currentScore;
                            bestRow = row;
                            bestCol = col;
                        }
                    } else {
                        currentScore = minimax(board, 0, true);
                        if (currentScore < bestScore) {
                            bestScore = currentScore;
                            bestRow = row;
                            bestCol = col;
                        }
                    }
                    board[row][col] = 0;
                }
            }
        }
        return new int[]{bestRow, bestCol};
    }

    private static int minimax(int[][] board, int depth, boolean isMaximizing) {
        int score = evaluate(board);
        if (score == 10 || score == -10) {
            return score;
        }
        if (!isMovesLeft(board)) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == 0) {
                        board[row][col] = 2;
                        bestScore = Math.max(bestScore, minimax(board, depth + 1, false));
                        board[row][col] = 0;
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == 0) {
                        board[row][col] = 1;
                        bestScore = Math.min(bestScore, minimax(board, depth + 1, true));
                        board[row][col] = 0;
                    }
                }
            }
            return bestScore;
        }
    }

    private static int evaluate(int[][] board) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == 2) {
                    return 10;
                } else if (board[row][0] == 1) {
                    return -10;
                }
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == 2) {
                    return 10;
                } else if (board[0][col] == 1) {
                    return -10;
                }
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 2) {
                return 10;
            } else if (board[0][0] == 1) {
                return -10;
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 2) {
                return 10;
            } else if (board[0][2] == 1) {
                return -10;
            }
        }

        return 0;
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