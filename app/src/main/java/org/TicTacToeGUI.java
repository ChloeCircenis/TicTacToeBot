package org;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import game.Game;
import board.Cell;
import game.GameDriver;
import player.Player;
import board.Occupant;
import TicTacToe.TicTacToeDriver;
import player.strategy.HumanStrategy;

public class TicTacToeGUI {

    private final Game game;
    private final GameDriver driver;
    private final JButton[][] buttons = new JButton[3][3];
    private JFrame frame;

    public TicTacToeGUI(Game game, GameDriver gameDriver) {
        this.game = game;
        this.driver = gameDriver;
        initUI();
        refreshBoard();
    }
    private void initUI() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int finalX = x;
                int finalY = y;

                JButton btn = new JButton("");
                btn.setFont(new Font("Arial", Font.BOLD, 60));

                btn.addActionListener(e -> handleClick(finalX, finalY));

                buttons[x][y] = btn;
                boardPanel.add(btn);
            }
        }

        JButton reset = new JButton("Reset Game");
        reset.setFont(new Font("Arial", Font.BOLD, 20));
        reset.addActionListener(e -> resetGame());

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(reset, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void handleClick(int x, int y) {
        if (game.isOver()) return;

        Cell cell = game.getGameBoard().getCell(x, y);

        // Ignore filled cells
        if (cell.getOccupant() != null &&
                cell.getOccupant().name != null &&
                !cell.getOccupant().name.isEmpty()) {
            return;
        }

        Player currentPlayer = game.getPlayers().get(driver.getTurn() % game.getPlayers().size());

        // ONLY set selectedCell for humans
        if (currentPlayer.getStrategy() instanceof HumanStrategy hs) {
            hs.setSelectedCell(cell);
        }

        // Let Driver process the move
        driver.turn();       // HUMAN MOVE
        refreshBoard();

        // If game ended after human move, stop.
        if (game.isOver()) {
            endDialog();
            return;
        }

        // If next player is bot, bot moves automatically
        Player nextPlayer = game.getPlayers().get(driver.getTurn() % game.getPlayers().size());

        if (!(nextPlayer.getStrategy() instanceof HumanStrategy)) {
            driver.turn();   // BOT MOVE
            refreshBoard();
        }

        if (game.isOver()) {
            endDialog();
        }
    }

    private void endDialog() {
        Player winner = driver.evaluateWinner();
        JOptionPane.showMessageDialog(frame,
                winner == null ? "Tie!" : "Winner: " + winner.getName());
    }


    private void refreshBoard() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Cell cell = game.getGameBoard().getCell(x, y);
                String text = "";
                if (cell.getOccupant() != null && cell.getOccupant().name != null) {
                    text = cell.getOccupant().name;
                }
                buttons[x][y].setText(text);
            }
        }
    }

    private void resetGame() {
        Game newGame = new Game.Builder()
                .onePlayer()
                .ticTacToe()
                .build();

        TicTacToeGUI newWindow = new TicTacToeGUI(newGame, new TicTacToeDriver(newGame));
        frame.dispose();
    }

    public static void main(String[] args) {
        Game game = new Game.Builder()
                .onePlayer()
                .ticTacToe()
                .build();
        new TicTacToeGUI(game, new TicTacToeDriver(game));
    }
}
