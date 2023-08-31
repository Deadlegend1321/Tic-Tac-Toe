package Models;

import Exceptions.InvalidGameDimensionException;
import Strategy.GameWinningStrategy;
import Strategy.OrderOneGameWinningStrategy;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nexPlayerIndex;
    private Player winningPlayer;

    public GameWinningStrategy gameWinningStrategy;

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

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

    public void makeNextMove() {
        Player playerWhosMoveItIs = players.get(nexPlayerIndex);
        System.out.println("It is " + playerWhosMoveItIs.getName() + "'s turn");
        Move move = playerWhosMoveItIs.decideMove(board);

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
            board.applyMove(move);
            moves.add(move);

            if(gameWinningStrategy.checkWinner(board, move)){
                gameStatus = GameStatus.ENDED;
                winningPlayer = playerWhosMoveItIs;
            }

            if (moves.size() == board.getSize() * board.getSize()){
                gameStatus = GameStatus.DRAW;
            }

            nexPlayerIndex += 1;
            nexPlayerIndex %= players.size();
        }
        else {

        }
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
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));

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
