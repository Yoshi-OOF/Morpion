package com.td.morpion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Modality;
import java.io.IOException;

public class MorpionApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MorpionApp.class.getResource("morpion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Jeu du Morpion");
        stage.setScene(scene);;
        stage.show();
        //scene.getStylesheets().add("https://raw.githubusercontent.com/antoniopelusi/JavaFX-Dark-Theme/main/style.css");

        Stage modalDialog = new Stage(StageStyle.UNDECORATED);
        modalDialog.initModality(Modality.WINDOW_MODAL);
        modalDialog.initOwner(stage);

        FXMLLoader fxmlRulesLoader = new FXMLLoader(MorpionApp.class.getResource("regles.fxml"));
        Scene sceneRules = new Scene(fxmlRulesLoader.load());
        modalDialog.setScene(sceneRules);

        Stage botDialog = new Stage(StageStyle.DECORATED);
        botDialog.initModality(Modality.NONE);
        botDialog.initOwner(stage);

        FXMLLoader fxmlBotLoader = new FXMLLoader(MorpionApp.class.getResource("bot.fxml"));
        Scene sceneBot = new Scene(fxmlBotLoader.load());
        botDialog.setScene(sceneBot);
        botDialog.setTitle("Bot");

        Stage playerNameDialog = new Stage(StageStyle.DECORATED);
        playerNameDialog.initModality(Modality.NONE);
        playerNameDialog.initOwner(stage);

        FXMLLoader fxmlPlayerNameLoader = new FXMLLoader(MorpionApp.class.getResource("joueurs.fxml"));
        Scene scenePlayerName = new Scene(fxmlPlayerNameLoader.load());
        playerNameDialog.setScene(scenePlayerName);
        playerNameDialog.setTitle("Nom des joueurs");

        Stage winDialog = new Stage(StageStyle.UNDECORATED);
        winDialog.initModality(Modality.WINDOW_MODAL);
        winDialog.initOwner(stage);
        winDialog.setX(stage.getX() + stage.getWidth() / 2 - 150);
        winDialog.setY(stage.getY() + stage.getHeight() / 2 - 150);


        FXMLLoader fxmlWinLoader = new FXMLLoader(MorpionApp.class.getResource("win.fxml"));
        Scene sceneWin = new Scene(fxmlWinLoader.load());
        winDialog.setScene(sceneWin);
        winDialog.setTitle("Victoire !");
        WinController winController = fxmlWinLoader.getController();

        MorpionController controller = (MorpionController)fxmlLoader.getController();
        controller.setModalDialog(modalDialog);
        controller.setBotDialog(botDialog);
        controller.setPlayerNameDialog(playerNameDialog);
        controller.setWinDialog(winDialog);
        controller.setWinDialogController(winController);
    }

    public static void main(String[] args) {
        launch();
    }
}