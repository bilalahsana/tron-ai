package com.tronai.algo;


import com.tronai.model.Game;

public class Node {
    private Game state;
    private int playerId; // The player whose turn it is

    public Node(Game state, int playerId) {
        this.state = state;
        this.playerId = playerId;
    }

    public Game getState() {
        return state;
    }

    public int getPlayerId() {
        return playerId;
    }
}
