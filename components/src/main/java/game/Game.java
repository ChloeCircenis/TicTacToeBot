package game;

import TicTacToe.TicTacToeDriver;
import board.Board;
import player.Player;
import player.PlayerFactory;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board gameBoard;
    private List<Player> players;
    private boolean isOver;
    private GameDriver driver;

    public Game(List<Player> players, Board board) {
        this.gameBoard = board;
        this.players = players;
        this.driver = driver;
    }

//    public void play(){
//        while(!isOver) {
//            driver.turn();
//            isOver = driver.endCheck();
//        }
//    }

    public boolean isOver() {
        return isOver;
    }
    public void startGame(){
        this.isOver = false;
    }

    public void setGameState(boolean state){
        this.isOver = state;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameDriver getGameDriver() {
        return driver;
    }

    public static class Builder{
        private Game game;
        private List<Player> players = new ArrayList<Player>();
        private Board board;
        public Builder(){
        }
        public Builder onePlayer(){
            Player player1 = PlayerFactory.createHumanPlayer("X");
            Player player2 = PlayerFactory.createBotPlayer("O");
            players.add(player1);
            players.add(player2);
            return this;
        }
        public Builder twoPlayer(){
            Player player1 = PlayerFactory.createHumanPlayer("X");
            Player player2 = PlayerFactory.createHumanPlayer("O");
            players.add(player1);
            players.add(player2);
            return this;
        }
        public Builder ticTacToe(){
//            game.driver = new TicTacToeDriver(game);
            this.board = new Board(3,3);
            return this;
        }
        public Game build(){
            return new Game(players, board);
        }
    }
}
