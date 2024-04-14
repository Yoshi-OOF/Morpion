package com.td.morpion;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class BotControllerv1 {
    @FXML protected void onActiveBot(Event event) {
        MorpionController.player2IsAI = true;
    }
    public static void PlayBot(ArrayList<Button> buttons) {
        int[] move = new int[2];
        do {
            move[0] = (int)(Math.random() * 3);
            move[1] = (int)(Math.random() * 3);
        } while (MorpionController.gameBoard[move[0]][move[1]] != 0);
        MorpionController.gameBoard[move[0]][move[1]] = 2;
        buttons.get(move[0] * 3 + move[1]).setText("O");
        MorpionController.PlayerTurn = 1;
    }
}

