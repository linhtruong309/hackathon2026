public class Game {
    private char[][] board;
    private char currentPlayer;
    private boolean gameOver;

    public Game() {
        board = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
        gameOver = false;
    }

    public boolean makeMove(int column) {
        if (gameOver || column < 0 || column >= 7) {
            return false;
        }

        for (int i = 5; i >= 0; i--) {
            if (board[i][column] == ' ') {
                board[i][column] = currentPlayer;

                if (checkWin(i, column)) {
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
                return true;
            }
        }
        return false;
    }

    private boolean checkWin(int row, int col) {
        char player = board[row][col];

        // horizontal
        if (count(row, col, 0, 1, player) + count(row, col, 0, -1, player) >= 3) return true;

        // vertical
        if (count(row, col, 1, 0, player) + count(row, col, -1, 0, player) >= 3) return true;

        // diagonal \
        if (count(row, col, 1, 1, player) + count(row, col, -1, -1, player) >= 3) return true;

        // diagonal /
        if (count(row, col, 1, -1, player) + count(row, col, -1, 1, player) >= 3) return true;

        return false;
    }

    private int count(int row, int col, int rowDir, int colDir, char player) {
        int count = 0;

        int r = row + rowDir;
        int c = col + colDir;

        while (r >= 0 && r < 6 && c >= 0 && c < 7 && board[r][c] == player) {
            count++;
            r += rowDir;
            c += colDir;
        }

        return count;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void printBoard() {
        System.out.println();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
        }

        System.out.println("-----------------------------");
        System.out.println("  0   1   2   3   4   5   6 ");
        System.out.println();
    }
}