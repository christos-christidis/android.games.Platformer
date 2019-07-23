package com.gamecodeschool.platformer.level;

import java.util.ArrayList;
import java.util.List;

public abstract class Level {

    public enum Choice {
        UNDERGROUND,
        MOUNTAINS,
        CITY,
    }

    private final List<String> mTiles;

    Level() {
        // NOTE: this initial capacity is enough for all 3 levels
        mTiles = new ArrayList<>(60);
    }

    public List<String> getTiles() {
        return mTiles;
    }
}
