package com.gamecodeschool.platformer;

import android.graphics.Canvas;
import android.graphics.Paint;

abstract class GraphicsComponent {

    private final GameObjectData mData;
    private final int mResourceId;

    GraphicsComponent(GameObjectData data, int resourceId) {
        mData = data;
        mResourceId = resourceId;
    }

    abstract void draw(Canvas canvas, Paint paint, Camera camera);

    int getResourceId() {
        return mResourceId;
    }

    GameObjectData getGameObjectData() {
        return mData;
    }
}
