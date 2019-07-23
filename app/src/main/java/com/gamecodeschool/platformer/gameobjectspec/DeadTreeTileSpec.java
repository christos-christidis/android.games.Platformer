package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class DeadTreeTileSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.InanimateBlockGraphics,
            Component.DecorativeBlockUpdate,
    };

    public DeadTreeTileSpec() {
        super("Inert Tile", R.drawable.dead_tree, 0,
                new PointF(2, 4), components, 1);
    }
}
