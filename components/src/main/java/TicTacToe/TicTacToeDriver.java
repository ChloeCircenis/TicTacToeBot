package TicTacToe;

import board.Board;
import board.Cell;
import board.Occupant;
import game.Game;
import game.GameDriver;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeDriver implements GameDriver {
    private Cell lastUpdated;
    private final int IN_A_ROW = 3;
    private Game game;
    private int turn = 0;

    public TicTacToeDriver(Game game) {
        this.game = game;
    }
    @Override
    public void turn() {
        List<Player> players = game.getPlayers();
        Player player = players.get(turn % players.size());

        Cell chosen = player.action(game, this);  // Get the chosen move

        if (chosen != null) {
            placePiece(
                    chosen.getCoord().xCoord(),
                    chosen.getCoord().yCoord(),
                    player.getToken()
            );
        }

        turn++;
    }


    @Override
    public boolean endCheck() {
        return
                listCheck(getDiagonalLeft(game.getGameBoard(), lastUpdated)) ||
                listCheck(getDiagonalRight(game.getGameBoard(), lastUpdated)) ||
                listCheck(getVertical(game.getGameBoard(), lastUpdated)) ||
                listCheck(getHorizontal(game.getGameBoard(), lastUpdated));
    }

    @Override
    public int getTurn() {
        return turn;
    }

    private List<Cell> getDiagonalLeft(Board board, Cell origin) {
        List<Cell> result = new ArrayList<>();
        int i = origin.getCoord().xCoord();
        int j = origin.getCoord().yCoord();
        while(board.inBounds(i, j)) {
            result.add(board.getCell(i, j));
            i++;
            j++;
        }
        int l = origin.getCoord().xCoord() - 1;
        int k = origin.getCoord().yCoord() - 1;
        while(board.inBounds(l, k)) {
            result.add(0,board.getCell(l, k));
            l--;
            k--;
        }
        return result;
    }

    private List<Cell> getDiagonalRight(Board board, Cell origin) {
        List<Cell> result = new ArrayList<>();
        int i = origin.getCoord().xCoord();
        int j = origin.getCoord().yCoord();
        while(board.inBounds(i, j)) {
            result.add(board.getCell(i, j));
            i++;
            j--;
        }
        int l = origin.getCoord().xCoord() - 1;
        int k = origin.getCoord().yCoord() + 1;
        while(board.inBounds(l, k)) {
            result.add(0,board.getCell(l, k));
            l--;
            k++;
        }
        return result;
    }

    private List<Cell> getVertical(Board board, Cell origin) {
        List<Cell> result = new ArrayList<>();
        int i = origin.getCoord().xCoord();
        int j = origin.getCoord().yCoord();
        while(board.inBounds(i, j)) {
            result.add(board.getCell(i, j));
            j++;
        }
        int l = origin.getCoord().xCoord();
        int k = origin.getCoord().yCoord() - 1;
        while(board.inBounds(l, k)) {
            result.add(0,board.getCell(l, k));
            k--;
        }
        return result;
    }

    private List<Cell> getHorizontal(Board board, Cell origin) {
        List<Cell> result = new ArrayList<>();
        int i = origin.getCoord().xCoord();
        int j = origin.getCoord().yCoord();
        while(board.inBounds(i, j)) {
            result.add(board.getCell(i, j));
            i++;
        }
        int l = origin.getCoord().xCoord() - 1;
        int k = origin.getCoord().yCoord();
        while(board.inBounds(l, k)) {
            result.add(0,board.getCell(l, k));
            l--;
        }
        return result;
    }

    private Boolean listCheck(List<Cell> cells){
        int count = 0;
        for(Cell cell: cells) {
            if(cell.getOccupant() == null){
                return false;
            }
          if (lastUpdated.getOccupant().name.equals(cell.getOccupant().name)) {
              count++;
          } else {
              return false;
          }
        }
        if(count >= IN_A_ROW) {
            return true;
        }
        return false;
    }

    @Override
    public void placePiece(int x, int y, Occupant symbol) {
        game.getGameBoard().addOccupant(x, y, symbol);
        lastUpdated = game.getGameBoard().getCell(x,y);
    }

    @Override
    public Player evaluateWinner() {
        Player winner = null;
        if(game.isOver()){
            if(game.getPlayers().getFirst().getToken() == lastUpdated.getOccupant()){
                return winner = game.getPlayers().getFirst();
            }
            else if(game.getPlayers().getLast().getToken() == lastUpdated.getOccupant()){
                return winner = game.getPlayers().getLast();
            }
        }
        return null;
    }
}

