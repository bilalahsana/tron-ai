package com.tronai.util;

public class Move {
    public final int dx;
    public final int dy;

    public Move(Direction dir) {
        this.dx = dir.dx;
        this.dy = dir.dy;
    }

    public Move getDirection() {
        return this;
    }
}