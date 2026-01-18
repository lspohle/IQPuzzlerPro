package org.puzzler.solver;

import org.puzzler.model.Board;
import org.puzzler.model.Piece;
import java.util.List;

/**
 * Simple backtracking solver implementation.
 *
 * The solver takes an ordered list of pieces to place. For the first piece in
 * the list it iterates over all normalized variations (rotations/reflections),
 * attempts to place it in every board cell and recurses to place the remainder.
 */
public class Backtracking implements Solver{
    @Override
    public boolean solve(Board board, List<Piece> pieces) {
        if (pieces.isEmpty()) {
            System.out.println("Finished!");
            System.out.println();
            return true;
        }

        Piece original = pieces.get(0);

        for (Piece piece : original.getAllVariations()) {
            for (int x = 0; x < board.getRow(); x++) {
                for (int y = 0; y < board.getColumn(); y++) {
                    if (board.checkPlacementOfPiece(piece, x, y)) {
                        board.placeRemainingBlock(piece, x, y);

                        // Recursive
                        if (solve(board, pieces.subList(1, pieces.size()))) {
                            return true;
                        }

                        board.removeBlock(piece, x, y);
                    }
                }
            }
        }

        return false;
    }
}
