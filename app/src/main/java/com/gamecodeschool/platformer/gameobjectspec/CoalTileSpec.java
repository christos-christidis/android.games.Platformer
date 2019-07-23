package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class CoalTileSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.InanimateBlockGraphics,
            Component.InanimateBlockUpdate,
    };

    public CoalTileSpec() {
        super("Inert Tile", R.drawable.coal, 0,
                new PointF(1, 1), components, 1);
    }
}
