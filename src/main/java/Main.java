import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.example.Board;
import org.example.Color;
import org.example.Piece;


public class Main {
    List<Piece> pieces = new ArrayList<>();

    List<Piece> remainingPieces = new ArrayList<>();

    public static void main(String[] args) {
        long start = System.nanoTime();

        Board board = new Board();

        Main mainObj = new Main();
        mainObj.initPieces(board);
        mainObj.initRemainingPieces();
        mainObj.solve(board, mainObj.remainingPieces);
        board.printBoard();

        long end = System.nanoTime();

        long duration = end - start;
        System.out.println();
        System.out.println("Benötigte Dauer: " + (double) (duration) / 1_000_000.0 + " ms.");
        System.out.println("Benötigte Dauer: " + (double) duration / 1_000_000_000.0 + " s.");
        System.out.println("Benötigte Dauer: " + (double) duration / 60_000_000_000.0 + " min.");
    }

    public boolean solve(Board board, List<Piece> remainingPieces) {
        if (remainingPieces.isEmpty()) {
            System.out.println("Finished!");
            System.out.println();
            return true;
        }

        Piece original = remainingPieces.get(0);

        for (Piece piece : original.getAllVariations()) {
            for (int x = 0; x < board.getRow(); x++) {
                for (int y = 0; y < board.getColumn(); y++) {
                    if (board.checkPlacementOfPiece(piece, x, y)) {
                        board.placeRemainingBlock(piece, x, y);

                        // Recursive
                        if (solve(board, remainingPieces.subList(1, remainingPieces.size()))) {
                            return true;
                        }

                        board.removeBlock(piece, x, y);
                    }
                }
            }
        }

        return false;
    }

    private void initPieces(Board board) {
        pieces.add(new Piece(Color.LIGHT_MINT));
        pieces.forEach(board::placeBlock);

        System.out.println("The following blocks are set:");
        pieces.forEach(piece -> System.out.println(piece.getColor()));
        System.out.println();
    }

    private void initRemainingPieces() {
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
    }
}