import TicTacToe.Simulation;
import TicTacToe.TicTacToeDriver;
import TicTacToe.TicTacToePiece;
import board.Board;
import game.Game;
import game.GameDriver;
import org.junit.jupiter.api.Test;
import ui.TicTacToeGUI;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    public void twoPlayerWinTest() {
        TicTacToeGUI gui = new TicTacToeGUI();
        Game game = new Game.Builder()
                .twoPlayer(gui)
                .squareBoardSize(3)
                .build();
        GameDriver driver = new TicTacToeDriver(game, gui);
        driver.placePiece(1,0,game.getPlayers().getLast().getToken());
        driver.placePiece(1,1,game.getPlayers().getLast().getToken());
        driver.placePiece(1,2,game.getPlayers().getLast().getToken());
        assertTrue(driver.endCheck());
        assertTrue(driver.hasWon(game.getGameBoard(),game.getPlayers().getLast().getToken()));
        assertEquals(driver.winner(), game.getPlayers().get(1));
    }
    @Test
    public void testCatsGame(){
        TicTacToeGUI gui = new TicTacToeGUI();
        Game game = new Game.Builder()
                .simulation()
                .squareBoardSize(3)
                .build();
        GameDriver driver = new TicTacToeDriver(game, gui);
        driver.start();
        assertTrue(driver.endCheck());
        assertNull(driver.winner());
    }
    @Test
    public void createResetBoard() {
        TicTacToePiece piece = new TicTacToePiece("Test piece", "X");
        Board board = new Board(3,3);
        board.addOccupant(1,1,piece);
        assertTrue(board.getCell(1,1).contains(piece));
    }
    @Test
    public void solvedGame(){
        char result = Simulation.runSimulation(3);
        assertEquals('D', result);

    }
}
