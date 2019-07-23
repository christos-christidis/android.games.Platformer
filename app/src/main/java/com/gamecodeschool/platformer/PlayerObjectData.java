package com.gamecodeschool.platformer;

import android.graphics.PointF;
import android.graphics.RectF;

class PlayerObjectData extends GameObjectData {

    private final RectF[] mColliders;

    private final int FEET_COLLIDER = 0;
    private final int HEAD_COLLIDER = 1;
    private final int LEFT_COLLIDER = 2;
    private final int RIGHT_COLLIDER = 3;

    private boolean mWantsToJump = false;
    private boolean mBumpedHead = false;

    private boolean mGrounded = true;

    PlayerObjectData(PointF startingLocation, float speed, PointF objectSize) {
        super(startingLocation, speed, objectSize);

        mColliders = new RectF[4];
        for (int i = 0; i < mColliders.length; i++) {
            mColliders[i] = new RectF();
        }
    }

    RectF[] getColliders() {
        updateColliders();
        return mColliders;
    }

    void updateColliders() {
        PointF location = getLocation();
        float objectWidth = getObjectWidth();
        float objectHeight = getObjectHeight();

        RectF mFeetCollider = mColliders[FEET_COLLIDER];
        RectF mHeadCollider = mColliders[HEAD_COLLIDER];
        RectF mLeftCollider = mColliders[LEFT_COLLIDER];
        RectF mRightCollider = mColliders[RIGHT_COLLIDER];

        // NOTE: these were arrived at by the author with pure trial-and-error.
        mFeetCollider.left = location.x + objectWidth * 0.33f;
        mFeetCollider.right = location.x + objectWidth * 0.66f;
        mFeetCollider.top = location.y + objectHeight * 0.9f;
        mFeetCollider.bottom = location.y + objectHeight;

        mHeadCollider.left = location.x + objectWidth * 0.33f;
        mHeadCollider.right = location.x + objectHeight * 0.66f;
        mHeadCollider.top = location.y;
        mHeadCollider.bottom = location.y + objectHeight * 0.1f;

        mLeftCollider.left = location.x;
        mLeftCollider.right = location.x + objectWidth * 0.1f;
        mLeftCollider.top = location.y + objectHeight * 0.2f;
        mLeftCollider.bottom = location.y + objectHeight * 0.8f;

        mRightCollider.left = location.x + objectWidth * 0.9f;
        mRightCollider.right = location.x + objectWidth;
        mRightCollider.top = location.y + objectHeight * 0.33f;
        mRightCollider.bottom = location.y + objectHeight * 0.5f;
    }

    String getColliderTag(int i) {
        if (i == FEET_COLLIDER) {
            return "Feet";
        } else if (i == HEAD_COLLIDER) {
            return "Head";
        } else if (i == LEFT_COLLIDER) {
            return "Left Side";
        } else if (i == RIGHT_COLLIDER) {
            return "Right Side";
        }

        throw new RuntimeException("getColliderTag: unexpected i found");
    }

    // The following methods are nothing new. Like objectData.headRight() which is called by the
    // input-component and handled by the update-component, so too are jump and bumped head handled.
    void jump() {
        mWantsToJump = true;
    }

    boolean wantsToJump() {
        return mWantsToJump;
    }

    // NOTE: the jump is NOT ack'ed if player is in-air or already jumping etc
    void jumpAcknowledged() {
        mWantsToJump = false;
    }

    void bumpHead() {
        mBumpedHead = true;
    }

    boolean bumpedHead() {
        return mBumpedHead;
    }

    void bumpedHeadAcknowledged() {
        mBumpedHead = false;
    }

    void setGrounded(boolean grounded) {
        mGrounded = grounded;
    }

    boolean isGrounded() {
        return mGrounded;
    }
}
