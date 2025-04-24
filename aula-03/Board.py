class Board:
    def __init__(self, initial_state, board_size):
        # Create a deep copy of the initial state to avoid modifying the original
        self.state = [row[:] for row in initial_state]  
        self.board_size = board_size
        # Find and set the initial position of the empty tile (0)
        self.current_x, self.current_y = self.find_initial_position()

    def find_initial_position(self):
        # Search the board to find the position of the empty tile (0)
        for i in range(self.board_size):
            for j in range(self.board_size):
                if self.state[i][j] == 0:
                    return i, j
        return 0, 0  # Default position if not found (shouldn't happen in valid puzzles)

    def execute_moves(self, moves):
        # Execute a sequence of moves (u=up, d=down, l=left, r=right)
        for move in moves:
            if move == 'u':
                self.move_up()
            elif move == 'd':
                self.move_down()
            elif move == 'l':
                self.move_left()
            elif move == 'r':
                self.move_right()
            # Display the board state after each move
            self.display_state()

    def move_up(self):
        # Move the empty tile up by swapping with the tile below
        if self.current_x < self.board_size - 1:
            self.swap_positions(self.current_x, self.current_y, self.current_x + 1, self.current_y)
            self.current_x += 1

    def move_down(self):
        # Move the empty tile down by swapping with the tile above
        if self.current_x > 0:
            self.swap_positions(self.current_x, self.current_y, self.current_x - 1, self.current_y)
            self.current_x -= 1

    def move_left(self):
        # Move the empty tile left by swapping with the tile to the right
        if self.current_y < self.board_size - 1:
            self.swap_positions(self.current_x, self.current_y, self.current_x, self.current_y + 1)
            self.current_y += 1

    def move_right(self):
        # Move the empty tile right by swapping with the tile to the left
        if self.current_y > 0:
            self.swap_positions(self.current_x, self.current_y, self.current_x, self.current_y - 1)
            self.current_y -= 1

    def swap_positions(self, x1, y1, x2, y2):
        # Swap the values of two positions on the board
        self.state[x1][y1], self.state[x2][y2] = self.state[x2][y2], self.state[x1][y1]

    def display_state(self):
        # Print the game board in a formatted grid
        for i in range(self.board_size):
            # Print top border of the row
            print("+", end="")
            for k in range(self.board_size):
                print("------+", end="")  # Each cell has a 7-character wide border
            print()  # New line after the border
            
           # Print cell values row
            for j in range(self.board_size):
                value = self.state[i][j]
                # If the value is 0, print a space; otherwise, print the number
                print(f"|  {'  ' if value == 0 else f'{value:2d}'}  ", end="")
            print("|")  # Close the last cell in the row
        
        # Print bottom border of the board
        print("+", end="")
        for k in range(self.board_size):
            print("------+", end="")
        print("\n")  # Extra new line for spacing

    def is_win(self):
        # Check if the current state is the winning configuration (sequential numbers)
        aux = 0
        for i in range(self.board_size):
            for j in range(self.board_size):
                if aux != self.state[i][j]:
                    return False
                aux += 1
        return True