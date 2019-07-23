package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class SnowyTreeTileSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.InanimateBlockGraphics,
            Component.DecorativeBlockUpdate,
    };

    public SnowyTreeTileSpec() {
        super("Inert Tile", R.drawable.snowy_tree, 0,
                new PointF(3, 6), components, 1);
    }
}
