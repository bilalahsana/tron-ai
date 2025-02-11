package com.tronai.util;


import com.tronai.model.Player;

public class Cell {
    private Boolean isEmpty;
    private Player owner;

    public Cell(){
        isEmpty = true;
    }

    public Cell(Player owner){
        isEmpty = false;
        this.owner = owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
        isEmpty = false;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isEmpty(){
        return isEmpty;
    }
}
