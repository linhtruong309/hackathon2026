public class game {
    public static void main(String[] args) {
        System.out.println("Welcome to Four in a Row!");
        // Initialize the game board and other necessary variables here
        // Implement the game logic, including player turns and win conditions
    }
    public class FourInARow {
    private char[][] board;
    private char currentPlayer;
    private boolean gameOver;   

    public Game() {
        board = new Board();
        scanner = new Scanner(System.in);
        System.out.println("Welcome to Four in a Row!");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        player1 = new Player(name, 'X');
        player2 = new Player("AI", 'O');
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
        Game game = new Game();
        game.play();
    }
}
}
