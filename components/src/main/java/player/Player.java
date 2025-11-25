package player;

import player.strategy.Strategy;

public class Player {
    private Strategy strategy;
    public Player(Strategy strategy) {
        this.strategy = strategy;
    }
}
