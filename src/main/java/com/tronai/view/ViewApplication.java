package com.tronai.view;

import com.tronai.config.ConfigLoader;
import com.tronai.controller.GameController;
import com.tronai.model.Game;
import com.tronai.util.AlgorithmType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ViewApplication extends Application {
//    public static GameConfig config;
    @Override
    public void start(Stage stage) throws IOException {
//        int numTeams = selectNumberOfTeams();
//        int playersPerTeam = selectPlayersPerTeam();
        // Initialize Model (Game)
        Game game = new Game(ConfigLoader.getIntProperty("grid.width"), ConfigLoader.getIntProperty("grid.height")); // Example: 10x10 grid with 2 players
        GameView gameView = new GameView(game);
        GameController gameController = new GameController(game, gameView);

        gameController.startGame();
        // Set up the scene and stage
        Scene scene = new Scene(gameView.getView());
        scene.setOnKeyPressed(event -> handleKeyPress(event, gameController));
        stage.setTitle("Tron Game - AI");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

    }

    private void handleKeyPress(KeyEvent event, GameController gameController) {
        KeyCode keyCode = event.getCode();
        switch (keyCode) {
            case ENTER:

                gameController.AutoMove(); // Move up
                break;
            default:
                break;
        }
    }

    private void selectTeamsAlgorithm(Game game) {
        List<AlgorithmType> teamAlgorithms = new ArrayList<>();
        for (int i = 0; i < game.getTeams().size(); i++) {
            AlgorithmType selectedAlgorithm = showAlgorithmDialog("Team " + (i + 1));
            teamAlgorithms.add(selectedAlgorithm);
        }
    }

    private AlgorithmType showAlgorithmDialog(String teamName) {
        List<AlgorithmType> choices = List.of(AlgorithmType.values());
        ChoiceDialog<AlgorithmType> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Algorithm Selection");
        dialog.setHeaderText("Choose an algorithm for " + teamName);
        dialog.setContentText("Algorithm:");

        Optional<AlgorithmType> result = dialog.showAndWait();
        return result.orElse(AlgorithmType.RANDOM); // Default to RANDOM if no selection is made
    }

    private int selectNumberOfTeams() {
        List<Integer> choices = List.of(1, 2, 3, 4); // Example: Allow 1-4 teams
        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Team Configuration");
        dialog.setHeaderText("Select the number of teams");
        dialog.setContentText("Teams:");

        Optional<Integer> result = dialog.showAndWait();
        return result.orElse(1); // Default to 1 team
    }

    private int selectPlayersPerTeam() {
        List<Integer> choices = List.of(1, 2, 3, 4, 5); // Example: 1-5 players per team
        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Players Configuration");
        dialog.setHeaderText("Select the number of players ");
        dialog.setContentText("Players per team:");

        Optional<Integer> result = dialog.showAndWait();
        return result.orElse(1); // Default to 1 player per team
    }


}