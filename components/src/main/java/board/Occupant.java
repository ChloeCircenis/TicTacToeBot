package board;

public abstract class Occupant {
    public final String idNumber;
    public final String name;

    protected Occupant(String idNumber, String name) {
        this.idNumber = idNumber;
        this.name = name;
    }
}
