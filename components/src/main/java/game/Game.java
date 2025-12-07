package game;

import board.Board;
import player.Player;
import player.PlayerFactory;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board gameBoard;
    private final List<Player> players;
    private boolean isOver;

    public Game(List<Player> players, Board board) {
        this.gameBoard = board;
        this.players = players;
    }

    public boolean isOver() {
        return isOver;
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


    public static class Builder{
        private final List<Player> players = new ArrayList<>();
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
            this.board = new Board(3,3);
            return this;
        }
        public Game build(){
            return new Game(players, board);
        }
    }
}
