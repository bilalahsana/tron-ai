package com.tronai.config;

public class GameConfig {
    private final int width;
    private final int height;
    private final int numTeams;
    private final int playersPerTeam;
    private final int cellSize;

    public GameConfig(int width, int height, int numTeams, int playersPerTeam) {
        this.width = width;
        this.height = height;
        this.numTeams = numTeams;
        this.playersPerTeam = playersPerTeam;
        this.cellSize =50;
    }

    public GameConfig(int width, int height, int numTeams, int playersPerTeam, int cellSize) {
        this.width = width;
        this.height = height;
        this.numTeams = numTeams;
        this.playersPerTeam = playersPerTeam;
        this.cellSize = cellSize;
    }

    public static GameConfig fromArgs(String[] args) {
        // Par défaut
        int width = 10;
        int height = 10;
        int numTeams = 2;
        int playersPerTeam = 1;
        int cellSize = 50;

        // Parser les arguments
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "--width":
                    width = Integer.parseInt(args[i + 1]);
                    break;
                case "--height":
                    height = Integer.parseInt(args[i + 1]);
                    break;
                case "--teams":
                    numTeams = Integer.parseInt(args[i + 1]);
                    break;
                case "--players":
                    playersPerTeam = Integer.parseInt(args[i + 1]);
                    break;
                case "--cellSize":
                    cellSize = Integer.parseInt(args[i + 1]);
                    break;
            }
        }

        return new GameConfig(width, height, numTeams, playersPerTeam, cellSize);
    }

    // Getters
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getNumTeams() { return numTeams; }
    public int getPlayersPerTeam() { return playersPerTeam; }
    public int getCellSize() { return cellSize; }
}
