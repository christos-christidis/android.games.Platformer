package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class StonePileTileSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.InanimateBlockGraphics,
            Component.InanimateBlockUpdate,
    };

    public StonePileTileSpec() {
        super("Inert Tile", R.drawable.stone_pile, 0,
                new PointF(2, 1), components, 1);
    }
}
