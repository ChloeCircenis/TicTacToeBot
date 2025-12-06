package player.strategy;

import board.Cell;
import game.Game;
import game.GameDriver;
import player.Player;

public interface Strategy {

    Cell action(Game game, GameDriver gameDriver, Player actor);
}
