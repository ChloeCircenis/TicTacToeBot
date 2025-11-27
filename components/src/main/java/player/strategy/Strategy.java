package player.strategy;

import game.Game;
import game.GameDriver;

public interface Strategy {

    void action(Game game, GameDriver gameDriver);
}
