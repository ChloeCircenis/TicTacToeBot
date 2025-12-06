import TicTacToe.TicTacToePiece;
import board.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {
    @Test
    public void createResetBoard() {
        TicTacToePiece piece = new TicTacToePiece("Test piece", "X");
        Board board = new Board(3,3);
        board.addOccupant(1,1,piece);
        assertTrue(board.getCell(1,1).contains(piece));
    }
}
