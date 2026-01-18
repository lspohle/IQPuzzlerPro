package org.puzzler.level.starter;

import org.puzzler.level.Level;
import org.puzzler.model.Board;
import org.puzzler.model.Color;
import org.puzzler.model.Piece;
import java.util.ArrayList;
import java.util.List;

/**
 * Level 7 implementation.
 *
 * This level places a predefined set of pieces on the board and leaves the rest
 * for the solver. Level-specific coordinates are provided by the overridden
 * {@link #initBlock(Piece)} method. Remaining pieces are initialized with the
 * default shapes (via {@code Level.super.initBlock(piece)}).
 */
public class Level7 implements Level {

    @Override
    public String name() {
        return "Level 7";
    }

    /**
     * Place level 7's fixed pieces on the given board.
     *
     * @param board the board to place pieces on
     * @return list of pieces that were placed (the fixed pieces)
     */
    @Override
    public List<Piece> initBoard(Board board) {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(new Piece(Color.LIGTH_RED, new int[][] {
                {3, 6},
                {4, 6},
                {4, 7},
                {4, 8},
                {4, 9}}));
        pieces.add(new Piece(Color.PINK, new int[][] {
                {0, 6},
                {0, 7},
                {1, 7},
                {1, 8},
                {1, 9}}));
        pieces.add(new Piece(Color.LIGHT_BLUE, new int[][] {
                {2, 7},
                {3, 7},
                {3, 8}}));
        pieces.add(new Piece(Color.DARK_MINT, new int[][] {
                {0, 2},
                {0, 3},
                {0, 4},
                {1, 3}}));
        pieces.add(new Piece(Color.PURPLE, new int[][] {
                {2, 8},
                {2, 9},
                {3, 9},
                {3, 10},
                {4, 10}}));
        pieces.add(new Piece(Color.ORANGE, new int[][] {
                {0, 5},
                {1, 4},
                {1, 5},
                {1, 6},
                {2, 6}}));
        pieces.add(new Piece(Color.DARK_BLUE, new int[][] {
                {2, 4},
                {2, 5},
                {3, 5},
                {4, 5}}));
        pieces.add(new Piece(Color.LIGHT_MINT, new int[][] {
                {0, 0},
                {1, 0},
                {0, 1},
                {1, 1},
                {1, 2}}));
        pieces.add(new Piece(Color.BLUE, new int[][] {
                {0, 8},
                {0, 9},
                {0, 10},
                {1, 10},
                {2, 10}}));

        pieces.forEach(board::placeBlock);

        System.out.println("The following blocks are set:");
        pieces.forEach(piece -> System.out.println(piece.getColor()));
        System.out.println();

        return pieces;
    }
}
