package com.tronai.view;

import com.tronai.config.ConfigLoader;
import com.tronai.config.GameConfig;
import com.tronai.controller.GameController;
import com.tronai.model.Game;
import com.tronai.model.Player;
import com.tronai.model.Team;
import com.tronai.util.AlgorithmType;
import com.tronai.util.Direction;
import com.tronai.util.Position;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ViewApplication extends Application {
//    public static GameConfig config;
    @Override
    public void start(Stage stage) throws IOException {
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
            case UP:
                gameController.move(Direction.UP); // Move up
                break;
            case DOWN:
                gameController.move(Direction.DOWN); // Move down
                break;
            case LEFT:
                gameController.move(Direction.LEFT); // Move left
                break;
            case RIGHT:
                gameController.move(Direction.RIGHT); // Move right
                break;
            default:
                // Handle other key presses if needed
                break;
        }
    }




}