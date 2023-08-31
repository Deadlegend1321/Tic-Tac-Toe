import Controller.GameController;
import Models.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        System.out.println("Welcome to TicTacToe");
        Scanner scanner = new Scanner(System.in);
        System.out.println("What will be the dimension of the game?");
        int dimension = scanner.nextInt();
        System.out.println("No. Of Players : ");
        int noOfPlayers = scanner.nextInt();

        List<Player> players = new LinkedList<>();

        System.out.println("Will there be any bot? y/n");
        String isBot = scanner.next();

        if(isBot.equals("y")){
            noOfPlayers = noOfPlayers - 1;

            System.out.println("Enter the name of the Bot: ");
            String name = scanner.next();

            System.out.println("Enter the symbol of the Bot: ");
            String botSymbol = scanner.next();

            System.out.println("Enter Bot Difficulty level: 1-Easy 2-Medium 3-Hard ?");
            int diificultyLevel = scanner.nextInt();

            players.add(new Bot(botSymbol.charAt(0), name, BotDifficultyLevel.EASY));
        }

        for(int i = 0; i<noOfPlayers; i++){
            System.out.println("What is the name of the player: " + (i+1));
            String name = scanner.next();

            System.out.println("What is the symbol for player: " + (i+1));
            String symbol = scanner.next();

            Player player = new Player(symbol.charAt(0), name, PlayerType.Human);
            players.add(player);
        }

//        Game game = Game.getBuilder().setDimensions(dimension).setPlayers(players).build();

        GameController gameController = new GameController();

        Game game = gameController.createGame(dimension, players);

        while (gameController.getGameStatus(game) == GameStatus.IN_PROGRESS){
            //break;
            System.out.println("Current Board");
            gameController.displayBoard(game);

            gameController.executeNextMove(game);
        }

        if(gameController.getGameStatus(game) == GameStatus.DRAW) {
            System.out.println("Game draw");
        }
        else {
            System.out.println("Game has won by: " + gameController.getWinnerName(game));
        }
        
    }
}