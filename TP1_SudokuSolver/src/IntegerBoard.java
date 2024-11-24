/**
 * 
 * Physical implementation of the GameBoard Interface
 * Array of integer type 
 * 
 * @author      Olivier Trudel, Benjamin Dorkins
 * @version     1.0
 * @since       1.0
 */

@SuppressWarnings("hiding")
public class IntegerBoard<Integer> implements GameBoard<Integer> {

	Integer[][] board;
	
	// Constructor
	public IntegerBoard( Integer[][] board ) {
		this.board = board;
	}

    // GameBoard Interface method
    // ---------------------------------------------
	@Override
	public Integer getCell(int x, int y) throws IndexOutOfBoundsException {
		return board[y][x];
	}

	@Override
	public void setCell(int x, int y, Integer value) throws IndexOutOfBoundsException {
		board[y][x] = value;
	}

	@Override
	public int getWidth() {
		return board[0].length;
	}

	@Override
	public int getHeight() {
		return board.length;
	}

	// Utility for pretty printing
	@Override
	public void display() {
		for (int i = 0; i < getWidth(); i++) {
			System.out.println();
			for (int j = 0; j < getHeight(); j++) {
				System.out.print(board[i][j] + " ");
			}
		}
		System.out.println();
	}
	
	// Board is a square matrix
	public boolean isSquareMatrix() {
		return getWidth() == getHeight();
	}
}
