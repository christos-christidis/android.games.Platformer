package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class FireTileSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.AnimatedGraphics,
            Component.InanimateBlockUpdate,
    };

    public FireTileSpec() {
        super("Death", R.drawable.fire, 0,
                new PointF(1, 1), components, 3);
    }
}
