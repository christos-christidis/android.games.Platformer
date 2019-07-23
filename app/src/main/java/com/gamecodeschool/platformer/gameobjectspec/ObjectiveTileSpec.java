package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class ObjectiveTileSpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.InanimateBlockGraphics,
            Component.InanimateBlockUpdate,};

    public ObjectiveTileSpec() {
        super("Objective Tile", R.drawable.objective, 0,
                new PointF(3, 3), components, 1);
    }
}
