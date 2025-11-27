package player;

import game.Game;
import game.GameDriver;
import player.strategy.Strategy;

public class Player {
    private Strategy strategy;
    public Player(Strategy strategy) {
        this.strategy = strategy;
    }

    public void action(Game game, GameDriver gameDriver) {
        this.strategy.action(game, gameDriver);
    }
}
