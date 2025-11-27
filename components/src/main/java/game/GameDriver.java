package game;

public interface GameDriver {
    public void turn();
    public boolean endCheck();
    public void logGameState();
}
