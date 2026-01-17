package org.example;

import java.awt.Point;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Board {
    public Piece[][] grid;
    private final int row = 5; // x
    private final int column = 11; // y

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

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

    public void placeRemainingBlock(Piece piece, int x, int y) {
        piece.getBlock().forEach( point -> this.grid[x + point.x][y + point.y] = piece);
    }

    public void removeBlock(Piece piece, int x, int y) {
        piece.getBlock().forEach( point -> this.grid[x + point.x][y + point.y] = null);
    }


    public void placeBlock(Piece piece) {
        piece.getBlock().forEach( point -> this.grid[point.x][point.y] = piece);
    }

    public void printBoard() {
        for (Piece[] row : grid) {
            for (Piece cell : row) {
                System.out.print(cell != null ? cell.getColor() + " " : null + " ");
            }
            System.out.println();
        }
    }
}
