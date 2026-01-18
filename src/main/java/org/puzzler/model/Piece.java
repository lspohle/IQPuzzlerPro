package org.puzzler.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a puzzle piece composed of multiple grid cells.
 *
 * <p>A Piece has a {@link Color} identifier and a list of relative coordinates
 * ({@link Point}) that describe its shape. Coordinates are relative to the
 * piece origin and are used by {@link Board} methods to place and check the piece.</p>
 *
 * <p>This class also provides utilities to:
 * <ul>
 *   <li>append coordinates via {@link #addPoints(int[][])}</li>
 *   <li>generate all normalized rotations/reflections via {@link #getAllVariations()}</li>
 *   <li>normalize shapes to the origin so equivalent shapes compare equal</li>
 * </ul>
 * </p>
 */
public class Piece {
    Color color;
    List<Point> block = new ArrayList<>();

    /**
     * Construct a piece with a color and a predefined block coordinate list.
     *
     * @param color piece color (type/identifier)
     * @param block list of relative points describing the piece shape
     */
    public Piece(Color color, List<Point> block) {
        this.color = color;
        this.block = block;
    }

    public Piece(Color color, int[][] coordinates) {
        this.color = color;
        addPoints(coordinates);
    }

    /**
     * Construct a piece with only a color. Coordinate points must be added later
     * with {@link #addPoints(int[][])} or via a level initializer.
     *
     * @param color piece color
     */
    public Piece(Color color) {
        this.color = color;
    }

    /**
     * Add coordinates to this piece.
     *
     * <p>The provided coordinates are arrays of {x, y} integer pairs. Points are appended
     * to the piece's internal block list.</p>
     *
     * @param coordinates 2D int array of coordinates where each element is {x, y}
     */
    public void  addPoints(int[][] coordinates) {
        for (int[] point : coordinates) {
            this.block.add(new Point(point[0], point[1]));
        }
    }

    /**
     * Compute all unique rotational and reflectional variations of this piece.
     *
     * <p>The returned {@link Set} contains {@link Piece} instances whose coordinate
     * lists are normalized (minimum x/y moved to 0). Normalization ensures that
     * variations that are identical up to translation are considered equal.</p>
     *
     * @return set of unique piece variations (rotations and reflections)
     */
    public Set<Piece> getAllVariations() {
        Set<Piece> variations = new HashSet<>();

        List<Point> original = block;

        for (int i = 0; i < 4; i++) {
            List<Point> rotated = normalize(rotateCopy(original, i));
            variations.add(new Piece(color, rotated));
            variations.add(new Piece(color, normalize(reflectHorizontal(rotated))));
            variations.add(new Piece(color, normalize(reflectVertical(rotated))));
        }

        return variations;
    }

    /**
     * Normalize the list of points so that the smallest x and y become 0.
     *
     * <p>This prevents identical shapes with different offsets from appearing distinct.</p>
     *
     * @param block list of points to normalize
     * @return new list of normalized points
     */
    private List<Point> normalize(List<Point> block) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Point p : block) {
            minX = Math.min(minX, p.x);
            minY = Math.min(minY, p.y);
        }

        List<Point> normalized = new ArrayList<>();
        for (Point p : block) {
            normalized.add(new Point(p.x - minX, p.y - minY));
        }
        return normalized;
    }

    /**
     * Reflect the given block horizontally (mirror along the vertical axis).
     *
     * @param block list of points
     * @return reflected list of points
     */
    private List<Point> reflectHorizontal(List<Point> block) {
        List<Point> reflected = new ArrayList<>();
        for (Point p : block) {
            reflected.add(reflectHorizontalPoint(p));
        }
        return reflected;
    }

    private Point reflectVerticalPoint(Point point) {
        return new Point(point.x, -point.y);
    }

    /**
     * Reflect the given block vertically (mirror along the horizontal axis).
     *
     * @param block list of points
     * @return reflected list of points
     */
    private List<Point> reflectVertical(List<Point> block) {
        List<Point> reflected = new ArrayList<>();
        for (Point p : block) {
            reflected.add(reflectVerticalPoint(p));
        }
        return reflected;
    }

    private Point reflectHorizontalPoint(Point point) {
        return new Point(-point.x, point.y);
    }

    /**
     * Rotate the provided block 90 degrees clockwise in-place.
     *
     * @param block list of points to rotate
     */
    private void rotate(List<Point> block) {
        block.forEach(point -> point.setLocation(rotatePoint(point)));
    }

    /**
     * Return a copy of the provided shape rotated {@code times} times (90° clockwise each).
     *
     * @param shape list of points to copy & rotate
     * @param times number of 90° clockwise rotations to apply (0..3)
     * @return new list with rotated points
     */
    private List<Point> rotateCopy(List<Point> shape, int times) {
        List<Point> copy = new ArrayList<>();
        for (Point p : shape) copy.add(new Point(p));
        for (int i = 0; i < times; i++) {
            rotate(copy);
        }
        return copy;
    }

    private Point rotatePoint(Point point) {
        return new Point(point.y, -point.x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece other)) return false;
        return color == other.color && block.equals(other.block);
    }

    @Override
    public int hashCode() {
        return 31 * color.hashCode() + block.hashCode();
    }

    /**
     * Get the piece color.
     *
     * @return color identifier for the piece
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set the piece block.
     *
     * @return block for the piece
     */
    public List<Point> getBlock() {
        return block;
    }
}
