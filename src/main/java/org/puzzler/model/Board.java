package org.puzzler.model;

import java.awt.Point;

/**
 * Represents the puzzle board as a 2D grid of placed pieces.
 *
 * Coordinates are integer grid coordinates where x is the row (0..row-1) and
 * y is the column (0..column-1). Each grid cell holds either a reference to a
 * {@link Piece} occupying that cell or null if the cell is empty.
 */
public class Board {
    public Piece[][] grid;
    private final int row = 5; // x
    private final int column = 11; // y

    /**
     * Get number of rows (x dimension).
     *
     * @return row count
     */
    public int getRow() {
        return row;
    }

    /**
     * Get number of columns (y dimension).
     *
     * @return column count
     */
    public int getColumn() {
        return column;
    }

    /**
     * Create and initialize an empty board.
     */
    public Board() {
        initBoard();
    }

    private void initBoard() {
        grid = new Piece[row][column];

        for (int x = 0; x < this.row; x++) {
            for (int y = 0; y < this.column; y++) {
                this.grid[x][y] = null;
            }
        }
    }

    /**
     * Check whether a piece can be placed at the given board coordinates
     * without going out of bounds or overlapping already placed pieces.
     *
     * The piece's relative coordinates (from {@link Piece#getBlock()}) are added to
     * the provided x/y position to compute absolute board cells.
     *
     * @param piece piece variation to test (its block list is relative coords)
     * @param x board x coordinate (row) where the piece origin would be placed
     * @param y board y coordinate (column) where the piece origin would be placed
     * @return true if placement fits and does not overlap, false otherwise
     */
    public boolean checkPlacementOfPiece(Piece piece, int x, int y) {
        for (Point p : piece.getBlock()) {
            int boardX = x + p.x;
            int boardY = y + p.y;

            // Check bounds of board
            if (boardX < 0 || boardX >= row || boardY < 0 || boardY >= column) {
                return false;
            }

            // Check if cell is empty
            if (grid[boardX][boardY] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Place a piece's cells on the board at the specified origin coordinates.
     * Used by the solver for remaining pieces (the piece variation should fit).
     *
     * @param piece piece to place
     * @param x origin x (row)
     * @param y origin y (column)
     */
    public void placeRemainingBlock(Piece piece, int x, int y) {
        piece.getBlock().forEach( point -> this.grid[x + point.x][y + point.y] = piece);
    }

    /**
     * Remove a previously placed piece from the board at the given origin coordinates.
     * The same coordinates used to place the piece should be used to remove it.
     *
     * @param piece piece to remove
     * @param x origin x used for placement
     * @param y origin y used for placement
     */
    public void removeBlock(Piece piece, int x, int y) {
        piece.getBlock().forEach( point -> this.grid[x + point.x][y + point.y] = null);
    }

    /**
     * Place a piece using its absolute coordinates. This is intended for pieces
     * that are already defined in board coordinates (e.g. pre-placed level pieces).
     *
     * @param piece piece whose points are absolute board coordinates
     */
    public void placeBlock(Piece piece) {
        piece.getBlock().forEach( point -> this.grid[point.x][point.y] = piece);
    }

    /**
     * Print the board to stdout. Null cells are printed as "null".
     */
    public void printBoard() {
        for (Piece[] row : grid) {
            for (Piece cell : row) {
                System.out.print(cell != null ? cell.getColor() + " " : null + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
