package TicTacToe;

import board.Board;
import board.Cell;
import board.Occupant;
import game.Game;
import game.GameDriver;
import player.Player;

import java.util.List;

public class TicTacToeDriver implements GameDriver {

    private final Game game;
    private int turn = 0;

    public TicTacToeDriver(Game game) {
        this.game = game;
    }

    @Override
    public void turn() {
        List<Player> players = game.getPlayers();
        Player player = players.get(turn % players.size());

        Cell chosen = player.action(game, this); // move selection
        if (chosen != null) {
            placePiece(chosen.getCoord().xCoord(), chosen.getCoord().yCoord(), player.getToken());
        }

        turn++;
    }

    @Override
    public boolean endCheck() {
        Board board = game.getGameBoard();
        boolean someoneWon = hasWon(board, game.getPlayers().getFirst().getToken()) ||
                hasWon(board, game.getPlayers().getLast().getToken());
        return someoneWon || isBoardFull(board);
    }

    @Override
    public int getTurn() {
        return turn;
    }

    @Override
    public void placePiece(int x, int y, Occupant symbol) {
        game.getGameBoard().addOccupant(x, y, symbol);
//        Cell lastUpdated = game.getGameBoard().getCell(x, y);
    }

    @Override
    public Player evaluateWinner() {
        if (!game.isOver()) return null;

        Board board = game.getGameBoard();
        if (hasWon(board, game.getPlayers().getFirst().getToken())) {
            return game.getPlayers().getFirst();
        }
        if (hasWon(board, game.getPlayers().getLast().getToken())) {
            return game.getPlayers().getLast();
        }
        return null; // draw
    }


    @Override
    public boolean hasWon(Board board, Occupant token) {
        return checkVertical(board, token) || checkHorizontal(board, token) || checkDiagonal(board, token);
    }


    private boolean checkVertical(Board board, Occupant token){
        List<List<Cell>> grid = board.getBoard();
        int width = grid.size();
        int height = grid.getFirst().size();

        for (int y = 0; y < height; y++) {
            boolean rowWin = true;
            for (int x = 0; x < width; x++) {
                if (grid.get(x).get(y).getOccupant() != token) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) return true;
        }
        return false;
    }

    private boolean checkHorizontal(Board board, Occupant token){
        List<List<Cell>> grid = board.getBoard();
        int width = grid.size();
        int height = grid.getFirst().size();
        for (int x = 0; x < width; x++) {
            boolean colWin = true;
            for (int y = 0; y < height; y++) {
                if (grid.get(x).get(y).getOccupant() != token) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) return true;
        }
        return false;
    }

    private boolean checkDiagonal(Board board, Occupant token){
        List<List<Cell>> grid = board.getBoard();
        int width = grid.size();
        int height = grid.getFirst().size();
        if (width == height) {
            boolean diag1 = true;
            boolean diag2 = true;
            for (int i = 0; i < width; i++) {
                if (grid.get(i).get(i).getOccupant() != token) {
                    diag1 = false;
                }
                if (grid.get(i).get(width - 1 - i).getOccupant() != token) {
                    diag2 = false;
                }
            }
            return diag1 || diag2;
        }
        return false;
    }

    private boolean isBoardFull(Board board) {
        for (List<Cell> row : board.getBoard()) {
            for (Cell cell : row) {
                if (cell.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}
