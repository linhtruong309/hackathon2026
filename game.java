import java.util.Scanner;
import java.util.Random;

public class game {
    private Board board;
    private Scanner scanner;
    private Player player1;
    private Player player2;

    public game() {
        board = new Board();
        scanner = new Scanner(System.in);
        System.out.println("Welcome to Four in a Row!");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        player1 = new Player(name, 'X');
        player2 = new Player("AI", 'O');
    }
    
    // Board class definition
    class Board {
        private char[][] grid;
    
        public Board() {
            grid = new char[6][7];
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    grid[i][j] = '.';
                }
            }
        }
    
        public void display() {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("0 1 2 3 4 5 6");
        }
    
        public boolean dropPiece(int column, char symbol) {
            if (column < 0 || column > 6) return false;
            for (int i = 5; i >= 0; i--) {
                if (grid[i][column] == '.') {
                    grid[i][column] = symbol;
                    return true;
                }
            }
            return false;
        }
    
        public boolean isFull() {
            for (int j = 0; j < 7; j++) {
                if (grid[0][j] == '.') return false;
            }
            return true;
        }
    
        public boolean checkWin(char symbol) {
            // Check horizontal
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 4; j++) {
                    if (grid[i][j] == symbol && grid[i][j+1] == symbol && grid[i][j+2] == symbol && grid[i][j+3] == symbol)
                        return true;
                }
            }
            // Check vertical
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 7; j++) {
                    if (grid[i][j] == symbol && grid[i+1][j] == symbol && grid[i+2][j] == symbol && grid[i+3][j] == symbol)
                        return true;
                }
            }
            // Check diagonal (down-right)
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    if (grid[i][j] == symbol && grid[i+1][j+1] == symbol && grid[i+2][j+2] == symbol && grid[i+3][j+3] == symbol)
                        return true;
                }
            }
            // Check diagonal (up-right)
            for (int i = 3; i < 6; i++) {
                for (int j = 0; j < 4; j++) {
                    if (grid[i][j] == symbol && grid[i-1][j+1] == symbol && grid[i-2][j+2] == symbol && grid[i-3][j+3] == symbol)
                        return true;
                }
            }
            return false;
        }
    }
    
    // Player class definition
    class Player {
        private String name;
        private char symbol;
    
        public Player(String name, char symbol) {
            this.name = name;
            this.symbol = symbol;
        }
    
        public String getName() {
            return name;
        }
    
        public char getSymbol() {
            return symbol;
        }
    }

    public void play() {
        boolean gameOver = false;
        while (!gameOver) {
            board.display();
            if (playerMove(player1)) {
                gameOver = true;
                System.out.println(player1.getName() + " wins!");
            } else if (board.isFull()) {
                gameOver = true;
                System.out.println("It's a draw!");
            } else {
                if (aiMove(player2)) {
                    gameOver = true;
                    System.out.println(player2.getName() + " wins!");
                } else if (board.isFull()) {
                    gameOver = true;
                    System.out.println("It's a draw!");
                }
            }
        }
        board.display();
    }

    private boolean playerMove(Player player) {
        System.out.print(player.getName() + ", enter the column (0-6) to drop your piece: ");
        int column = scanner.nextInt();
        while (column < 0 || column > 6 || !board.dropPiece(column, player.getSymbol())) {
            System.out.print("Invalid move. Try again: ");
            column = scanner.nextInt();
        }
        return board.checkWin(player.getSymbol());
    }

    private boolean aiMove(Player ai) {
        Random rand = new Random();
        int column;
        do {
            column = rand.nextInt(7);
        } while (!board.dropPiece(column, ai.getSymbol()));
        return board.checkWin(ai.getSymbol());
    }
    public static void main(String[] args) {
        game g = new game();
        g.play();
    }
}
