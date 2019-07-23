package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class PlayerSpec extends GameObjectSpec {
    private static final Component[] components = new Component[]{
            Component.PlayerInput,
            Component.AnimatedGraphics,
            Component.PlayerUpdate,
    };

    public PlayerSpec() {
        super("Player", R.drawable.player, 10,
                new PointF(1, 2), components, 5);
    }
}
