/**
 * interface for game solvers (player)
 *
 * @author      Francois Major (for TP1-IFT2015-A24)
 *              
 * @version     1.0
 * @since       1.0 (25 October 2024)
 */

public interface GameSolver {

    /**
     * Solves the given game state.
     *
     * @param game the current game state
     */
    boolean solve();

    /**
     * Provides a description of the solution.
     *
     * @param solution the solution obtained from the solve method
     */
    void printSolution();
}
