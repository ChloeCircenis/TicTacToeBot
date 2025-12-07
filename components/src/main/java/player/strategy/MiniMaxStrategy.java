package player.strategy;

import board.Cell;
import game.Game;
import game.GameDriver;
import player.Player;

import java.util.List;

public class MiniMaxStrategy implements Strategy {

    @Override
    public Cell action(Game game, GameDriver gameDriver, Player actor) {
        return bestMove(game, actor, gameDriver);
    }

    private int minTurn(Game game, int depth, GameDriver gameDriver){
        int bestValue = Integer.MAX_VALUE;
        for(List<Cell> row : game.getGameBoard().getBoard()){
            for(Cell cell : row){
                if(cell.isEmpty()) {
                    cell.setOccupant(game.getPlayers().getLast().getToken());
                    int value = miniMax(game, depth + 1, true, gameDriver);
                    cell.clearOccupant(); // FIXED
                    bestValue = Math.min(bestValue, value);
                }
            }
        }
        return bestValue;
    }

    private int maxTurn(Game game, int depth, GameDriver gameDriver){
        int bestValue = Integer.MIN_VALUE;
        for(List<Cell> row : game.getGameBoard().getBoard()){
            for(Cell cell : row){
                if(cell.isEmpty()) {
                    cell.setOccupant(game.getPlayers().getFirst().getToken());
                    int value = miniMax(game, depth + 1, false, gameDriver);
                    cell.clearOccupant(); // FIXED
                    bestValue = Math.max(bestValue, value);
                }
            }
        }
        return bestValue;
    }

    private int miniMax(Game game, int depth, boolean isMaximizing, GameDriver gameDriver) {
        if(game.isOver()){
            return evaluate(gameDriver, game);
        } else if(isMaximizing){
            return maxTurn(game, depth, gameDriver);
        } else {
            return minTurn(game, depth, gameDriver);
        }
    }

    private Cell bestMove(Game game, Player actor, GameDriver gameDriver){
        int bestVal = Integer.MIN_VALUE;
        Cell bestCell = null;

        for(List<Cell> row : game.getGameBoard().getBoard()){
            for(Cell cell : row){
                if(cell.isEmpty()) {

                    cell.setOccupant(actor.getToken());
                    int moveVal = miniMax(game, 1, actor.isMaximizing(), gameDriver);
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


    static int evaluate(GameDriver gameDriver, Game game){
        Player winner = gameDriver.evaluateWinner();
        if(winner == game.getPlayers().getFirst()){
            return 10;
        }
        else if(winner == game.getPlayers().getLast()){
            return -10;
        }
        return 0;
    }
}
