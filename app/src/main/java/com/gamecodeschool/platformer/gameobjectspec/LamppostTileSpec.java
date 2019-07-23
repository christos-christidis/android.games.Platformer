package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class LamppostTileSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.InanimateBlockGraphics,
            Component.DecorativeBlockUpdate,
    };

    public LamppostTileSpec() {
        super("Inert Tile", R.drawable.lamppost, 0,
                new PointF(1, 4), components, 1);
    }
}
