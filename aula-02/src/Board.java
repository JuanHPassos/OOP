class Board {
    private int[][] state;
    private int currentX; // current position (row)
    private int currentY; // current position (column)
    private int boardSize; // Board dimension

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
        for (int i = 0; i < boardSize; i++) {
            System.out.println("+------+------+------+");
            for (int j = 0; j < boardSize; j++) {
                System.out.print("|   " + state[i][j] + "  ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("+------+------+------+\n");
    }

    public boolean isWin() {
        int aux = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if(aux != state[i][i]) return false;
                aux++;
            }
        }
        return true;
    }
}