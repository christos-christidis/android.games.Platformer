package com.gamecodeschool.platformer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

import com.gamecodeschool.platformer.gameobjectspec.GameObjectSpec;

class AnimatedGraphicsComponent extends GraphicsComponent {

    private final Animator mAnimator;
    private Rect mSectionToDraw;

    AnimatedGraphicsComponent(GameObjectData data, GameObjectSpec spec, int pixelsPerMeter, Context context) {
        super(data, spec.getResourceId());

        PointF objectSize = spec.getObjectSize();

        mAnimator = new Animator(objectSize, spec.getNumFrames(), pixelsPerMeter);

        float totalWidth = objectSize.x * spec.getNumFrames();

        BitmapStore.addBitmap(context, spec.getResourceId(), new PointF(totalWidth, objectSize.y),
                pixelsPerMeter, true);

        // TODO: move call to System inside getCurrentFrame ?
        mSectionToDraw = mAnimator.getCurrentFrame();
    }

    @Override
    void draw(Canvas canvas, Paint paint, Camera camera) {
        GameObjectData data = getGameObjectData();

        if (data.headingRight() || data.headingLeft() || data.getSpeed() == 0) {
            mSectionToDraw = mAnimator.getCurrentFrame();
        }

        Rect screenCoordinates = camera.worldToScreen(data.getLocation(),
                data.getObjectWidth(), data.getObjectHeight());

        int resourceId = getResourceId();

        Bitmap bitmap = data.facingRight() ? BitmapStore.getBitmap(resourceId) :
                BitmapStore.getBitmapReversed(resourceId);

        canvas.drawBitmap(bitmap, mSectionToDraw, screenCoordinates, paint);
    }
}
