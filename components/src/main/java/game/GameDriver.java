package game;

import board.Occupant;
import player.Player;

public interface GameDriver {
    public void turn();
    public boolean endCheck();
    public int getTurn();
    public Player evaluateWinner();
    public void placePiece(int x, int y, Occupant symbol);
}
