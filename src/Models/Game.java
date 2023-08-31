package Models;

import Exceptions.InvalidGameDimensionException;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nexPlayerIndex;
    private Player winningPlayer;

    public Player getWinningPlayer() {
        return winningPlayer;
    }

    public void setWinningPlayer(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNexPlayerIndex() {
        return nexPlayerIndex;
    }

    public void setNexPlayerIndex(int nexPlayerIndex) {
        this.nexPlayerIndex = nexPlayerIndex;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;

        public Builder setDimensions(int dimension){
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players){
            this.players = players;
            return this;
        }

        public Game build(){
            try{
                isValid();
            } catch (InvalidGameDimensionException e) {
                return null;
            }

            Game game = new Game();
            game.setBoard(new Board(dimension));
            game.setPlayers(players);
            game.setMoves(new LinkedList<>());
            game.setNexPlayerIndex(0);

            return game;
        }

        private boolean isValid() throws InvalidGameDimensionException {
            if (dimension < 3){
                throw new InvalidGameDimensionException("Dimension should be greater than 2");
            }

            return true;
        }
    }
}
