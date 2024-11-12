
@SuppressWarnings("hiding")
public class IntegerBoard<Integer> implements GameBoard<Integer> {

	Integer[][] board;
	
	// Constructor
	public IntegerBoard( Integer[][] board ) {
		this.board = board;
	}

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
	
	public boolean isSquareMatrix() {
		return getWidth() == getHeight();
	}
}