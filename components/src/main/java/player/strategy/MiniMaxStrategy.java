package player.strategy;

import board.Cell;
import board.Occupant;
import game.Game;
import game.GameDriver;
import player.Player;
import java.util.List;

public class MiniMaxStrategy implements Strategy {

    @Override
    public Cell action(Game game, GameDriver gameDriver, Player actor) {
        return bestMove(game, actor, gameDriver);
    }

    private Cell bestMove(Game game, Player actor, GameDriver gameDriver){
        int bestVal = Integer.MIN_VALUE;
        Cell bestCell = null;
        Occupant opponentToken = null;
        for (Player player : game.getPlayers()) {
            if (!player.equals(actor)) {
                opponentToken = player.getToken();
                break;
            }
        }
        for(List<Cell> row : game.getGameBoard().getBoard()){
            for(Cell cell : row){
                if(cell.isEmpty()) {
                    cell.setOccupant(actor.getToken());
                    int moveVal = miniMax(game, 0, false, actor.getToken(), opponentToken, gameDriver);
                    cell.clearOccupant();
                    if (moveVal > bestVal) {
                        bestVal = moveVal;
                        bestCell = cell;
                    }
                }
            }
        }
        return bestCell;
    }

    private int miniMax(Game game, int depth, boolean isMaximizing, Occupant maximizingToken, Occupant minimizingToken, GameDriver gameDriver) {
        Integer evaluation = evaluateBoard(game, depth, maximizingToken, minimizingToken, gameDriver);
        if (evaluation != null) {
            return evaluation;
        }

        if (isMaximizing) {
            int bestValue = Integer.MIN_VALUE;
            for (List<Cell> row : game.getGameBoard().getBoard()) {
                for (Cell cell : row) {
                    if (cell.isEmpty()) {
                        cell.setOccupant(maximizingToken);
                        bestValue = Math.max(bestValue,
                                miniMax(game, depth + 1, false, maximizingToken, minimizingToken, gameDriver));
                        cell.clearOccupant();
                    }
                }
            }
            return bestValue;
        } else {
            int bestValue = Integer.MAX_VALUE;
            for (List<Cell> row : game.getGameBoard().getBoard()) {
                for (Cell cell : row) {
                    if (cell.isEmpty()) {
                        cell.setOccupant(minimizingToken);
                        bestValue = Math.min(bestValue,
                                miniMax(game, depth + 1, true, maximizingToken, minimizingToken, gameDriver));
                        cell.clearOccupant();
                    }
                }
            }
            return bestValue;
        }
    }

    private Integer evaluateBoard(Game game, int depth, Occupant maximizingToken, Occupant minimizingToken, GameDriver gameDriver) {
        if (gameDriver.hasWon(game.getGameBoard(), maximizingToken)) {
            return 10 - depth;
        }
        if (gameDriver.hasWon(game.getGameBoard(), minimizingToken)) {
            return depth - 10;
        }
        if (isBoardFull(game)) {
            return 0;
        }
        return null;
    }

    private boolean isBoardFull(Game game) {
        for (List<Cell> row : game.getGameBoard().getBoard()) {
            for (Cell cell : row) {
                if (cell.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}
