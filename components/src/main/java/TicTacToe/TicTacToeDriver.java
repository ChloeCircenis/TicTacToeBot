package TicTacToe;

import board.Board;
import board.Cell;
import board.Occupant;
import game.Game;
import game.GameDriver;
import player.Player;
import ui.TicTacToeGUI;

import java.util.List;

public class TicTacToeDriver implements GameDriver, MoveObserver {
    private final Game game;
    private final TicTacToeGUI gui;
    private int turn = 0;
    private boolean gameOver = false;

    public TicTacToeDriver(Game game, TicTacToeGUI gui) {
        this.game = game;
        this.gui = gui;
    }

    public void start() {
        System.out.println("Starting TicTacToeDriver");
        gui.display(game.getGameBoard());
        turn = 0;
        while(!gameOver) {
            turn();
            gui.display(game.getGameBoard());
        }
        System.out.println("Game over!");
        Player winner = winner();
        if(winner == null){
            gui.gameOver("It's a cat's game!");
            return;
        }
        gui.gameOver(winner.getName() + " has won!");
    }

    @Override
    public void notifyMove(int x, int y) {    }
    @Override
    public void notifyReset(){
        reset();
    }
    @Override
    public void notifyStart(){
        start();
    }
    @Override
    public void turn() {
        Player currentPlayer = game.getByTurn(turn);
        Cell cell = currentPlayer.action(game, this);
        if(cell.isEmpty()) {
            placePiece(cell.getCoord().xCoord(), cell.getCoord().yCoord(), currentPlayer.getToken());
            turn++;
            endCheck();
        }
    }

    @Override
    public boolean endCheck() {
        Board board = game.getGameBoard();
        boolean someoneWon = hasWon(board, game.getPlayers().getFirst().getToken()) ||
                hasWon(board, game.getPlayers().getLast().getToken());
        gameOver = someoneWon || isBoardFull(board);
        return gameOver;
    }

    @Override
    public void placePiece(int x, int y, Occupant symbol) {
        if (game.getGameBoard().isVacant(x, y)) {
            game.getGameBoard().addOccupant(x, y, symbol);
        }
    }

    @Override
    public Player winner() {
        if (!gameOver){
            return null;
        }
        Board board = game.getGameBoard();
        if (hasWon(board, game.getPlayers().getFirst().getToken())) {
            return game.getPlayers().getFirst();
        }
        if (hasWon(board, game.getPlayers().getLast().getToken())) {
            return game.getPlayers().getLast();
        }
        return null;
    }

    @Override
    public boolean hasWon(Board board, Occupant token) {
        return checkVertical(board, token) || checkHorizontal(board, token) || checkDiagonal(board, token);
    }

    public void reset() {
        System.out.println("Restarting...");
        game.getGameBoard().resetBoard();
        gameOver = false;
        turn = 0;
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
