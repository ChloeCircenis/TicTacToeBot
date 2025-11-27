package board;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final Coordinates coordinates;
    private Occupant occupant = null;
    List<Cell> neighbours = new ArrayList<>();

    public Cell(int x, int y) {
        this.coordinates = new Coordinates(x, y);
    }
    public Occupant getOccupant() {
        return occupant;
    }
    public void setOccupant(Occupant occupant) {
        this.occupant = occupant;
    }
    public Coordinates getCoord() {
        return this.coordinates;
    }
    public void clearOccupant() {
        this.occupant = null;
    }
    public void addAdjacencies(List<Cell> adjacencies) {
        for(Cell adjacency : adjacencies) {
            neighbours.add(adjacency);
        }
    }
    public void addAdjacencies(Cell adjacency) {
        neighbours.add(adjacency);
    }
    public List<Cell> getNeighbours() {
        return neighbours;
    }
    public boolean isEmpty() {
        return occupant == null;
    }
}