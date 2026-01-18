package org.puzzler.level;

import org.puzzler.model.Board;
import org.puzzler.model.Color;
import org.puzzler.model.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a puzzle level.
 *
 * Implementations are responsible for placing initial pieces on a provided board
 * and returning remaining pieces (not yet placed) so a solver can attempt to place them.
 */
public interface Level {

    /**
     * Initialize the board with the pieces that should be pre-placed for this level.
     *
     * Implementations should create Piece instances for pieces that are fixed on the
     * board, initialize their blocks (using {@link #initBlock(Piece)} or a custom initializer),
     * place them on the provided {@link Board} and return the list of those placed pieces.
     *
     * @param board the board to place the initial pieces on
     * @return the list of pieces that have been placed on the board
     */
    List<Piece> initBoard(Board board);

    /**
     * Create and return a list of pieces that are not yet placed on the board and
     * therefore need to be placed by the solver.
     *
     * Implementations typically compute which colors are not present among the already
     * placed pieces, instantiate new {@link Piece} objects for them, initialize their
     * block shapes, and return that list.
     *
     * @param placedPieces the list of already placed pieces
     * @return list of pieces that remain to be placed
     */
    default List<Piece> remainingPieces(List<Piece> placedPieces) {
        List<Piece> remainingPieces = new ArrayList<>();

        System.out.println("The following blocks are unset:");

        for (Color color : Color.values()) {
            boolean isRemaining = true;

            for (Piece piece : placedPieces) {
                if (piece.getColor() == color) {
                    isRemaining = false;
                    break;
                }
            }

            if (isRemaining) {
                Piece piece = new Piece(color);
                initBlock(piece);
                remainingPieces.add(piece);
                System.out.println(color);
            }
        }
        return remainingPieces;
    }

    /**
     * Return a human-readable name for this level.
     *
     * @return level name
     */
    String name();

    /**
     * Default initializer for a piece's block shape.
     *
     * Implementations (concrete levels) may override to provide level-specific
     * starting coordinates for pieces. The default implementation provides the
     * canonical shapes/coordinates for each color.
     *
     * The coordinates passed to {@link Piece#addPoints(int[][])} are arrays of
     * {x, y} integer pairs describing the relative coordinates of the piece's cells.
     *
     * @param piece piece to initialize with default coordinates
     */
    default void initBlock(Piece piece) {
        switch (piece.getColor()) {
            case YELLOW:
                // Initial Coordinates
                piece.addPoints(new int[][]{
                        {0, 0},
                        {1, 0},
                        {2, 0},
                        {3, 0},
                        {1, 1}});
                break;
            case LIGTH_RED:
                // Initial Coordinates
                piece.addPoints(new int[][]{
                        {0, 0},
                        {1, 0},
                        {2, 0},
                        {3, 0},
                        {0, 1}});
                break;
            case PINK:
                // Initial Coordinates
                piece.addPoints(new int[][]{
                        {0, 1},
                        {1, 0},
                        {2, 0},
                        {3, 0},
                        {1, 1}});
                break;
            case LIGHT_BLUE:
                // Initial Coordinates
                piece.addPoints(new int[][]{
                        {0, 0},
                        {1, 0},
                        {0, 1}});
                break;
            case DARK_MINT:
                // Initial Coordinates
                piece.addPoints(new int[][]{
                        {0, 0},
                        {0, 1},
                        {0, 2},
                        {1, 1}});
                break;
            case PURPLE:
                // Initial Coordinates
                piece.addPoints(new int[][]{
                        {0, 0},
                        {0, 1},
                        {1, 1},
                        {1, 2},
                        {2, 2}});
                break;
            case ORANGE:
                // Initial Coordinates
                piece.addPoints(new int[][]{
                        {0, 0},
                        {0, 1},
                        {1, 1},
                        {1, 2},
                        {2, 1}});
                break;
            case DARK_BLUE:
                // Initial Coordinates
                piece.addPoints(new int[][]{
                        {0, 0},
                        {0, 1},
                        {1, 0},
                        {2, 0}});
                break;
            case GREEN:
                // Initial Coordinates
                piece.addPoints(new int[][]{
                        {0, 0},
                        {1, 0},
                        {1, 1},
                        {1, 2},
                        {0, 2}});
                break;
            case DARK_RED:
                // Initial Coordinates
                piece.addPoints(new int[][]{
                        {0, 0},
                        {0, 1},
                        {1, 1},
                        {1, 2}});
                break;
            case LIGHT_MINT:
                // Initial Coordinates
                piece.addPoints(new int[][]{
                        {0, 0},
                        {0, 1},
                        {1, 0},
                        {1, 1},
                        {2, 0}});
                break;
            case BLUE:
                // Initial Coordinates
                piece.addPoints(new int[][]{
                        {0, 0},
                        {0, 1},
                        {0, 2},
                        {1, 0},
                        {2, 0}});
                break;
            default:
                System.out.println("Error");
                //default -> throw new IllegalArgumentException("Unsupported color: " + piece.getColor());;
        }
    }
}
