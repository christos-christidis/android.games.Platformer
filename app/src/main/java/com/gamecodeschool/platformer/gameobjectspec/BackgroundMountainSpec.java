package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class BackgroundMountainSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.BackgroundGraphics,
            Component.BackgroundUpdate,
    };

    public BackgroundMountainSpec() {
        super("Background", R.drawable.mountains, 3,
                new PointF(100, 70), components, 1);
    }
}
