
import java.util.Deque;
import java.util.LinkedList;

@SuppressWarnings("rawtypes")
public class SudokuSolver implements GameSolver {

    // Attributes
	IntegerBoard board;
    IntegerBoard solution;
    Tree<IntegerBoard> tree = new LinkedGeneralTree<>();
    private int dim;
    private int perfect_square;
   
    		
    // Constructor
    public SudokuSolver(GameBoard board) {
        this.board = (IntegerBoard) board;
        this.solution = null; // Initially no solution found
        this.dim = board.getWidth();
        this.perfect_square = (int) Math.sqrt(dim); // generalize the solution
    }

    @SuppressWarnings("unchecked")
    public boolean solve() {
    	if (!board.isSquareMatrix()) return false;
        // Add the root (initial board state) to the tree
        Position<IntegerBoard> root = ((LinkedGeneralTree<IntegerBoard>) tree).addRoot(board);

        // DFS setup using a stack
        Deque<Position<IntegerBoard>> stack = new LinkedList<>();
        stack.push(root);

        // DFS loop
        while (!stack.isEmpty()) {
            Position<IntegerBoard> p = stack.pop();
            IntegerBoard currentBoard = p.getElement();

            // Check if this board is a solved solution
            if (isSolved(currentBoard)) {
                solution = currentBoard;
                return true;
            }

            int[] spot = getFirstAvailableSpot(currentBoard);
            if (spot != null) {
                // Try placing numbers (1 to dimension of the board) in the current empty spot
                for (int k = 1; k <= dim; k++) {
                    if (isValidPlacement(currentBoard, spot[0], spot[1], k)) {
                        // Create a copy of the board and set the new value
                        IntegerBoard tmp = new IntegerBoard(generateBoardCopy(currentBoard));
                        tmp.setCell(spot[1], spot[0], k);

                        // Add this new board as a child to the current node and push it to the stack
                        Position<IntegerBoard> child = ((LinkedGeneralTree<IntegerBoard>) tree).addChild(p, tmp);
                        stack.push(child);
                    }
                }
            }
        }
        return false; // No solution found
    }

    public Integer[][] generateBoardCopy(IntegerBoard x) {
        Integer[][] b = new Integer[x.getHeight()][x.getWidth()]; // Correct order of dimensions
        for (int i = 0; i < x.getHeight(); i++) {
            for (int j = 0; j < x.getWidth(); j++) {
                b[i][j] = (Integer) x.getCell(j, i);
            }
        }
        return b;
    }

    public int[] getFirstAvailableSpot(IntegerBoard board) {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getCell(j, i) == (Integer)0) { // Assuming 0 represents an empty cell
                    return new int[]{i, j}; // Return row and column index
                }
            }
        }
        return null; // No empty spots found
    }

    public boolean isSolved(IntegerBoard board) {
        return getFirstAvailableSpot(board) == null;
    }

    public void printSolution() {
    	System.out.println("Sudoku detected ("+board.getWidth()+"x"+board.getHeight()+")");
    	
    	if (!board.isSquareMatrix()) {
    		System.out.println("Illegal format.");
    	}
        if (solution != null) {
            solution.display();
        } else {
            System.out.println("No solution found.");
        }
        System.out.println();	
        //System.out.println(tree);
    }

    public boolean isValidPlacement(IntegerBoard board, int row, int col, int value) {
        return !isNumberInColumn(board, col, value) &&
                !isNumberInRow(board, row, value) &&
                !isNumberInBox(board, row, col, value);
    }

    public boolean isNumberInColumn(IntegerBoard board, int column, int number) {
        for (int i = 0; i < board.getHeight(); i++) {
            if ((Integer)board.getCell(column, i) == number) {
                return true;
            }
        }
        return false;
    }

    public boolean isNumberInRow(IntegerBoard board, int row, int number) {
        for (int j = 0; j < board.getWidth(); j++) {
            if ((Integer)board.getCell(j, row) == number) {
                return true;
            }
        }
        return false;
    }
    
    
    public boolean isNumberInBox(IntegerBoard board, int row, int column, int number) {

        int boxRow = row / perfect_square;
        int boxCol = column / perfect_square;
        int firstRowInBox = boxRow * perfect_square;
        int firstColumnInBox = boxCol * perfect_square;
        
        //System.out.println("row : "+firstRowInBox);
        //System.out.println("col : "+firstColumnInBox);

        for (int i = firstRowInBox; i < firstRowInBox + perfect_square; i++) {
            for (int j = firstColumnInBox; j < firstColumnInBox + perfect_square; j++) {
                if ((Integer)board.getCell(j, i) == number) {
                    return true;
                }
            }
        }
        return false;
    }
    
}

