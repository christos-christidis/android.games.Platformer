package com.gamecodeschool.platformer;

import android.graphics.PointF;

class BackgroundObjectData extends GameObjectData {

    private float mXClip;
    private boolean mReversedFirst;

    BackgroundObjectData(PointF startingLocation, float speed, PointF objectSize) {
        super(startingLocation, speed, objectSize);
    }

    boolean getReversedFirst() {
        return mReversedFirst;
    }

    void flipReversedFirst() {
        mReversedFirst = !mReversedFirst;
    }

    float getXClip() {
        return mXClip;
    }

    void setXClip(float xClip) {
        mXClip = xClip;
    }
}
