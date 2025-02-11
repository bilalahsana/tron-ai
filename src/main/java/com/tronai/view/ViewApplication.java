package com.tronai.view;

import com.tronai.config.GameConfig;
import com.tronai.controller.GameController;
import com.tronai.model.Game;
import com.tronai.model.Player;
import com.tronai.model.Team;
import com.tronai.util.AlgorithmType;
import com.tronai.util.Position;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ViewApplication extends Application {
    public static GameConfig config;
    @Override
    public void start(Stage stage) throws IOException {
        // Initialize Model (Game)
        Game game = new Game(config.getWidth(), config.getHeight()); // Example: 10x10 grid with 2 players
        GameView gameView = new GameView(game);
        GameController gameController = new GameController(game, gameView);

        gameController.startGame();
        // Set up the scene and stage
        Scene scene = new Scene(gameView.getGridPane(),
                config.getWidth()*config.getCellSize()+10,
                config.getHeight()*config.getCellSize()+10);
        stage.setTitle("Tron Game - AI");
        stage.setScene(scene);
        stage.show();

    }


//    public static void main(String[] args) {
//     config = GameConfig.fromArgs(args);
//        launch(args);
//    }

}
