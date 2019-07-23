package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class BrickTileSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.InanimateBlockGraphics,
            Component.InanimateBlockUpdate,
    };

    public BrickTileSpec() {
        super("Inert Tile", R.drawable.brick, 0,
                new PointF(1, 1), components, 1);
    }
}
