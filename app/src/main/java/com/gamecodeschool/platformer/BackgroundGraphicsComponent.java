package com.gamecodeschool.platformer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

import com.gamecodeschool.platformer.gameobjectspec.GameObjectSpec;

class BackgroundGraphicsComponent extends GraphicsComponent {

    BackgroundGraphicsComponent(GameObjectData data, GameObjectSpec spec, int pixelsPerMeter, Context context) {
        super(data, spec.getResourceId());

        BitmapStore.addBitmap(context, spec.getResourceId(), spec.getObjectSize(),
                pixelsPerMeter, true);
    }

    @Override
    void draw(Canvas canvas, Paint paint, Camera camera) {
        BackgroundObjectData backgroundData = (BackgroundObjectData) getGameObjectData();

        int resourceId = getResourceId();
        Bitmap bitmap = BitmapStore.getBitmap(resourceId);
        Bitmap bitmapReversed = BitmapStore.getBitmapReversed(resourceId);

        int scaledXClip = (int) (backgroundData.getXClip() * camera.getPixelsPerMeter());

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        PointF location = backgroundData.getLocation();

        float floatStartY = ((camera.getyCenter() - ((camera.getWorldCenterY() - location.y) * camera.getPixelsPerMeterY())));
        int startY = (int) floatStartY;

        float floatEndY = ((camera.getyCenter() - ((camera.getWorldCenterY() - location.y - backgroundData.getObjectHeight()) * camera.getPixelsPerMeterY())));
        int endY = (int) floatEndY;

        Rect fromRect1 = new Rect(0, 0, width - scaledXClip, height);
        Rect toRect1 = new Rect(scaledXClip, startY, width, endY);

        Rect fromRect2 = new Rect(width - scaledXClip, 0, width, height);
        Rect toRect2 = new Rect(0, startY, scaledXClip, endY);

        if (!backgroundData.getReversedFirst()) {
            canvas.drawBitmap(bitmap, fromRect1, toRect1, paint);
            canvas.drawBitmap(bitmapReversed, fromRect2, toRect2, paint);
        } else {
            canvas.drawBitmap(bitmap, fromRect2, toRect2, paint);
            canvas.drawBitmap(bitmapReversed, fromRect1, toRect1, paint);
        }
    }
}
