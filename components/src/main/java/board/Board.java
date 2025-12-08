package board;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int width;
    private final int height;
    private final List<List<Cell>> board = new ArrayList<>();
    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        for  (int i = 0; i < width; i++) {
            List<Cell> column = new ArrayList<>();
            for(int j = 0; j < height; j++) {
                Cell cell = new Cell(i, j);
                column.add(cell);
            }
            board.add(column);
        }
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void resetBoard() {
        for  (List<Cell> column : board) {
            for(Cell cell : column) {
                cell.clearOccupant();
            }
        }
    }

    public Cell getCell(int x, int y){
        return  board.get(x).get(y);
    }

    public boolean isVacant(int x, int y) {
        return board.get(x).get(y).isEmpty();
    }

    public void addOccupant(int x, int y, Occupant occupant) {
        if(isVacant(x,y)) {
            Cell cell = board.get(x).get(y);
            cell.setOccupant(occupant);
        }
    }
}
