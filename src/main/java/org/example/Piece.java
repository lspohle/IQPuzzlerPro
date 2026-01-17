package org.example;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Piece {
    Color color;
    List<Point> block = new ArrayList<>();

    public Piece(Color color, List<Point> block) {
        this.color = color;
        this.block = block;
    }

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


    private List<Point> rotate(List<Point> block) {
        block.forEach(point -> point.setLocation(rotatePoint(point)));
        return block;
    }

    private List<Point> rotateCopy(List<Point> shape, int times) {
        List<Point> copy = new ArrayList<>();
        for (Point p : shape) copy.add(new Point(p));
        for (int i = 0; i < times; i++) copy = rotate(copy);
        return copy;
    }

    private Point rotatePoint(Point point) {
        return new Point(point.y, -point.x);
    }

    public Piece(Color color) {
        this.color = color;
        initBlock(this.color);
    }

    private void  addPoints(int[][] coordinates) {
        for (int[] point : coordinates) {
            this.block.add(new Point(point[0], point[1]));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        Piece other = (Piece) o;
        return color == other.color && block.equals(other.block);
    }

    @Override
    public int hashCode() {
        return 31 * color.hashCode() + block.hashCode();
    }

    // Level 40
    private void initBlock(Color color) {
        switch (color) {
            case YELLOW:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {2, 0},
                        {3, 0},
                        {1, 1}});
/*                this.addPoints(new int[][] {
                        {2, 2},
                        {2, 3},
                        {2, 4},
                        {2, 5},
                        {3, 4}});*/
                break;
            case LIGTH_RED:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {2, 0},
                        {3, 0},
                        {0, 1}});
                break;
            case PINK:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 1},
                        {1, 0},
                        {2, 0},
                        {3, 0},
                        {1, 1}});
                break;
            case LIGHT_BLUE:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {0, 1}});
                break;
            case DARK_MINT:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {0, 2},
                        {1, 1}});
                break;
            case PURPLE:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 1},
                        {1, 2},
                        {2, 2}});
                break;
            case ORANGE:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 1},
                        {1, 2},
                        {2, 1}});
                break;
            case DARK_BLUE:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 0},
                        {2, 0}});
                break;
            case GREEN:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {1, 1},
                        {1, 2},
                        {0, 2}});
                break;
            case DARK_RED:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 1},
                        {1, 2}});
                break;
            case LIGHT_MINT:
                // Initial Coordinates
                /*this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 0},
                        {1, 1},
                        {2, 0}});*/
                this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {0, 2},
                        {1, 0},
                        {1, 1}});
                break;
            case BLUE:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {0, 2},
                        {1, 0},
                        {2, 0}});
                break;
            default:
                System.out.println("Error");
        }
    }

    /*// Level 33
    private void initBlock(Color color) {
        switch (color) {
            case YELLOW:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {2, 0},
                        {3, 0},
                        {1, 1}});
                break;
            case LIGTH_RED:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {2, 0},
                        {3, 0},
                        {0, 1}});
                break;
            case PINK:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 1},
                        {1, 0},
                        {2, 0},
                        {3, 0},
                        {1, 1}});
                break;
            case LIGHT_BLUE:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {0, 1}});
                break;
            case DARK_MINT:
                // Initial Coordinates
               this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {0, 2},
                        {1, 1}});
                break;
            case PURPLE:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 1},
                        {1, 2},
                        {2, 2}});
                break;
            case ORANGE:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 1},
                        {1, 2},
                        {2, 1}});
                break;
            case DARK_BLUE:
                // Initial Coordinates
                *//*this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 0},
                        {2, 0}});*//*
                this.addPoints(new int[][] {
                        {2, 5},
                        {2, 6},
                        {2, 7},
                        {3, 7}});
                break;
            case GREEN:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {1, 1},
                        {1, 2},
                        {0, 2}});
                break;
            case DARK_RED:
                // Initial Coordinates
                *//*this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 1},
                        {1, 2}});*//*
                this.addPoints(new int[][] {
                        {0, 3},
                        {1, 3},
                        {1, 4},
                        {2, 4}});
                break;
            case LIGHT_MINT:
                // Initial Coordinates
                *//*this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 0},
                        {1, 1},
                        {2, 0}});*//*
                this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {0, 2},
                        {1, 0},
                        {1, 1}});
                break;
            case BLUE:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {0, 2},
                        {1, 0},
                        {2, 0}});
                break;
            default:
                System.out.println("Error");
        }
    }*/

    // Level 8
    /*private void initBlock(Color color) {
        switch (color) {
            case YELLOW:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {2, 0},
                        {3, 0},
                        {1, 1}});
                break;
            case LIGTH_RED:
                // Initial Coordinates
                *//*this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {2, 0},
                        {3, 0},
                        {0, 1}});*//*
                this.addPoints(new int[][] {
                        {3, 7},
                        {4, 7},
                        {4, 8},
                        {4, 9},
                        {4, 10}});
                break;
            case PINK:
                // Initial Coordinates
                *//*this.addPoints(new int[][] {
                        {0, 1},
                        {1, 0},
                        {2, 0},
                        {3, 0},
                        {1, 1}});*//*
                this.addPoints(new int[][] {
                        {0, 3},
                        {1, 3},
                        {1, 4},
                        {2, 4},
                        {3, 4}});
                break;
            case LIGHT_BLUE:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {0, 1}});
                break;
            case DARK_MINT:
                // Initial Coordinates
               *//* this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {0, 2},
                        {1, 1}});
                break;*//*
                this.addPoints(new int[][] {
                        {1, 10},
                        {2, 10},
                        {2, 9},
                        {3, 10}});
                break;
            case PURPLE:
                // Initial Coordinates
                *//*this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 1},
                        {1, 2},
                        {2, 2}});*//*
                this.addPoints(new int[][] {
                        {1, 7},
                        {2, 7},
                        {2, 8},
                        {3, 8},
                        {3, 9}});
                break;
            case ORANGE:
                // Initial Coordinates
                this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 1},
                        {1, 2},
                        {2, 1}});
                break;
            case DARK_BLUE:
                // Initial Coordinates
                *//*this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 0},
                        {2, 0}});*//*
                this.addPoints(new int[][] {
                        {0, 4},
                        {0, 5},
                        {1, 5},
                        {2, 5}});
                break;
            case GREEN:
                // Initial Coordinates
                *//*this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {1, 1},
                        {1, 2},
                        {0, 2}});*//*
                this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {0, 1},
                        {0, 2},
                        {1, 2}});
                break;
            case DARK_RED:
                // Initial Coordinates
                *//*this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 1},
                        {1, 2}});*//*
                this.addPoints(new int[][] {
                        {1, 8},
                        {1, 9},
                        {0, 9},
                        {0, 10}});
                break;
            case LIGHT_MINT:
                // Initial Coordinates
                *//*this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 0},
                        {1, 1},
                        {2, 0}});*//*
                this.addPoints(new int[][] {
                        {3, 5},
                        {3, 6},
                        {4, 4},
                        {4, 5},
                        {4, 6}});
                break;
            case BLUE:
                // Initial Coordinates
                *//*this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {0, 2},
                        {1, 0},
                        {2, 0}});*//*
                this.addPoints(new int[][] {
                        {0, 6},
                        {1, 6},
                        {2, 6},
                        {0, 7},
                        {0, 8}});
                break;
            default:
                System.out.println("Error");
        }
    }*/

    // Level 7
    /*private void initBlock(Color color) {
        switch (color) {
            case YELLOW:
                // REMAINING
                this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {2, 0},
                        {3, 0},
                        {1, 1}});
                break;
            case LIGTH_RED:
                this.addPoints(new int[][] {
                        {3, 6},
                        {4, 6},
                        {4, 7},
                        {4, 8},
                        {4, 9}});
                break;
            case PINK:
                this.addPoints(new int[][] {
                        {0, 6},
                        {0, 7},
                        {1, 7},
                        {1, 8},
                        {1, 9}});
                break;
            case LIGHT_BLUE:
                this.addPoints(new int[][] {
                        {2, 7},
                        {3, 7},
                        {3, 8}});
                break;
            case DARK_MINT:
                this.addPoints(new int[][] {
                        {0, 2},
                        {0, 3},
                        {0, 4},
                        {1, 3}});
                break;
            case PURPLE:
                this.addPoints(new int[][] {
                        {2, 8},
                        {2, 9},
                        {3, 9},
                        {3, 10},
                        {4, 10}});
                break;
            case ORANGE:
                this.addPoints(new int[][] {
                        {0, 5},
                        {1, 4},
                        {1, 5},
                        {1, 6},
                        {2, 6}});
                break;
            case DARK_BLUE:
                this.addPoints(new int[][] {
                        {2, 4},
                        {2, 5},
                        {3, 5},
                        {4, 5}});
                break;
            case GREEN:
                // REMAINING
                this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {1, 1},
                        {1, 2},
                        {0, 2}});
                break;
            case DARK_RED:
                // REMAINING
                this.addPoints(new int[][] {
                        {0, 0},
                        {0, 1},
                        {1, 1},
                        {1, 2}});
                break;
            case LIGHT_MINT:
                this.addPoints(new int[][] {
                        {0, 0},
                        {1, 0},
                        {0, 1},
                        {1, 1},
                        {1, 2}});
                break;
            case BLUE:
                this.addPoints(new int[][] {
                        {0, 8},
                        {0, 9},
                        {0, 10},
                        {1, 10},
                        {2, 10}});
                break;
            default:
                System.out.println("Error");
        }
    }*/

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Point> getBlock() {
        return block;
    }

    public void setBlock(List<Point> block) {
        this.block = block;
    }
}
