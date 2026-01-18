package org.puzzler.level.master;

import org.puzzler.level.Level;
import org.puzzler.model.Board;
import org.puzzler.model.Color;
import org.puzzler.model.Piece;

import java.util.ArrayList;
import java.util.List;

/** Level 36 implementation.
 *
 * This level places a predefined set of pieces on the board and leaves the rest
 * for the solver. Level-specific coordinates are provided by the overridden
 * {@link #initBlock(Piece)} method. Remaining pieces are initialized with the
 * default shapes (via {@code Level.super.initBlock(piece)}).
 */
public class Level36 implements Level {

    @Override
    public String name() {
        return "Level 36";
    }

    /**
     * Place level 36's fixed pieces on the given board.
     *
     * @param board the board to place pieces on
     * @return list of pieces that were placed (the fixed pieces)
     */
    @Override
    public List<Piece> initBoard(Board board) {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(new Piece(Color.PINK, new int[][] {
                {0, 0},
                {0, 1},
                {0, 2},
                {1, 2},
                {1, 3}}));
        pieces.add(new Piece(Color.LIGHT_MINT, new int[][] {
                {2, 2},
                {2, 3},
                {2, 4},
                {3, 3},
                {3, 4}}));
        pieces.add(new Piece(Color.DARK_MINT, new int[][] {
                {0, 3},
                {0, 4},
                {0, 5},
                {1, 4}}));

        pieces.forEach(board::placeBlock);

        System.out.println("The following blocks are set:");
        pieces.forEach(piece -> System.out.println(piece.getColor()));
        System.out.println();

        return pieces;
    }
}

