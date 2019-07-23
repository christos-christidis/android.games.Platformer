package com.gamecodeschool.platformer;

import android.content.Context;
import android.content.SharedPreferences;

import com.gamecodeschool.platformer.level.Level;

final class GameState {

    private static volatile boolean mGameLoopShouldRun = false;
    private static volatile boolean mGameOver = true;
    private static volatile boolean mDrawingObjectsIsAllowed = false;

    private final LevelStarter mLevelStarter;
    private Level.Choice mCurrentLevel;

    private long mStartTimeInMillis;

    private int mCoinsAvailable;
    private int mCoinsCollected;

    private final SharedPreferences mPrefs;
    private final String FASTEST_UNDERGROUND_TIME = "fastest_underground_time";
    private final String FASTEST_MOUNTAINS_TIME = "fastest_mountains_time";
    private final String FASTEST_CITY_TIME = "fastest_city_time";

    private int mFastestUnderground;
    private int mFastestMountains;
    private int mFastestCity;

    GameState(LevelStarter levelStarter, Context context) {
        mLevelStarter = levelStarter;
        mPrefs = context.getSharedPreferences("HiScore", Context.MODE_PRIVATE);

        mFastestUnderground = mPrefs.getInt(FASTEST_UNDERGROUND_TIME, 1000);
        mFastestMountains = mPrefs.getInt(FASTEST_MOUNTAINS_TIME, 1000);
        mFastestCity = mPrefs.getInt(FASTEST_CITY_TIME, 1000);
    }

    void startNewGame(Level.Choice levelChoice) {
        stopGame();
        mLevelStarter.startNewLevel(levelChoice);
        startGame();
        mCurrentLevel = levelChoice;
        mStartTimeInMillis = System.currentTimeMillis();
    }

    void death() {
        stopGame();
        SoundEngine.playPlayerBurn();
    }

    void objectiveReached() {
        stopGame();

        int totalTime = getCurrentTime() + getCoinsRemaining() * 10;
        SharedPreferences.Editor editor = mPrefs.edit();

        switch (mCurrentLevel) {
            case UNDERGROUND:
                if (totalTime < mFastestUnderground) {
                    mFastestUnderground = totalTime;
                    editor.putInt(FASTEST_UNDERGROUND_TIME, mFastestUnderground);
                }
                break;
            case MOUNTAINS:
                if (totalTime < mFastestMountains) {
                    mFastestMountains = totalTime;
                    editor.putInt(FASTEST_MOUNTAINS_TIME, mFastestMountains);
                }
                break;
            case CITY:
                if (totalTime < mFastestCity) {
                    mFastestCity = totalTime;
                    editor.putInt(FASTEST_CITY_TIME, mFastestCity);
                }
                break;
            default:
                throw new RuntimeException("Some level is not covered by the switch!");
        }

        editor.apply();
    }

    int getFastestTime(Level.Choice levelChoice) {
        switch (levelChoice) {
            case UNDERGROUND:
                return mFastestUnderground;
            case MOUNTAINS:
                return mFastestMountains;
            case CITY:
                return mFastestCity;
            default:
                throw new RuntimeException("level choice not covered by switch!");
        }
    }

    int getCurrentTime() {
        long MILLIS_IN_SECOND = 1000;
        return (int) ((System.currentTimeMillis() - mStartTimeInMillis) / MILLIS_IN_SECOND);
    }

    void coinCollected() {
        mCoinsCollected++;
    }

    int getCoinsRemaining() {
        return mCoinsAvailable - mCoinsCollected;
    }

    void coinAddedToLevel() {
        mCoinsAvailable++;
    }

    void resetCoins() {
        mCoinsAvailable = 0;
        mCoinsCollected = 0;
    }

    void stopGame() {
        mGameOver = true;
        mDrawingObjectsIsAllowed = false;
    }

    private void startGame() {
        mGameOver = false;
        mDrawingObjectsIsAllowed = true;
    }

    void setGameLoopShouldRun(boolean gameLoopShouldRun) {
        mGameLoopShouldRun = gameLoopShouldRun;
    }

    boolean gameLoopShouldRun() {
        return mGameLoopShouldRun;
    }

    boolean drawingObjectsIsAllowed() {
        return mDrawingObjectsIsAllowed;
    }

    boolean gameOver() {
        return mGameOver;
    }
}
