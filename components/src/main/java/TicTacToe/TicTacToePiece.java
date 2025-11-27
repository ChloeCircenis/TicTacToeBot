package TicTacToe;

import board.Occupant;

public class TicTacToePiece extends Occupant {
    private final char symbol;

    public TicTacToePiece(String idNumber, char  symbol) {
        super(idNumber);
        this.symbol = symbol;
    }

}
