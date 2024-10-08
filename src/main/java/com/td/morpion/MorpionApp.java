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
        String css = getClass().getResource("/com/td/morpion/themes/dracula.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage modalDialog = new Stage(StageStyle.UNDECORATED);
        modalDialog.initModality(Modality.WINDOW_MODAL);
        modalDialog.initOwner(stage);

        FXMLLoader fxmlRulesLoader = new FXMLLoader(MorpionApp.class.getResource("regles.fxml"));
        Scene sceneRules = new Scene(fxmlRulesLoader.load());
        modalDialog.setScene(sceneRules);
        modalDialog.setTitle("Règles");
        sceneRules.getStylesheets().add(css);

        Stage botDialog = new Stage(StageStyle.DECORATED);
        botDialog.initModality(Modality.NONE);
        botDialog.initOwner(stage);

        FXMLLoader fxmlBotLoader = new FXMLLoader(MorpionApp.class.getResource("bot.fxml"));
        Scene sceneBot = new Scene(fxmlBotLoader.load());
        botDialog.setScene(sceneBot);
        botDialog.setTitle("Bot");
        sceneBot.getStylesheets().add(css);

        Stage playerNameDialog = new Stage(StageStyle.DECORATED);
        playerNameDialog.initModality(Modality.NONE);
        playerNameDialog.initOwner(stage);

        FXMLLoader fxmlPlayerNameLoader = new FXMLLoader(MorpionApp.class.getResource("joueurs.fxml"));
        Scene scenePlayerName = new Scene(fxmlPlayerNameLoader.load());
        playerNameDialog.setScene(scenePlayerName);
        playerNameDialog.setTitle("Nom des joueurs");
        scenePlayerName.getStylesheets().add(css);

        Stage winDialog = new Stage(StageStyle.UNDECORATED);
        winDialog.initModality(Modality.NONE);
        winDialog.initOwner(stage);

        FXMLLoader fxmlWinLoader = new FXMLLoader(MorpionApp.class.getResource("win.fxml"));
        Scene sceneWin = new Scene(fxmlWinLoader.load());
        winDialog.setScene(sceneWin);
        winDialog.setTitle("Victoire !");
        WinController winController = fxmlWinLoader.getController();
        sceneWin.getStylesheets().add(css);

        MorpionController controller = (MorpionController)fxmlLoader.getController();
        controller.setModalDialog(modalDialog);
        controller.setBotDialog(botDialog);
        controller.setPlayerNameDialog(playerNameDialog);
        controller.setWinDialog(winDialog);
        // Permet de récupérer le controller de la fenêtre de victoire pour pouvoir y accéder depuis le controller principal
        controller.setWinDialogController(winController);
    }

    public static void main(String[] args) {
        launch();
    }
}