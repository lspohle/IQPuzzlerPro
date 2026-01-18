package org.puzzler.level;

import org.puzzler.model.Board;
import org.puzzler.model.Color;
import org.puzzler.model.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Level 8 implementation.
 *
 * This level places a predefined set of pieces on the board and leaves the rest
 * for the solver. Level-specific coordinates are provided by the overridden
 * {@link #initBlock(Piece)} method. Remaining pieces are initialized with the
 * default shapes (via {@code Level.super.initBlock(piece)}).
 */
public class Level8 implements Level{

    @Override
    public String name() {
        return "Level 8";
    }

    /**
     * Place level 8's fixed pieces on the given board.
     *
     * @param board the board to place pieces on
     * @return list of pieces that were placed (the fixed pieces)
     */
    @Override
    public List<Piece> initBoard(Board board) {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(new Piece(Color.LIGTH_RED));
        pieces.add(new Piece(Color.PINK));
        pieces.add(new Piece(Color.DARK_MINT));
        pieces.add(new Piece(Color.PURPLE));
        pieces.add(new Piece(Color.DARK_BLUE));
        pieces.add(new Piece(Color.GREEN));
        pieces.add(new Piece(Color.DARK_RED));
        pieces.add(new Piece(Color.LIGHT_MINT));
        pieces.add(new Piece(Color.BLUE));
        pieces.forEach(this::initBlock);
        pieces.forEach(board::placeBlock);

        System.out.println("The following blocks are set:");
        pieces.forEach(piece -> System.out.println(piece.getColor()));
        System.out.println();

        return pieces;
    }

    /**
     * Compute the pieces that are not yet placed on the board and initialize them
     * using the default shapes from the Level interface.
     *
     * @param pieces list of pieces already placed on the board
     * @return list of pieces that remain to be placed by the solver
     */
    @Override
    public List<Piece> remainingPieces(List<Piece> pieces) {
        List<Piece> remainingPieces = new ArrayList<>();

        System.out.println("The following blocks are unset:");

        boolean isRemaining;
        for (Color color : Color.values()) {
            isRemaining = true;
            for (Piece piece: pieces) {
                if (piece.getColor() == color) {
                    isRemaining = false;
                    break;
                }
            }
            if (isRemaining) {
                remainingPieces.add(new Piece(color));
                System.out.println(color);
            }
        }
        System.out.println();
        remainingPieces.forEach(Level.super::initBlock);
        return remainingPieces;
    }

    /**
     * Level 8 specific block coordinates for pieces that are pre-placed on the board.
     * Coordinates are relative {x, y} pairs passed to {@link Piece#addPoints(int[][])}.
     *
     * @param piece piece to initialize for level 8
     */
    @Override
    public void initBlock(Piece piece) {
        switch (piece.getColor()) {
            case LIGTH_RED:
                piece.addPoints(new int[][] {
                        {3, 7},
                        {4, 7},
                        {4, 8},
                        {4, 9},
                        {4, 10}});
                break;
            case PINK:
                piece.addPoints(new int[][] {
                        {0, 3},
                        {1, 3},
                        {1, 4},
                        {2, 4},
                        {3, 4}});
                break;
            case DARK_MINT:
                piece.addPoints(new int[][] {
                        {1, 10},
                        {2, 10},
                        {2, 9},
                        {3, 10}});
                break;
            case PURPLE:
                piece.addPoints(new int[][] {
                        {1, 7},
                        {2, 7},
                        {2, 8},
                        {3, 8},
                        {3, 9}});
                break;
            case DARK_BLUE:
                piece.addPoints(new int[][] {
                        {0, 4},
                        {0, 5},
                        {1, 5},
                        {2, 5}});
                break;
            case GREEN:
                piece.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {0, 1},
                        {0, 2},
                        {1, 2}});
                break;
            case DARK_RED:
                piece.addPoints(new int[][] {
                        {1, 8},
                        {1, 9},
                        {0, 9},
                        {0, 10}});
                break;
            case LIGHT_MINT:
                piece.addPoints(new int[][] {
                        {3, 5},
                        {3, 6},
                        {4, 4},
                        {4, 5},
                        {4, 6}});
                break;
            case BLUE:
                piece.addPoints(new int[][] {
                        {0, 6},
                        {1, 6},
                        {2, 6},
                        {0, 7},
                        {0, 8}});
                break;
            default:
                System.out.println("Error");
        }
    }
}
