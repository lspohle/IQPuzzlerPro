package org.puzzler.solver;

import org.puzzler.model.Board;
import org.puzzler.model.Piece;
import java.util.List;

/**
 * Solver interface for puzzle solving strategies.
 *
 * Implementations should attempt to place all provided pieces onto the board
 * and return true if a complete placement was found, false otherwise.
 */
public interface Solver {
    /**
     * Attempt to place all pieces on the given board.
     *
     * @param board board to place pieces on
     * @param pieces list of pieces to place (order may affect the algorithm)
     * @return true if a full placement was found, false otherwise
     */
    boolean solve(Board board, List<Piece> pieces);
}
