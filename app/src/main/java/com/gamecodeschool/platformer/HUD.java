package com.gamecodeschool.platformer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.gamecodeschool.platformer.level.Level;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class HUD {

    private final Bitmap mSelectionScreenBitmap;

    private final int mTextSize;
    private final int mScreenWidth;
    private final int mScreenHeight;

    private final float ONE_THIRD = 0.33f;
    private final float TWO_THIRDS = 0.66f;

    private Map<Button.Id, Button> mButtons;

    HUD(Context context, Point screenSize) {
        mScreenWidth = screenSize.x;
        mScreenHeight = screenSize.y;

        mTextSize = mScreenWidth / 25;

        Bitmap selectionScreenBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.selection_screen);
        mSelectionScreenBitmap = Bitmap.createScaledBitmap(selectionScreenBitmap, mScreenWidth, mScreenHeight, false);

        prepareButtons();
    }

    private void prepareButtons() {
        int buttonWidth = mScreenWidth / 14;
        int buttonHeight = mScreenHeight / 12;
        int buttonPadding = mScreenWidth / 90;

        Rect leftRect = new Rect(buttonPadding, mScreenHeight - buttonPadding - buttonHeight,
                buttonPadding + buttonWidth, mScreenHeight - buttonPadding);

        Rect rightRect = new Rect(buttonPadding * 2 + buttonWidth, mScreenHeight - buttonPadding - buttonHeight,
                buttonPadding * 2 + buttonWidth * 2, mScreenHeight - buttonPadding);

        Rect jumpRect = new Rect(mScreenWidth - buttonPadding - buttonWidth, mScreenHeight - buttonPadding - buttonHeight,
                mScreenWidth - buttonPadding, mScreenHeight - buttonPadding);

        mButtons = new HashMap<>();
        mButtons.put(Button.Id.LEFT, new Button(leftRect));
        mButtons.put(Button.Id.RIGHT, new Button(rightRect));
        mButtons.put(Button.Id.JUMP, new Button(jumpRect));
    }

    void drawSelectionScreen(Canvas canvas, Paint paint, GameState gameState) {
        canvas.drawBitmap(mSelectionScreenBitmap, 0, 0, paint);

        // draw rectangle to highlight the text
        final int LIGHT_BLUE_TRANSPARENT = Color.argb(100, 26, 128, 182);
        paint.setColor(LIGHT_BLUE_TRANSPARENT);
        canvas.drawRect(0, 0, mScreenWidth, mTextSize * 4, paint);

        paint.setColor(Color.WHITE);
        drawLevelNames(canvas, paint);
        drawFastestTimes(canvas, paint, gameState);

        // draw rectangle to highlight the large text
        paint.setColor(LIGHT_BLUE_TRANSPARENT);
        canvas.drawRect(0, mScreenHeight - mTextSize * 2,
                mScreenWidth, mScreenHeight, paint);
        // draw large text
        paint.setColor(Color.WHITE);
        paint.setTextSize(mTextSize * 1.5f);
        canvas.drawText("DOUBLE TAP A LEVEL TO PLAY", ONE_THIRD + mTextSize * 2,
                mScreenHeight - mTextSize / 2f, paint);
    }

    private void drawLevelNames(Canvas canvas, Paint paint) {
        paint.setTextSize(mTextSize);
        canvas.drawText("Underground", mTextSize, mTextSize * 2, paint);
        canvas.drawText("Mountains", mScreenWidth * ONE_THIRD + mTextSize, mTextSize * 2, paint);
        canvas.drawText("City", mScreenWidth * TWO_THIRDS + mTextSize, mTextSize * 2, paint);
    }

    private void drawFastestTimes(Canvas canvas, Paint paint, GameState gameState) {
        paint.setTextSize(mTextSize / 1.8f);
        String bestTimeFormat = "BEST: %d seconds";
        canvas.drawText(String.format(Locale.getDefault(), bestTimeFormat,
                gameState.getFastestTime(Level.Choice.UNDERGROUND)),
                mTextSize, mTextSize * 3, paint);
        canvas.drawText(String.format(Locale.getDefault(), bestTimeFormat,
                gameState.getFastestTime(Level.Choice.MOUNTAINS)),
                mScreenWidth * ONE_THIRD + mTextSize, mTextSize * 3, paint);
        canvas.drawText(String.format(Locale.getDefault(), bestTimeFormat,
                gameState.getFastestTime(Level.Choice.CITY)),
                mScreenWidth * TWO_THIRDS + mTextSize, mTextSize * 3, paint);
    }

    void drawTimeAndControls(Canvas canvas, Paint paint, GameState gameState) {
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, mScreenWidth, mTextSize, paint);

        // draw time
        paint.setTextSize(mTextSize / 1.5f);
        paint.setColor(Color.WHITE);
        canvas.drawText("Time:" + gameState.getCurrentTime() + " + " + gameState.getCoinsRemaining() * 10,
                mTextSize / 4f, mTextSize / 1.5f, paint);

        // Draw buttons
        paint.setColor(Color.WHITE);
        paint.setAlpha(100);

        for (Button.Id key : mButtons.keySet()) {
            Button button = mButtons.get(key);
            if (button != null) {
                button.draw(canvas, paint);
            }
        }

        // set the colors back
        paint.setColor(Color.WHITE);
    }

    Button getButton(Button.Id buttonId) {
        return mButtons.get(buttonId);
    }
}
