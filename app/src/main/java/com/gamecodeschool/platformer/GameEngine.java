package com.gamecodeschool.platformer;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.gamecodeschool.platformer.level.Level;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@SuppressWarnings("ViewConstructor")
class GameEngine extends SurfaceView implements Runnable, InputBroadcaster, LevelStarter {

    private Thread mThread = null;
    private long mFPS;

    private final GameState mGameState;
    private final GameInputObserver mGameInputObserver;

    // TODO: read book to understand why this class!
    private final List<InputObserver> mInputObservers = new CopyOnWriteArrayList<>();

    private final HUD mHUD;
    private final LevelManager mLevelManager;
    private final PhysicsEngine mPhysicsEngine;
    private final Renderer mRenderer;

    GameEngine(Context context, Point screenSize) {
        super(context);

        mHUD = new HUD(context, screenSize);
        mGameState = new GameState(this, context);
        mGameInputObserver = new GameInputObserver(this, screenSize, mGameState, context);
        mPhysicsEngine = new PhysicsEngine();
        mRenderer = new Renderer(this, screenSize);
        mLevelManager = new LevelManager(context, this, mRenderer.getPixelsPerMeter());
        BitmapStore.initialize(context);    // TODO: see comment in BitmapStore. Added this because exception!
        SoundEngine.initialize(context);    // TODO: ditto
    }

    @Override
    public void run() {
        while (mGameState.gameLoopShouldRun()) {
            long frameStartTime = System.currentTimeMillis();

            if (!mGameState.gameOver()) {
                mPhysicsEngine.update(mFPS, mLevelManager.getGameObjects(), mGameState);
            }

            mRenderer.draw(mLevelManager.getGameObjects(), mHUD, mGameState);

            long timeThisFrame = System.currentTimeMillis() - frameStartTime;
            if (timeThisFrame > 0) {
                final int MILLIS_IN_SECOND = 1000;
                mFPS = MILLIS_IN_SECOND / timeThisFrame;
            }
        }
    }

    void startGameLoop() {
        mGameState.setGameLoopShouldRun(true);
        mThread = new Thread(this);
        mThread.start();
    }

    void stopGameLoop() {
        mGameState.stopGame();
        mGameState.setGameLoopShouldRun(false);
        try {
            mThread.join();
        } catch (InterruptedException e) {
            Log.e("Exception", "stopGameLoop() " + e.getMessage());
        }
    }

    // TODO: hm, I don't like this arrangement. Couldn't I pass LevelManager to GameState instead of
    // GameEngine to do this job. Yes, I could provided that we don't need to clear and add again
    // the inputobserver which I don't know why he does!
    @Override
    public void startNewLevel(Level.Choice levelChoice) {
        BitmapStore.clearStore();
        mInputObservers.clear(); // NOTE: we must clear this because each level creates a new PlayerInputComponent!
        mInputObservers.add(mGameInputObserver);
        mLevelManager.setUpNewLevel(levelChoice, mGameState);
    }

    @Override
    public void addInputObserver(InputObserver inputObserver) {
        mInputObservers.add(inputObserver);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (InputObserver inputObserver : mInputObservers) {
            inputObserver.handleInput(event, mGameState, mHUD);
        }
        return true;
    }
}
