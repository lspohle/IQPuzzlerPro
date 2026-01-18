package org.puzzler.level;

/**
 * Simple factory to load level implementations by number.
 */
public class LevelLoader {
    public LevelLoader() {}

    /**
     * Load a {@link Level} implementation for the specified level number.
     *
     * @param levelNumber numeric level identifier
     * @return Level instance matching levelNumber
     * @throws IllegalArgumentException if the level number is unsupported
     */
    public static Level load(int levelNumber) {
        return switch (levelNumber) {
            case 7 -> new Level7();
            case 8 -> new Level8();
            default -> throw new IllegalArgumentException("Unsupported level: " + levelNumber);
        };
    }
}
