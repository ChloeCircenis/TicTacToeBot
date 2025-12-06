package player;

import board.Occupant;
import game.Game;
import game.GameDriver;
import player.strategy.Strategy;

public class Player {
    private Strategy strategy;
    private Occupant occupant;
    private boolean isMaximizing;
    public Player(Strategy strategy,  Occupant occupant, boolean isMaximizing) {
        this.strategy = strategy;
        this.occupant = occupant;
        this.isMaximizing = isMaximizing;
    }

    public void action(Game game, GameDriver gameDriver) {
        this.strategy.action(game, gameDriver, this);
    }
    public Occupant getToken(){
        return occupant;
    }
    public boolean isMaximizing() {
        return isMaximizing;
    }
    public String getName(){
        return occupant.name;
    }
    public Strategy getStrategy(){
        return strategy;
    }
}
