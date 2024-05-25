package com.td.morpion;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ReglesController {
    @FXML
    private Button RulesAcceptButton;

    @FXML
    private AnchorPane AnchorPaneBg;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void onRulesAccept(ActionEvent event) {
        Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Méthode qui est appelée après que le fichier fxml ait été chargé.
     * Elle permet de définir le style du bouton de validation des règles.
     * Elle permet également de définir la position du scrollPane à chaque fois que la fenêtre est ouverte.
     */
    @FXML
    protected void initialize() {
        RulesAcceptButton.setStyle("-fx-background-color: #007e00; -fx-text-fill: #ffffff;");

        // Solution trouvé ici -> https://stackoverflow.com/questions/58775873/how-to-use-timeline-to-perform-a-void-method-every-x-seconds-on-javafx
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            scrollPane.setVvalue(0.0);
            System.out.println("ScrollPane position set to top.");
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
