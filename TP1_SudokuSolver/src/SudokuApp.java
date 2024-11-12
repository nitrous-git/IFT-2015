public class SudokuApp {

   public void testCase1() {
      System.out.println("*** Test Case 1 ***");
      Integer[][] var1 = new Integer[][]{{5, 3, 0, 0, 7, 0, 0, 0, 0}, 
    	  								{6, 0, 0, 1, 9, 5, 0, 0, 0}, 
    	  								{0, 9, 8, 0, 0, 0, 0, 6, 0}, 
    	  								{8, 0, 0, 0, 6, 0, 0, 0, 3}, 
    	  								{4, 0, 0, 8, 0, 3, 0, 0, 1}, 
    	  								{7, 0, 0, 0, 2, 0, 0, 0, 6}, 
    	  								{0, 6, 0, 0, 0, 0, 2, 8, 0}, 
    	  								{0, 0, 0, 4, 1, 9, 0, 0, 5},
    	  								{0, 0, 0, 0, 8, 0, 0, 7, 9}};
      IntegerBoard var2 = new IntegerBoard(var1);
      SudokuSolver var3 = new SudokuSolver(var2);
      var3.solve();
      var3.printSolution();
   }

   public void testCase2() {
      System.out.println("*** Test Case 2 ***");
      Integer[][] var1 = new Integer[][]{{0, 0, 0, 0, 0, 0, 0, 1, 0}, {4, 0, 3, 0, 0, 5, 0, 0, 0}, {0, 0, 0, 0, 3, 0, 0, 6, 0}, {0, 5, 0, 4, 6, 0, 0, 0, 0}, {0, 0, 0, 3, 0, 8, 0, 0, 0}, {0, 0, 0, 0, 9, 7, 0, 8, 0}, {0, 6, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 5, 0, 0, 2, 0, 9}, {0, 8, 0, 0, 0, 0, 0, 0, 0}};
      IntegerBoard var2 = new IntegerBoard(var1);
      SudokuSolver var3 = new SudokuSolver(var2);
      var3.solve();
      var3.printSolution();
   }

   public void testCase3() {
      System.out.println("*** Test Case 3 ***");
      Integer[][] var1 = new Integer[][]{{5, 1, 6, 8, 4, 9, 7, 3, 2}, {3, 0, 7, 6, 0, 5, 0, 0, 0}, {8, 0, 9, 7, 0, 0, 0, 6, 5}, {1, 3, 5, 0, 6, 0, 9, 0, 7}, {4, 7, 2, 5, 9, 1, 0, 0, 6}, {9, 6, 8, 3, 7, 0, 0, 5, 0}, {2, 5, 3, 1, 8, 6, 0, 7, 4}, {6, 8, 4, 2, 0, 7, 5, 0, 0}, {7, 9, 1, 0, 5, 0, 6, 0, 0}};
      IntegerBoard var2 = new IntegerBoard(var1);
      SudokuSolver var3 = new SudokuSolver(var2);
      var3.solve();
      var3.printSolution();
   }

   public void testCase4() {
      System.out.println("*** Test Case 4 ***");
      Integer[][] var1 = new Integer[][]{{0, 0, 0, 0, 0, 0, 0, 0, 2}, {0, 0, 0, 0, 0, 0, 6, 0, 0}, {0, 0, 0, 5, 0, 9, 0, 0, 0}, {6, 0, 0, 0, 4, 0, 0, 0, 0}, {0, 7, 0, 0, 0, 0, 0, 5, 0}, {0, 0, 0, 0, 0, 0, 0, 3, 0}, {9, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 7, 0, 0, 0, 0}, {0, 0, 8, 0, 0, 0, 0, 0, 0}};
      IntegerBoard var2 = new IntegerBoard(var1);
      SudokuSolver var3 = new SudokuSolver(var2);
      var3.solve();
      var3.printSolution();
   }

   public void testCase5() {
      System.out.println("*** Test Case 5 ***:");
      Integer[][] var1 = new Integer[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {9, 0, 0, 0}, {6, 0, 0, 0}, {0, 0, 0, 0}};
      IntegerBoard var2 = new IntegerBoard(var1);
      SudokuSolver var3 = new SudokuSolver(var2);
      var3.solve();
      var3.printSolution();
   }
   
   public void testCase6() {
	      System.out.println("*** Test Case 6 ***:");
	      Integer[][] var1 = new Integer[][]{{0, 3, 4, 0}, 
    	  									{4, 0, 0, 2}, 
    	  									{1, 0, 0, 3}, 
    	  									{0, 2, 1, 0}};
	      IntegerBoard var2 = new IntegerBoard(var1);
	      SudokuSolver var3 = new SudokuSolver(var2);
	      var3.solve();
	      var3.printSolution();
   }
   
   public void testCase7() {
	      System.out.println("*** Test Case 7 ***");
	      Integer[][] var1 = new Integer[][]{{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    	  									{0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    	  									{0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    	  									{0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    	  									{0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    	  									{0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    	  									{0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    	  									{0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0}, 
    	  									{0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0}, 
    	  									{0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0}, 
    	  									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0}, 
    	  									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, 0, 0, 0, 0}, 
    	  									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0, 0}, 
    	  									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0}, 
    	  									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0}, 
    	  									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16}};							
	      IntegerBoard var2 = new IntegerBoard(var1);
	      SudokuSolver var3 = new SudokuSolver(var2);
	      var3.solve();
	      var3.printSolution();
   }
   
   public static void main(String[] var0) {
      SudokuApp var1 = new SudokuApp();
      var1.testCase1();
      var1.testCase2();
      var1.testCase3();
      var1.testCase4();
      var1.testCase5();
      // extra cases 
      var1.testCase6();
      var1.testCase7();
   }
}
