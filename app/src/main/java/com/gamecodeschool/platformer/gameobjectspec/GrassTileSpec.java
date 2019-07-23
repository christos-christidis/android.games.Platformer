package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class GrassTileSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.InanimateBlockGraphics,
            Component.InanimateBlockUpdate,
    };

    public GrassTileSpec() {
        super("Inert Tile", R.drawable.turf, 0,
                new PointF(1, 1), components, 1);
    }
}
