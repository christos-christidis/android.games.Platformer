package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class CartTileSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.InanimateBlockGraphics,
            Component.InanimateBlockUpdate,
    };

    public CartTileSpec() {
        super("Inert Tile", R.drawable.cart, 0,
                new PointF(2, 1), components, 1);
    }
}
