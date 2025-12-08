package player.strategy;

import TicTacToe.MoveObserver;
import board.Cell;
import game.Game;
import game.GameDriver;
import player.Player;

public class HumanStrategy implements Strategy, MoveObserver {
    private Integer x = null;
    private Integer y = null;
    private final Object lock = new Object();
    private boolean isResetting = false;

    @Override
    public Cell action(Game game, GameDriver driver, Player actor) {
        synchronized (lock) {
            x = null;
            y = null;
            isResetting = false;
            while (x == null || y == null) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (isResetting) {
                return null;
            }
            return game.getGameBoard().getCell(x, y);
        }
    }

    @Override
    public void notifyMove(int x, int y) {
        synchronized (lock) {
            System.out.println("Move from " + x + " to " + y);
            this.x = x;
            this.y = y;
            lock.notifyAll();
        }
    }
    @Override
    public void notifyReset() {
        synchronized (lock) {
            isResetting = true;
            lock.notifyAll();
        }
    }
    @Override
    public void notifyStart(){}
}
