package player.strategy;

import board.Cell;
import game.Game;
import game.GameDriver;
import player.Player;

public class HumanStrategy implements Strategy {

    private volatile Cell selectedCell = null;

    public void setSelectedCell(Cell cell) {
        this.selectedCell = cell;
    }

    @Override
    public Cell action(Game game, GameDriver driver, Player actor) {
        while (selectedCell == null) {
            try { Thread.sleep(10); } catch(Exception ignored) {}
        }

        Cell move = selectedCell;
        selectedCell = null;
        driver.placePiece(move.getCoord().xCoord(),move.getCoord().yCoord(),actor.getToken());
        return move;
    }
}
