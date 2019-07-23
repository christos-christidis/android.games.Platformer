package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class SnowTileSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.InanimateBlockGraphics,
            Component.InanimateBlockUpdate,
    };

    public SnowTileSpec() {
        super("Inert Tile", R.drawable.snow, 0,
                new PointF(1, 1), components, 1);
    }
}
