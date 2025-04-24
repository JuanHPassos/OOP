import math
# from file Board.py import Class Board
from Board import Board

def main():
    # Read the initial board state from input
    pieces_line = input().strip()
    # Split the input into individual pieces
    piece_chars = pieces_line.split()

    # Build the board
    board_size = int(math.sqrt(len(piece_chars)))
    initial_state = []
    for i in range(board_size):
        row = []
        for j in range(board_size):
            # Convert to integer and store in the appropriate position
            row.append(int(piece_chars[i * board_size + j]))
        initial_state.append(row)

    # Read the sequence of moves
    moves = input().strip()

    # Create the board and process moves
    board = Board(initial_state, board_size)

    # Print initial state
    board.display_state()

    # Execute the moves
    board.execute_moves(moves)

    # Check and print the final result
    print(f"Posicao final: {board.is_win()}")

if __name__ == "__main__":
    main()