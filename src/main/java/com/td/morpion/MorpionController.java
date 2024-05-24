package com.td.morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.event.Event;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MorpionController {
    private Stage modalDialog;
    private Stage botDialog;
    private Stage playerNameDialog;
    private Stage winDialog;
    private WinController winDialogController;

    static String player1Name = "Player 1";
    static String player2Name = "Player 2";
    static boolean player2IsAI = false;
    static int PlayerWhoStart = 3;
    static int PlayerTurn = 1;
    static int GameStatus = 0;
    static int Player1Score = 0;
    static int Player2Score = 0;
    static int[] winningLine;
    static String Winner = "";
    static int[][] gameBoard = new int[3][3];
    static ArrayList<Button> buttonsCache = new ArrayList<Button>();

    public void setModalDialog(Stage modal) {
        this.modalDialog = modal;
    }

    public void setBotDialog(Stage bot) {
        this.botDialog = bot;
    }

    public void setPlayerNameDialog(Stage playerName) {
        this.playerNameDialog = playerName;
    }

    public void setWinDialog(Stage win) {
        this.winDialog = win;
    }

    public void setWinDialogController(WinController winController) {
        this.winDialogController = winController;
    }

    @FXML
    private RadioButton FirstPlayerRadio;

    @FXML
    private RadioButton SecondPlayerRadio;

    @FXML
    private RadioButton RandomPlayerRadio;

    @FXML
    private Label StatusLabel;

    @FXML
    private Button ButtonStart;

    @FXML
    private GridPane ButtonsGrid;

    public static void ResetGameBoard() {
        Winner = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = 0;
            }
        }

        if (!buttonsCache.isEmpty()) {
            buttonsCache.forEach(button -> button.setText(""));
            buttonsCache.forEach(button -> button.setStyle(""));
        }
    }

    @FXML
    protected void StartGame(Event event) {
        System.out.println("Starting game");
        GameStatus = 1;
        StatusLabel.setText("C'est parti !");
        PlayerTurn = PlayerWhoStart;

        Button theButton = (Button) event.getSource();

        buttonsCache = new ArrayList<Button>();
        for (int i = 0; i < 9; i++) {
            Button button = (Button) theButton.getScene().lookup("#Button" + i);
            buttonsCache.add(button);
            System.out.println("Button added to cache: " + button.getId());
        }

        if (PlayerTurn == 3) {
            PlayerTurn = (int) (Math.random() * 2) + 1;
        }

        if (PlayerTurn == 2 && player2IsAI) {
            BotController.PlayBot(buttonsCache);

        }
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

    public static boolean checkIfGameEquality(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean CheckWin(int[][] board) {
        // Vérifier les lignes
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != 0) {
                winningLine = new int[]{i*3, i*3+1, i*3+2};
                return true;
            }
        }

        // Vérifier les colonnes
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != 0) {
                winningLine = new int[]{j, j+3, j+6};
                return true;
            }
        }

        // Vérifier les diagonales
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0) {
            winningLine = new int[]{0, 4, 8};
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != 0) {
            winningLine = new int[]{2, 4, 6};
            return true;
        }

        return false;
    }

    private void handleKey(Button theButton) {
        if (GameStatus == 2) {
            return;
        }

        //if (GameStatus == 0) {
          //  StartGame();
        //}

        if (PlayerTurn == 3) {
            PlayerTurn = (int) (Math.random() * 2) + 1;
        }

        String ButtonIdString = theButton.getId().substring(6);
        int ButtonId = Integer.parseInt(ButtonIdString);
        int x = ButtonId / 3;
        int y = ButtonId % 3;

        buttonsCache = new ArrayList<Button>();
        for (int i = 0; i < 9; i++) {
            Button button = (Button) theButton.getScene().lookup("#Button" + i);
            buttonsCache.add(button);
        }

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
                BotController.PlayBot(buttonsCache);
            }
        } else {
            theButton.setText("O");
            gameBoard[x][y] = 2;
            PlayerTurn = 1;
        }

        if (CheckWin(gameBoard)) {
            if (winningLine != null) {
                for (int index : winningLine) {
                    Button winningButton = (Button) theButton.getScene().lookup("#Button" + index);
                    winningButton.setStyle("-fx-background-color: Yellow;");
                }
            }
            if (PlayerTurn == 1) {
                Winner = player2IsAI ? "L'IA" : player2Name;
                Player2Score++;
            } else {
                Winner = player1Name;
                Player1Score++;
            }
            StatusLabel.setText(Winner  + " a gagné !");
            winDialog.show();
            winDialogController.AnnounceWinner(Winner);
            winDialogController.setScoreText(player1Name, player2Name, Player1Score, Player2Score);
            GameStatus = 2;
        } else if (checkIfGameEquality(gameBoard)) {
            StatusLabel.setText("Match nul !");
            GameStatus = 2;
        } else {
            StatusLabel.setText("C'est à " + (PlayerTurn == 1 ? player1Name : player2IsAI ? "l'ordinateur" : player2Name));
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
        playerNameDialog.show();
    }
    @FXML
    protected void PlayAgainstAI() {
        botDialog.show();
    }

    @FXML
    protected void CloseGame() {
        System.exit(0);
    }

    @FXML
    protected void initialize() {
        CheckSelectedRadioButton(PlayerWhoStart);
        ResetGameBoard();
        ButtonStart.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 20px; -fx-padding: 10px 20px; -fx-border-radius: 5px;");
        ButtonsGrid.setStyle("-fx-padding: 10px;");
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

    @FXML
    protected void handleTheme(ActionEvent event) {
        // Todo selection de themes
    }
}