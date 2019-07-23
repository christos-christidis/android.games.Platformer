package com.gamecodeschool.platformer;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

class Camera {

    private final Point mScreenCenter;
    private final int mPixelsPerMeter;
    private final PointF mCameraLocation;
    private final Rect mConvertedRect;

    Camera(Point screenSize) {
        mScreenCenter = new Point(screenSize.x / 2, screenSize.y / 2);

        final int HORIZONTAL_METERS_ON_SCREEN = 48;
        mPixelsPerMeter = screenSize.x / HORIZONTAL_METERS_ON_SCREEN;

        mConvertedRect = new Rect();
        mCameraLocation = new PointF();
    }

    // TODO: why Y? Also, I already have getPixelsPerMeter
    int getPixelsPerMeterY() {
        return mPixelsPerMeter;
    }

    int getyCenter() {
        return mScreenCenter.y;
    }

    float getWorldCenterY() {
        return mCameraLocation.y;
    }

    // We always set the camera's world location to where the player is.
    void setCameraLocation(PointF playerLocation) {
        mCameraLocation.set(playerLocation);
    }

    int getPixelsPerMeter() {
        return mPixelsPerMeter;
    }

    // NOTE: An object's location is somewhere in the world (which contains all the tiles of a level).
    // All objects start at integer locations, ie a specific tile, but may end up somewhere in between
    // if their speed is not integer.
    Rect worldToScreen(PointF objectLocation, float objectWidth, float objectHeight) {
        // NOTE: for the player, these will be 0.
        float metersToTheLeftOfPlayer = mCameraLocation.x - objectLocation.x;
        float metersAbovePlayer = mCameraLocation.y - objectLocation.y;

        int left = (int) (mScreenCenter.x - metersToTheLeftOfPlayer * mPixelsPerMeter);
        int top = (int) (mScreenCenter.y - metersAbovePlayer * mPixelsPerMeter);
        int right = (int) (left + objectWidth * mPixelsPerMeter);
        int bottom = (int) (top + objectHeight * mPixelsPerMeter);

        // TODO: why do I need this field?
        mConvertedRect.set(left, top, right, bottom);
        return mConvertedRect;
    }
}
