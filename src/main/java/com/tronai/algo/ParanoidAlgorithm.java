package com.tronai.algo;

public class ParanoidAlgorithm {
//    private Heuristic heuristic;
//    private int maxDepth;
//
//    public ParanoidAlgorithm(Heuristic heuristic, int maxDepth) {
//        this.heuristic = heuristic;
//        this.maxDepth = maxDepth;
//    }
//
//    /**
//     * Computes the best move for the current player using the Paranoid algorithm.
//     *
//     * @param state    The current game state.
//     * @param playerId The ID of the current player.
//     * @return The best direction to move.
//     */
//    public Direction getBestMove(GameState state, int playerId) {
//        Node root = new Node(state, playerId);
//        return paranoid(root, maxDepth, playerId);
//    }
//
//    private Direction paranoid(Node node, int depth, int playerId) {
//        if (depth == 0 || node.getState().isGameOver()) {
//            return null; // Base case
//        }
//
//        Direction bestMove = null;
//        int bestValue = Integer.MIN_VALUE;
//
//        for (Direction direction : Direction.values()) {
//            if (node.getState().isValidMove(node.getState().getPlayer(playerId), direction)) {
//                GameState newState = node.getState().clone();
//                newState.applyMove(node.getState().getPlayer(playerId), direction);
//
//                Node childNode = new Node(newState, playerId);
//                int value = heuristic.evaluate(newState, playerId);
//
//                // Assume other players are minimizing the current player's score
//                for (int opponentId : newState.getPlayerIds()) {
//                    if (opponentId != playerId) {
//                        value -= heuristic.evaluate(newState, opponentId);
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