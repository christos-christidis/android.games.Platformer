package com.gamecodeschool.platformer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.SparseArray;

// TODO: Do I REALLY need to set this as singleton?

// Previously each object contained its own bitmap. Now that we can have 100 tiles w the same bitmap,
// the bitmap must be stored once elsewhere and that's what this class does.
class BitmapStore {

    // SparseArray can be used when the key is an int. Under 10000 items, SparseArray and Map have
    // the same performance. After that point, SparseArray is significantly faster at retrieving data
    // while Map is significantly faster at storing data! So choice depends on the application!
    // In ANY case, SparseArray uses MUCH less memory so it's perfect for this here.
    private static SparseArray<Bitmap> mBitmaps;
    private static SparseArray<Bitmap> mBitmapsReversed;

    private static BitmapStore ourInstance;

    private BitmapStore(Context context) {
        // NOTE: we have less than 30 bitmaps and the reversed bitmaps are few:
        mBitmaps = new SparseArray<>(30);
        mBitmapsReversed = new SparseArray<>(30);

        addBitmap(context, R.drawable.death_visible, new PointF(1, 1), 128, true);
    }

    static void initialize(Context context) {
        if (ourInstance == null) {
            ourInstance = new BitmapStore(context);
        }
    }

    // return death_visible if the bitmap requested is not found
    static Bitmap getBitmap(int resourceId) {
        Bitmap defaultBitmap = mBitmaps.get(R.drawable.death_visible);
        return mBitmaps.get(resourceId, defaultBitmap);
    }

    static Bitmap getBitmapReversed(int resourceId) {
        Bitmap defaultBitmap = mBitmapsReversed.get(R.drawable.death_visible);
        return mBitmapsReversed.get(resourceId, defaultBitmap);
    }

    static void addBitmap(Context context, int resourceId, PointF objectSize, int pixelsPerMeter,
                          boolean reversedBitmapNeeded) {
        if (mBitmaps.get(resourceId) != null) {
            return;
        }

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId);
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) objectSize.x * pixelsPerMeter,
                (int) objectSize.y * pixelsPerMeter, false);

        mBitmaps.put(resourceId, bitmap);

        if (reversedBitmapNeeded) {
            Matrix matrix = new Matrix();
            matrix.setScale(-1, 1);
            Bitmap bitmapReversed = Bitmap.createBitmap(bitmap, 0, 0,
                    bitmap.getWidth(), bitmap.getHeight(), matrix, true);

            mBitmapsReversed.put(resourceId, bitmapReversed);
        }
    }

    // Theoretically every level has different bitmaps, so it's a good idea to clear and load only
    // the necessary bitmaps. This doesn't matter for our game, but for big games it's a MUST.
    static void clearStore() {
        mBitmaps.clear();
        mBitmapsReversed.clear();
    }
}
