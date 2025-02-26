package com.tronai.controller;

import com.tronai.algo.IAI;
import com.tronai.algo.MaxN;
import com.tronai.model.Game;
import com.tronai.util.Move;
import com.tronai.view.GameView;

import java.util.Timer;

public class GameController {
    private final Game game;
    private final GameView gameView;

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
        this.updateView();
    }


    private void updateView() {
        this.gameView.update(game);
    }

    public void startGame() {
        Timer timer = new Timer();
        this.game.initializeTeamsAndPlayers(2, 1);
                this.updateView();
    }

    public void AutoMove() {
        IAI algo =  new MaxN();
        Move bestMove = algo.findBestMove(game.copy(),game.copy().getCurrentPlayer());
        game.makeMove(bestMove);
        updateView();
    }

}