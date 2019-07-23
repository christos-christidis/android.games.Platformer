package com.gamecodeschool.platformer;

import android.graphics.PointF;
import android.graphics.Rect;

class Animator {

    private final Rect mSourceRect;
    private final int mNumFrames;
    private int mCurrentFrame;
    private long mFrameTicker;
    private final int mMillisPerFrame;
    private final int mFrameWidth;

    Animator(PointF objectSize, int numFrames, int pixelsPerMeter) {
        mCurrentFrame = 0;
        mNumFrames = numFrames;

        mFrameWidth = (int) objectSize.x * pixelsPerMeter;

        int frameHeight = (int) objectSize.y * pixelsPerMeter;

        mSourceRect = new Rect(0, 0, mFrameWidth, frameHeight);

        final int MILLIS_IN_SECOND = 1000;
        final int ANIM_FPS = 10;
        mMillisPerFrame = MILLIS_IN_SECOND / ANIM_FPS;
        mFrameTicker = 0;
    }

    Rect getCurrentFrame() {
        long time = System.currentTimeMillis();
        if (time > mFrameTicker + mMillisPerFrame) {
            mFrameTicker = time;
            mCurrentFrame++;
            if (mCurrentFrame >= mNumFrames) {
                mCurrentFrame = 0;
            }
        }

        mSourceRect.left = mCurrentFrame * mFrameWidth;
        mSourceRect.right = mSourceRect.left + mFrameWidth;

        return mSourceRect;
    }
}
