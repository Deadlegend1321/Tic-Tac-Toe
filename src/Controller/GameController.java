package Controller;

import Models.Game;
import Models.GameStatus;
import Models.Player;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players){
        return Game.getBuilder()
                .setDimensions(dimension)
                .setPlayers(players)
                .build();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public String getWinnerName(Game game){
        return game.getWinningPlayer().getName();
    }

    public void setGameStatus(Game game, GameStatus gameStatus){
        game.setGameStatus(gameStatus);
    }
}
