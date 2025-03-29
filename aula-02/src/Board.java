class Board {
    private int[][] state;
    private int currentX; // current position (row)
    private int currentY; // current position (column)
    private final int boardSize; // Board dimension

    public Board(int[][] initialState, int boardSize) {
        this.state = initialState;
        this.boardSize = boardSize;
        // Find the initial position (value 0)
        findInitialPosition();
    }

    private void findInitialPosition() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (state[i][j] == 0) {
                    currentX = i;
                    currentY = j;
                    return;
                }
            }
        }
    }

    public void executeMoves(String moves) {
        for (char move : moves.toCharArray()) {
            switch (move) {
                case 'u': moveUp(); break;
                case 'd': moveDown(); break;
                case 'l': moveLeft(); break;
                case 'r': moveRight(); break;
            }
            displayState();
        }
    }

    private void moveUp() {
        if (currentX < boardSize - 1) {
            swapPositions(currentX, currentY, currentX + 1, currentY);
            currentX++;
        }
    }

    private void moveDown() {
        if (currentX > 0) {
            swapPositions(currentX, currentY, currentX - 1, currentY);
            currentX--;
        }
    }

    private void moveLeft() {
        if (currentY < boardSize - 1) {
            swapPositions(currentX, currentY, currentX, currentY + 1);
            currentY++;
        }
    }

    private void moveRight() {
        if (currentY > 0) {
            swapPositions(currentX, currentY, currentX, currentY - 1);
            currentY--;
        }
    }

    private void swapPositions(int x1, int y1, int x2, int y2) {
        int temp = state[x1][y1];
        state[x1][y1] = state[x2][y2];
        state[x2][y2] = temp;
    }

    public void displayState() {
        // Print the game board state in a formatted grid
        for (int i = 0; i < boardSize; i++) {
            // Print top border of the row
            System.out.print("+");
            for(int k = 0; k < boardSize; k++){
                System.out.print("------+");  // Each cell has a 7-character wide border
            }
            
            System.out.print("\n");  // New line after the border
            
            // Print cell values row
            for (int j = 0; j < boardSize; j++) {
                // Format each number to take exactly 2 spaces (right-aligned)
                // %2d means:
                // - %d for integer
                // - 2 for minimum width of 2 characters
                System.out.printf("|  %2d  ", state[i][j]);
            }
            System.out.print("|");  // Close the last cell in the row
            System.out.println();   // New line after values
        }
        
        // Print bottom border of the board
        System.out.print("+");
        for(int k = 0; k < boardSize; k++){
            System.out.print("------+");
        }
        System.out.println("\n");  // Extra new line for spacing
    }

    public boolean isWin() {
        int aux = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if(aux != state[i][j]) return false;
                aux++;
            }
        }
        return true;
    }
}