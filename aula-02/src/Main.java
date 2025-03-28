import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the next complete line of input
        // Remove any unnecessary whitespace from beginning to end
        String piecesLine = scanner.nextLine().trim();
        // Separate pieces
        String[] pieceChars = piecesLine.split(" ");

        // Build the board
        int boardSize = (int) Math.sqrt(pieceChars.length);
        int[][] initialState = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                // Cast to Integer and save
                initialState[i][j] = Integer.parseInt(pieceChars[(i * boardSize) + j]);
            }
        }
        // Consume the line break after the numbers
        scanner.nextLine();

        // Read the sequence of moves
        String moves = scanner.nextLine().trim();

        // Create the board and process moves
        Board board = new Board(initialState, boardSize);
        board.executeMoves(moves);

        // Check result
        System.out.print("Posicao final: " + board.isWin());

        scanner.close();
    }
}