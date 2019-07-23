package com.gamecodeschool.platformer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.List;

class Renderer {

    private final SurfaceHolder mSurfaceHolder;
    private final Paint mPaint;

    private final Camera mCamera;

    Renderer(SurfaceView surfaceView, Point screenSize) {
        mSurfaceHolder = surfaceView.getHolder();
        mPaint = new Paint();
        mCamera = new Camera(screenSize);
    }

    int getPixelsPerMeter() {
        return mCamera.getPixelsPerMeter();
    }

    void draw(List<GameObject> objects, HUD hud, GameState gameState) {
        if (mSurfaceHolder.getSurface().isValid()) {
            Canvas canvas = mSurfaceHolder.lockCanvas();

            if (gameState.gameOver()) {
//                Log.i("WTF", "drawing selection screen");
                hud.drawSelectionScreen(canvas, mPaint, gameState);
            } else {
                canvas.drawColor(Color.BLACK);

                if (gameState.drawingObjectsIsAllowed()) {
                    PointF playerLocation = objects.get(LevelManager.PLAYER_INDEX).getData().getLocation();
                    mCamera.setCameraLocation(playerLocation);

                    for (GameObject object : objects) {
                        if (object.isSpawned()) {
                            object.draw(canvas, mPaint, mCamera);
                        }
                    }
                }

//                Log.i("WTF", "drawing normal HUD");
                hud.drawTimeAndControls(canvas, mPaint, gameState);
            }

            mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}
