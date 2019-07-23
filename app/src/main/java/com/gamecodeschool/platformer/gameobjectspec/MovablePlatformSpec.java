package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class MovablePlatformSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.InanimateBlockGraphics,
            Component.MovableBlockUpdate,
    };

    public MovablePlatformSpec() {
        super("Movable Platform", R.drawable.platform, 3,
                new PointF(2, 1), components, 1);
    }
}
