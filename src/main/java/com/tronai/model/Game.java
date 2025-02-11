package com.tronai.model;


import com.tronai.util.Cell;
import com.tronai.util.Direction;
import com.tronai.util.Move;
import com.tronai.util.Position;

import java.util.ArrayList;
import java.util.List;

import static com.tronai.view.ViewApplication.config;

public class Game {
    private final int width;
    private final int height;
    private final Cell[][] grid;
    private final List<Player> players;
    private final List<Team> teams;
    private int currentPlayerIndex;
    private final List<Move> moveHistory;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[width][height];
        this.players = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.moveHistory = new ArrayList<>();
        initializeGrid();
    }

    private void initializeGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell();
            }
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
        Position pos = player.getPosition();
        grid[pos.getY()][pos.getX()] = new Cell(player);
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    private void updateGrid(Player player, Position pos) {
        grid[pos.getY()][pos.getX()] = new Cell(player);
    }


    public boolean makeMove(Move move) {
        Player currentPlayer = getCurrentPlayer();
        if (!currentPlayer.isAlive() || !isValidMove(move)) {
            return false;
        }

        Position newPos = calculateNewPosition(currentPlayer.getPosition(), move);
        updateGrid(currentPlayer, newPos);
        currentPlayer.setPosition(newPos);
        moveHistory.add(move);

        if (!hasValidMoves(currentPlayer)) {
            currentPlayer.die();
        }

        nextTurn();
        return true;
    }

    private Position calculateNewPosition(Position currentPos, Move move) {
        return new Position(
                currentPos.getX() + move.getDirection().dx,
                currentPos.getY() + move.getDirection().dy
        );
    }

    public boolean isValidMove(Move move) {
        Player currentPlayer = getCurrentPlayer();
        Position currentPos = currentPlayer.getPosition();
        Position newPos = calculateNewPosition(currentPos, move);

        return isWithinBounds(newPos) && grid[newPos.getY()][newPos.getX()].isEmpty();
    }

    private boolean isWithinBounds(Position pos) {
        return pos.getX() >= 0 && pos.getX() < width && pos.getY() >= 0 && pos.getY() < height;
    }

    public boolean hasValidMoves(Player player) {
        Position pos = player.getPosition();
        for (Direction dir : Direction.values()) {
            Position newPos = calculateNewPosition(pos, new Move(dir));
            if (isWithinBounds(newPos) && grid[newPos.getY()][newPos.getX()].isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void nextTurn() {
        do {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } while (!getCurrentPlayer().isAlive() && !isGameOver());
    }

    public boolean isGameOver() {
        // Le jeu est terminé si une seule équipe a des joueurs en vie
        long teamsAlive = teams.stream().filter(Team::hasAlivePlayers).count();
        return teamsAlive <= 1;
    }

    public Team getWinningTeam() {
        if (!isGameOver()) return null;
        return teams.stream()
                .filter(Team::hasAlivePlayers)
                .findFirst()
                .orElse(null);
    }

    private Player getPlayerById(int playerId) {
        return players.stream().filter(p -> p.getId() == playerId).findFirst().orElse(null);
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public int getWidth() {
        return width;
    }

    public void initializeTeamsAndPlayers(int numberOfPlayers, int numberOfPlayerPerTeam){
        int numberOfTeams =numberOfPlayers/numberOfPlayerPerTeam + (numberOfPlayers%numberOfPlayerPerTeam == 0?0:1);
        for (int t = 0; t < numberOfTeams; t++) {
            Team team = new Team(t+1, "Team " + (t + 1));

            this.addTeam(team);
            for (int p = 0; p < numberOfPlayers/numberOfTeams; p++) {
                Position startPos = calculateStartPosition(
                        t, p,
                        numberOfTeams,
                        numberOfPlayers/numberOfTeams,
                        getWidth(),
                        getHeight()
                );

                Player player = new Player(
                        p+1,
                        startPos,
                        "Player " + (t + 1) + "-" + (p + 1),
                        team
                );

                team.addPlayer(player);
                addPlayer(player);
            }
        }
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

    public int getHeight() {
        return height;
    }
}
