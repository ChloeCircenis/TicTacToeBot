package TicTacToe;

import board.Occupant;

public class TicTacToePiece extends Occupant {
    private final String symbol;

    public TicTacToePiece(String idNumber, String symbol) {
        super(idNumber,symbol);
        this.symbol = symbol;
    }
}
