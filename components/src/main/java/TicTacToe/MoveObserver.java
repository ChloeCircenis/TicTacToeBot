package TicTacToe;

public interface MoveObserver {
    void notifyMove(int x, int y);
    void notifyReset();
    void notifyStart();
}
