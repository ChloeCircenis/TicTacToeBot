package game;

import board.Board;
import player.Player;

import java.util.List;

public class Game {
    private Board gameBoard;
    private List<Player> players;
    private boolean isOver;
    private GameDriver driver;

    public Game(List<Player> players, GameDriver driver) {
        while(!isOver) {
            driver.turn();
            isOver = driver.endCheck();
        }
    }
    public void startGame(){
        this.isOver = false;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
