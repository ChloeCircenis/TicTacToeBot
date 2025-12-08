package game;

import board.Board;
import board.Occupant;
import player.Player;

public interface GameDriver {
    void turn();
    boolean endCheck();
    Player winner();
    void placePiece(int x, int y, Occupant symbol);
    boolean hasWon(Board board, Occupant token);
}
