package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

import com.gamecodeschool.platformer.R;

public class BackgroundCitySpec extends GameObjectSpec {

    private static final Component[] components = new Component[]{
            Component.BackgroundGraphics,
            Component.BackgroundUpdate,
    };

    public BackgroundCitySpec() {
        super("Background", R.drawable.city, 3,
                new PointF(100, 70), components, 1);
    }
}
