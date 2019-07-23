package com.gamecodeschool.platformer;

import android.graphics.PointF;
import android.graphics.RectF;

class GameObjectData {

    // The collider and location are specified in world coordinates. Read Camera to understand.
    private final RectF mCollider;
    private PointF mLocation;
    private final float mSpeed;
    private final PointF mObjectSize;
    private final PointF mStartingPosition;

    private boolean mFacingRight = true;
    private boolean mHeadingUp = false;
    private boolean mHeadingDown = false;
    private boolean mHeadingLeft = false;
    private boolean mHeadingRight = false;

    GameObjectData(PointF startingLocation, float speed, PointF objectSize) {
        mCollider = new RectF();
        mLocation = startingLocation;
        mSpeed = speed;
        mObjectSize = objectSize;

        // TODO: Can I remove this variable? It's not used anywhere yet
        mStartingPosition = new PointF(mLocation.x, mLocation.y);

        updateCollider();
    }

    void updateCollider() {
        mCollider.left = mLocation.x;
        mCollider.top = mLocation.y;
        mCollider.right = mCollider.left + getObjectWidth();
        mCollider.bottom = mCollider.top + getObjectHeight();
    }

    RectF getCollider() {
        return mCollider;
    }

    PointF getLocation() {
        return mLocation;
    }

    float getSpeed() {
        return mSpeed;
    }

    float getObjectWidth() {
        return mObjectSize.x;
    }

    float getObjectHeight() {
        return mObjectSize.y;
    }

    PointF getStartingPosition() {
        return mStartingPosition;
    }

    void headUp() {
        mHeadingUp = true;
        mHeadingDown = false;
    }

    void headDown() {
        mHeadingDown = true;
        mHeadingUp = false;
    }

    void headLeft() {
        mHeadingLeft = true;
        mHeadingRight = false;
        mFacingRight = false;
    }

    void headRight() {
        mHeadingRight = true;
        mHeadingLeft = false;
        mFacingRight = true;
    }

    boolean headingUp() {
        return mHeadingUp;
    }

    boolean headingDown() {
        return mHeadingDown;
    }

    boolean headingLeft() {
        return mHeadingLeft;
    }

    boolean headingRight() {
        return mHeadingRight;
    }

    boolean facingRight() {
        return mFacingRight;
    }

    void stopHorizontal() {
        mHeadingLeft = false;
        mHeadingRight = false;
    }

    void stopMovingLeft() {
        mHeadingLeft = false;
    }

    void stopMovingRight() {
        mHeadingRight = false;
    }

    void setLocation(float horizontal, float vertical) {
        mLocation = new PointF(horizontal, vertical);
        updateCollider();
    }
}
