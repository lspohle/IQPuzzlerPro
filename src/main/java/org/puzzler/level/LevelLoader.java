package org.puzzler.level;

import org.puzzler.level.expert.Level26;
import org.puzzler.level.master.Level33;
import org.puzzler.level.master.Level36;
import org.puzzler.level.starter.Level7;
import org.puzzler.level.starter.Level8;
import org.puzzler.level.wizard.Level40;

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
            //case 7 -> new Level7();
            case 8 -> new Level8();
            case 26 -> new Level26();
            case 33 -> new Level33();
            case 36 -> new Level36();
            case 40 -> new Level40();
            default -> throw new IllegalArgumentException("Unsupported level: " + levelNumber);
        };
    }
}
