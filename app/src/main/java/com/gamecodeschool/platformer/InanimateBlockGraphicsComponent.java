package com.gamecodeschool.platformer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.gamecodeschool.platformer.gameobjectspec.GameObjectSpec;

class InanimateBlockGraphicsComponent extends GraphicsComponent {

    // TODO: Do I really need to pass pixelsPerMeter here? WE can get it from camera
    InanimateBlockGraphicsComponent(GameObjectData data, GameObjectSpec spec, int pixelsPerMeter, Context context) {
        super(data, spec.getResourceId());

        BitmapStore.addBitmap(context, spec.getResourceId(), spec.getObjectSize(),
                pixelsPerMeter, false);
    }

    @Override
    public void draw(Canvas canvas, Paint paint, Camera camera) {
        GameObjectData data = getGameObjectData();
        int resourceId = getResourceId();

        Bitmap bitmap = BitmapStore.getBitmap(resourceId);

        Rect screenCoordinates = camera.worldToScreen(data.getLocation(),
                data.getObjectWidth(), data.getObjectHeight());

        canvas.drawBitmap(bitmap, screenCoordinates.left, screenCoordinates.top, paint);
    }
}
