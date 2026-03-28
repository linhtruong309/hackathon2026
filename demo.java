import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Game game = new Game();

            System.out.println("Welcome to Four in a Row!");

            while (!game.isGameOver()) {
                game.printBoard();

                System.out.print("Player " + game.getCurrentPlayer() + ", choose column (0-6): ");
                
                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a number.");
                    scanner.next(); // clear invalid input
                    continue;
                }

                int col = scanner.nextInt();

                if (!game.makeMove(col)) {
                    System.out.println("Invalid move. Try again.");
                }
            }

            game.printBoard();
        }
        System.out.println("Player wins!");
    }
}