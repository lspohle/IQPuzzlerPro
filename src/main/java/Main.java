import java.util.List;
import org.puzzler.level.Level;
import org.puzzler.level.LevelLoader;
import org.puzzler.model.Board;
import org.puzzler.model.Piece;
import org.puzzler.model.Timer;
import org.puzzler.solver.Backtracking;
import org.puzzler.solver.Solver;

/**
 * Main entry point for running the puzzle solver on a selected level.
 * This class sets up a timer, loads the requested level, initializes the board
 * with pre-placed pieces, runs the solver on the remaining pieces and prints
 * the final board and timing information.
 */
public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.start();

        Board board = new Board();
        Level level = LevelLoader.load(7);
        Solver solver  = new Backtracking();

        List<Piece> pieces = level.initBoard(board);
        board.printBoard();
        solver.solve(board, level.remainingPieces(pieces));
        board.printBoard();

        timer.end();
        timer.printAllDurations();
    }
}