import TicTacToe.TicTacToeDriver;
import game.Game;
import game.GameDriver;
import org.junit.jupiter.api.Test;
import ui.TicTacToeGUI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    @Test
    public void playerOWinTest() {
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

}
