package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class CollectibleObjectSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.InanimateBlockGraphics,
            Component.InanimateBlockUpdate,
    };

    public CollectibleObjectSpec() {
        super("Collectible", R.drawable.coin, 0,
                new PointF(1, 1), components, 1);
    }
}
