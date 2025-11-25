package board;

import java.util.ArrayList;
import java.util.List;

public abstract class Board {
    private final int width;
    private final int height;
    private final List<List<Cell>> board = new ArrayList<>();
    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        for  (int i = 0; i < width; i++) {
            List<Cell> row = new ArrayList<>();
            for(int j = 0; j < height; j++) {
                Cell cell = new Cell(i, j);
                row.add(cell);
            }
            board.add(row);
        }
    }

}
