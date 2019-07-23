package com.gamecodeschool.platformer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

// TODO: add bitmaps like scrollingshooter?
class Button {

    enum Id {
        LEFT, RIGHT, JUMP,
    }

    //    private Bitmap mBitmap;
    private final Rect mRect;
//    private final Context mContext;

    Button(/*int resourceId, */Rect rect/*, Context context*/) {
        mRect = rect;
//        mContext = context;
//
//        setBitmap(resourceId);
    }

    void draw(Canvas canvas, Paint paint) {
//        canvas.drawBitmap(mBitmap, mRect.left, mRect.top, paint);
        canvas.drawRect(mRect, paint);
    }

    boolean contains(int x, int y) {
        return mRect.contains(x, y);
    }

    // needed to change the buttons' bitmaps (to a highlighted version) when pressed.
//    void setBitmap(int resourceId) {
//        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), resourceId);
//        int buttonSize = mRect.right - mRect.left;
//        mBitmap = Bitmap.createScaledBitmap(bitmap, buttonSize, buttonSize, false);
//    }
}
