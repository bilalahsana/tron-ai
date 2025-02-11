package com.tronai.view;

import com.tronai.config.GameConfig;
import com.tronai.model.Game;
import com.tronai.util.Cell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class GameView {

    private final GridPane gridPane;
    private final Color[] teamColors; // Array to store colors for each team
    private final Game game;
    private final int CELL_SIZE;

    public GameView(Game game) {
        this.game = game;
        this.gridPane = new GridPane();
        this.gridPane.setMaxSize(game.getWidth(),game.getHeight());
        this.CELL_SIZE = 50;
        this.teamColors = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW}; // Example colors for teams
        initializeGrid();
    }

    public GameView(Game game, GameConfig config) {
        this.game = game;
        this.gridPane = new GridPane();
        this.CELL_SIZE = config.getCellSize();
        this.teamColors = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW}; // Example colors for teams
        initializeGrid();
    }

    private void initializeGrid() {
        int width = game.getWidth();
        int height = game.getHeight();
        Cell[][] grid = game.getGrid();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Rectangle cell = new Rectangle(this.CELL_SIZE, this.CELL_SIZE); // Cell size
                Text text = new Text();
                text.setFont(Font.font(10)); // Set font size for the player ID

                if (grid[x][y].isEmpty()) {
                    cell.setFill(Color.BLACK); // Empty cell
                    text.setText(""); // No text for empty cells
                } else {
                    int teamId = grid[x][y].getOwner().getTeam().getId();
                    cell.setFill(getTeamColor(teamId)); // Color based on team ID
                    text.setText(String.format("T%dP%d",grid[x][y].getOwner().getTeam().getId(),grid[x][y].getOwner().getId())); // Set player ID as text
                }

                cell.setStroke(Color.BLACK); // Border color

                // Use a StackPane to overlay the text on top of the rectangle
                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(cell, text);

                gridPane.add(stackPane, x, y);
            }
        }
    }

    public void update(Game model){
        int width = model.getWidth();
        int height = model.getHeight();
        Cell[][] grid = model.getGrid();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Rectangle cell = new Rectangle(this.CELL_SIZE, this.CELL_SIZE); // Cell size
                Text text = new Text();
                text.setFont(Font.font(10)); // Set font size for the player ID

                if (grid[x][y].isEmpty()) {
                    cell.setFill(Color.WHITE); // Empty cell
                    text.setText(""); // No text for empty cells
                } else {
                    int teamId = grid[x][y].getOwner().getTeam().getId();
                    cell.setFill(getTeamColor(teamId)); // Color based on team ID
                    text.setText(String.format("T%dP%d",grid[x][y].getOwner().getTeam().getId(),grid[x][y].getOwner().getId())); // Set player ID as text
                }

                cell.setStroke(Color.BLACK); // Border color

                // Use a StackPane to overlay the text on top of the rectangle
                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(cell, text);

                gridPane.add(stackPane, x, y);
            }
        }
    }

    private Color getTeamColor(int teamId) {
        // Ensure the teamId is within the bounds of the teamColors array
        if (teamId >= 0 && teamId < teamColors.length) {
            return teamColors[teamId];
        } else {
            return Color.GRAY; // Default color for unknown teams
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public static void displayGrid(Cell[][] grid) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                String data = grid[x][y].isEmpty() ? "    " : String.format("T%dP%d", grid[x][y].getOwner().getTeam().getId(), grid[x][y].getOwner().getId());
                System.out.print("[" + data + "]");
            }
            System.out.println();
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}