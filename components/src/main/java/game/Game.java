package game;

import TicTacToe.MoveSubject;
import board.Board;
import player.Player;
import player.PlayerFactory;
import player.strategy.HumanStrategy;
import ui.TicTacToeGUI;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board gameBoard;
    private final List<Player> players;

    public Game(List<Player> players, Board board) {
        this.gameBoard = board;
        this.players = players;
    }
    public Board getGameBoard() {
        return gameBoard;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public Player getByTurn(int turn) {
        return players.get(turn % players.size());
    }
    public static class Builder{
        private final List<Player> players = new ArrayList<>();
        private Board board;
        public Builder(){
        }
        public Builder onePlayer(TicTacToeGUI gui){
            Player player1 = PlayerFactory.createHumanPlayer("X");
            Player player2 = PlayerFactory.createBotPlayer("O");
            players.add(player1);
            gui.addObserver((HumanStrategy)player1.getStrategy(), MoveSubject.EventType.All);
            players.add(player2);
            return this;
        }
        public Builder twoPlayer(TicTacToeGUI gui){
            Player player1 = PlayerFactory.createHumanPlayer("X");
            Player player2 = PlayerFactory.createHumanPlayer("O");
            players.add(player1);
            gui.addObserver((HumanStrategy)player1.getStrategy(), MoveSubject.EventType.All);
            players.add(player2);
            gui.addObserver((HumanStrategy)player2.getStrategy(), MoveSubject.EventType.All);
            return this;
        }
        public Builder squareBoardSize(int x){
            this.board = new Board(x,x);
            return this;
        }
        public Game build(){
            return new Game(players, board);
        }
    }
}
