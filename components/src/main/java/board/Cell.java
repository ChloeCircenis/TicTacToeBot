package board;

public class Cell {
    private final int xCoord;
    private final int yCoord;
    private Occupant occupant = null;

    public Cell(int x, int y) {
        this.xCoord = x;
        this.yCoord = y;
    }
}