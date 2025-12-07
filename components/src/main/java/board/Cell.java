package board;

public class Cell {
    private final Coordinates coordinates;
    private Occupant occupant = null;
    private boolean wasLastPlaced = false;

    public Cell(int x, int y) {
        this.coordinates = new Coordinates(x, y);
    }

    public Occupant getOccupant() {
        return occupant;
    }

    public void setOccupant(Occupant occupant) {
        this.occupant = occupant;
        this.wasLastPlaced = true;
    }

    public void clearOccupant() {
        this.occupant = null;
        this.wasLastPlaced = false;
    }

    public Coordinates getCoord() {
        return this.coordinates;
    }


    public boolean isEmpty() {
        return occupant == null;
    }

    public boolean contains(Occupant occupant) {
        return this.occupant == occupant;
    }
}
