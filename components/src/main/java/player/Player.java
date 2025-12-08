package player;

import board.Cell;
import board.Occupant;
import game.Game;
import game.GameDriver;
import player.strategy.Strategy;

public class Player {
    private final Strategy strategy;
    private final Occupant occupant;
    public Player(Strategy strategy,  Occupant occupant) {
        this.strategy = strategy;
        this.occupant = occupant;
    }
    public Cell action(Game game, GameDriver gameDriver) {
        return this.strategy.action(game, gameDriver, this);
    }
    public Occupant getToken(){
        return occupant;
    }
    public String getName(){
        return occupant.name;
    }
    public Strategy getStrategy(){
        return strategy;
    }
}
