package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class StalagmiteTileSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.InanimateBlockGraphics,
            Component.DecorativeBlockUpdate,
    };

    public StalagmiteTileSpec() {
        super("Inert Tile", R.drawable.stalagmite, 0,
                new PointF(2, 4), components, 1);
    }
}
