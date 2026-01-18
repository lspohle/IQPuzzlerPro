package org.puzzler.level.expert;

import org.puzzler.level.Level;
import org.puzzler.model.Board;
import org.puzzler.model.Color;
import org.puzzler.model.Piece;

import java.util.ArrayList;
import java.util.List;

/** Level 26 implementation.
 *
 * This level places a predefined set of pieces on the board and leaves the rest
 * for the solver. Level-specific coordinates are provided by the overridden
 * {@link #initBlock(Piece)} method. Remaining pieces are initialized with the
 * default shapes (via {@code Level.super.initBlock(piece)}).
 */
public class Level26 implements Level {

    @Override
    public String name() {
        return "Level 26";
    }

    /**
     * Place level 26's fixed pieces on the given board.
     *
     * @param board the board to place pieces on
     * @return list of pieces that were placed (the fixed pieces)
     */
    @Override
    public List<Piece> initBoard(Board board) {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(new Piece(Color.YELLOW, new int[][] {
                {0, 0},
                {0, 1},
                {0, 2},
                {0, 3},
                {1, 2}}));
        pieces.add(new Piece(Color.DARK_RED, new int[][] {
                {1, 0},
                {1, 1},
                {2, 1},
                {2, 2}}));
        pieces.add(new Piece(Color.PURPLE, new int[][] {
                {0, 4},
                {0, 5},
                {1, 3},
                {1, 4},
                {2, 3}}));
        pieces.add(new Piece(Color.LIGHT_MINT, new int[][] {
                {0, 6},
                {1, 5},
                {1, 6},
                {2, 5},
                {2, 6}}));
        pieces.add(new Piece(Color.LIGHT_BLUE, new int[][] {
                {0, 7},
                {0, 8},
                {1, 8}}));

        pieces.forEach(board::placeBlock);

        System.out.println("The following blocks are set:");
        pieces.forEach(piece -> System.out.println(piece.getColor()));
        System.out.println();

        return pieces;
    }
}

