package com.tronai.controller;

import com.tronai.config.GameConfig;
import com.tronai.model.Game;
import com.tronai.model.Player;
import com.tronai.model.Team;
import com.tronai.util.Cell;
import com.tronai.util.Direction;
import com.tronai.util.Move;
import com.tronai.util.Position;
import com.tronai.view.GameView;
import javafx.scene.layout.GridPane;

public class GameController {
    private final Game game;
    private final GameView gameView;
    private final GameConfig config;

    public GameController(Game game, GameView gameView,GameConfig config) {
        this.game = game;
        this.gameView = gameView;
        this.config = config;
//        setupEventHandlers();
    }
    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
        this.config = new GameConfig(8,8,2,1);
//        setupEventHandlers();
//        initializeTeamsAndPlayers();
        this.updateView();
    }

    private void setupEventHandlers() {
        GridPane gridPane = gameView.getGridPane();
        for (int y = 0; y < game.getHeight(); y++) {
            for (int x = 0; x < game.getWidth(); x++) {
                int finalX = x;
                int finalY = y;
                gridPane.getChildren().get(y * game.getWidth() + x).setOnMouseClicked(event -> {
                    handleCellClick(finalX, finalY);
                });
            }
        }
    }

    private void handleCellClick(int x, int y) {
        Player currentPlayer = game.getCurrentPlayer();
        Move move = new Move(Direction.RIGHT); // Example: Always move right
        if (game.makeMove(move)) {
            updateView();
        } else {
            System.out.println("Invalid move!");
        }
    }

    private void updateView() {
        this.gameView.update(game);
    }

    public void startGame() {
        this.game.initializeTeamsAndPlayers(2, 1);
                game.makeMove(new Move(Direction.UP));
                game.makeMove(new Move(Direction.RIGHT));
                this.updateView();
    }

    public void initializeTeamsAndPlayers() {
        this.game.initializeTeamsAndPlayers(2, 1);
        this.startGame();
        this.updateView();
    }

    private static Position calculateStartPosition(
            int teamIndex, int playerIndex,
            int numTeams, int playersPerTeam,
            int width, int height
    ) {
        // Positionner les joueurs aux coins et le long des bords
        int x, y;

        if (numTeams == 2) {
            // Pour 2 équipes, placer les joueurs aux extrémités opposées
            x = (teamIndex == 0) ? 2 : width - 3;
            y = height / 2 + (playerIndex - playersPerTeam / 2);
        } else {
            // Pour plus d'équipes, répartir autour du plateau
            double angle = 2 * Math.PI * teamIndex / numTeams;
            x = (int) (width / 2 + (width / 3) * Math.cos(angle));
            y = (int) (height / 2 + (height / 3) * Math.sin(angle));
        }

        return new Position(x, y);
    }
}