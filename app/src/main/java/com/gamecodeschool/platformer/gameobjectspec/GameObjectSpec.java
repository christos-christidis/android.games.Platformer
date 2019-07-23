package com.gamecodeschool.platformer.gameobjectspec;

import android.graphics.PointF;

public abstract class GameObjectSpec {

    public enum Component {
        AnimatedGraphics,
        InanimateBlockGraphics,
        BackgroundGraphics,
        BackgroundUpdate,
        PlayerUpdate,
        InanimateBlockUpdate,
        MovableBlockUpdate,
        DecorativeBlockUpdate,
        PlayerInput,
    }

    private final String mTag;
    private final int mResourceId;
    private final float mSpeed;
    private final PointF mSize;
    private final Component[] mComponents;
    private final int mNumFrames;

    GameObjectSpec(String tag, int resourceId, float speed, PointF size, Component[] components, int numFrames) {

        mTag = tag;
        mResourceId = resourceId;
        mSpeed = speed;
        mSize = size;
        mComponents = components;
        mNumFrames = numFrames;
    }

    public String getTag() {
        return mTag;
    }

    public int getResourceId() {
        return mResourceId;
    }

    public float getSpeed() {
        return mSpeed;
    }

    public PointF getObjectSize() {
        return mSize;
    }

    public Component[] getComponents() {
        return mComponents;
    }

    public int getNumFrames() {
        return mNumFrames;
    }
}
