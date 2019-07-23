package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class BackgroundUndergroundSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.BackgroundGraphics,
            Component.BackgroundUpdate,
    };

    public BackgroundUndergroundSpec() {
        super("Background", R.drawable.underground, 3,
                new PointF(100, 70), components, 1);
    }
}
