package player;

import TicTacToe.TicTacToePiece;
import player.strategy.HumanStrategy;
import player.strategy.MiniMaxStrategy;

public class PlayerFactory {
    public static Player createHumanPlayer(String symbol){
        Player player = new Player(new HumanStrategy(), new TicTacToePiece("Human Player "+symbol.toString(),symbol), false);
        return player;
    }
    public static Player createBotPlayer(String symbol){
        Player player = new Player(new MiniMaxStrategy(), new TicTacToePiece("Bot Player "+symbol.toString(),symbol), true);
        return player;
    }
}
