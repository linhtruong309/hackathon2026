//Students may use this game as a platform to practice and revise their knowledge. First, they will choose a topic then there will be a list of questions about that topic. If the user can answer the question correctly, they will get a chance to play the game. The game will be a simple version of four in a row, where the user will play against the computer. The user will have to connect four of their pieces in a row, either horizontally, vertically, or diagonally, before the computer does.

import java.util.Scanner;

public class fourinarow {

    public static void main(String[] args) {
        System.out.println("Welcome to Four in a Row!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose a topic:");
        String topic = scanner.nextLine();
        System.out.println("You have chosen:" + topic);

    }
}