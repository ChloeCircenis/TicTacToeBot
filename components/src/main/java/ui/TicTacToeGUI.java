package ui;

import javax.swing.*;
import java.awt.*;

import TicTacToe.MoveSubject;
import board.Board;
import game.Game;
import board.Cell;
import TicTacToe.TicTacToeDriver;

public class TicTacToeGUI extends MoveSubject {
    private Thread gameThread;
    private final JButton[][] buttons = new JButton[3][3];
    private JFrame frame;

    public TicTacToeGUI() {
        initUI();
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
        reset.addActionListener(e -> reset());

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(reset, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void reset() {
        if (gameThread != null && gameThread.isAlive()) {
            gameThread.interrupt();

            try {
                gameThread.join(1000);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

        notifyReset();
        gameThread = new Thread(() -> {
            notifyStart();
        });
        gameThread.start();
    }

    private void handleClick(int x, int y) {
        notifyMove(x, y);
    }

    public void display(Board board) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Cell cell = board.getCell(x, y);
                String text = "";
                if (cell.getOccupant() != null && cell.getOccupant().name != null) {
                    text = cell.getOccupant().name;
                }
                buttons[x][y].setText(text);
            }
        }
    }
    public void gameOver(String message) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Game Over");
        dialog.setSize(300, 150);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(null); // center on screen
        dialog.setModal(true); // block other windows

        JLabel text = new JLabel(message , SwingConstants.CENTER);
        JButton closeButton = new JButton("Close");

        closeButton.addActionListener(e -> dialog.dispose());

        dialog.add(text, BorderLayout.CENTER);
        dialog.add(closeButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        TicTacToeGUI gui = new TicTacToeGUI();
        Game game = new Game.Builder()
                .onePlayer(gui)
                .squareBoardSize(3)
                .build();
        TicTacToeDriver driver = new TicTacToeDriver(game, gui);
        gui.addObserver(driver, EventType.All);
        driver.start();
    }
}
