package com.gamecodeschool.platformer;

import android.graphics.Canvas;
import android.graphics.Paint;

class GameObject {

    private final String mTag;
    private GameObjectData mData;

    private boolean mIsSpawned = true;

    private GraphicsComponent mGraphicsComponent;
    private UpdateComponent mUpdateComponent;

    GameObject(String tag) {
        mTag = tag;
    }

    void setGraphicsComponent(GraphicsComponent graphicsComponent) {
        mGraphicsComponent = graphicsComponent;
    }

    void setUpdateComponent(UpdateComponent updateComponent) {
        mUpdateComponent = updateComponent;
    }

    void setData(GameObjectData data) {
        mData = data;
    }

    void draw(Canvas canvas, Paint paint, Camera camera) {
        mGraphicsComponent.draw(canvas, paint, camera);
    }

    void update(long fps, GameObjectData playerData) {
        mUpdateComponent.update(fps, playerData);
    }

    boolean isSpawned() {
        return mIsSpawned;
    }

    void unspawn() {
        mIsSpawned = false;
    }

    String getTag() {
        return mTag;
    }

    GameObjectData getData() {
        return mData;
    }
}
