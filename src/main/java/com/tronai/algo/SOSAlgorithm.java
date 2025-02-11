package com.tronai.algo;

public class SOSAlgorithm {
//    private Heuristic heuristic;
//    private int maxDepth;
//
//    public SOSAlgorithm(Heuristic heuristic, int maxDepth) {
//        this.heuristic = heuristic;
//        this.maxDepth = maxDepth;
//    }
//
//    /**
//     * Computes the best move for the current team using the SOS algorithm.
//     *
//     * @param state    The current game state.
//     * @param teamId   The ID of the current team.
//     * @return The best direction to move.
//     */
//    public Direction getBestMove(GameState state, int teamId) {
//        Node root = new Node(state, teamId);
//        return sos(root, maxDepth, teamId);
//    }
//
//    private Direction sos(Node node, int depth, int teamId) {
//        if (depth == 0 || node.getState().isGameOver()) {
//            return null; // Base case
//        }
//
//        Direction bestMove = null;
//        int bestValue = Integer.MIN_VALUE;
//
//        for (Direction direction : Direction.values()) {
//            if (node.getState().isValidMove(node.getState().getPlayer(teamId), direction)) {
//                GameState newState = node.getState().clone();
//                newState.applyMove(node.getState().getPlayer(teamId), direction);
//
//                Node childNode = new Node(newState, teamId);
//                int value = 0;
//
//                // Sum the heuristic values for all players in the team
//                for (int playerId : newState.getPlayerIds()) {
//                    if (newState.getPlayer(playerId).getTeamId() == teamId) {
//                        value += heuristic.evaluate(newState, playerId);
//                    }
//                }
//
//                if (value > bestValue) {
//                    bestValue = value;
//                    bestMove = direction;
//                }
//            }
//        }
//
//        return bestMove;
//    }
}