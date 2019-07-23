package com.gamecodeschool.platformer;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.gamecodeschool.platformer.level.Level;

// NOTE: the reason I use double-tap to start the game is because the player often accidentally taps
// after gameover and new game starts immediately!
class GameInputObserver implements InputObserver {

    private final float THIRD_OF_SCREEN;

    private final GameState mGameState;   // set in handleInput because it's needed onDoubleTap is called

    private final GestureDetectorCompat mGestureDetector;

    GameInputObserver(InputBroadcaster broadcaster, Point screenSize, GameState gameState, Context context) {
        broadcaster.addInputObserver(this);

        THIRD_OF_SCREEN = screenSize.x / 3f;
        mGameState = gameState;

        mGestureDetector = new GestureDetectorCompat(context, new MyGestureListener());
    }

    @Override
    public void handleInput(MotionEvent event, GameState gameState, HUD hud) {
        if (gameState.gameOver()) {
            mGestureDetector.onTouchEvent(event);
        }
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDoubleTap(MotionEvent event) {
            float x = event.getX();

            if (x < THIRD_OF_SCREEN) {
                mGameState.startNewGame(Level.Choice.UNDERGROUND);
            } else if (x >= THIRD_OF_SCREEN && x < THIRD_OF_SCREEN * 2) {
                mGameState.startNewGame(Level.Choice.MOUNTAINS);
            } else if (x >= THIRD_OF_SCREEN * 2) {
                mGameState.startNewGame(Level.Choice.CITY);
            }

            return true;
        }

        // SimpleOnGestureListener's implementation of this returns false, so I have to override it,
        // because if onDown (which starts all gestures) returns false, the OS assumes I don't care
        // about them and does not notify me of any gesture!
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }
}
