package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class InvisibleDeath10x10Spec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.InanimateBlockGraphics,
            Component.InanimateBlockUpdate,
    };

    public InvisibleDeath10x10Spec() {
        super("Death", R.drawable.death_visible, 0,
                new PointF(10, 10), components, 1);
    }
}
