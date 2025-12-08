package TicTacToe;

import game.GameDriver;
import ui.TicTacToeGUI;
import game.Game;

public class Simulation {
    public static char runSimulation(int x){
        TicTacToeGUI gui = new TicTacToeGUI();
        Game game = new Game.Builder()
                .simulation()
                .squareBoardSize(x)
                .build();
        GameDriver driver = new TicTacToeDriver(game, gui);
        driver.start();
        if(driver.winner()==null){
            return 'D';
        }
        else if(driver.winner().getToken()==game.getPlayers().getFirst().getToken()){
            return 'X';
        }
        else{
            return 'O';
        }
    }
}
