package com.gamecodeschool.platformer;

import android.view.MotionEvent;

class PlayerInputComponent implements InputObserver {

    private final PlayerObjectData mPlayerData;

    PlayerInputComponent(InputBroadcaster broadcaster, GameObjectData data) {
        broadcaster.addInputObserver(this);
        mPlayerData = (PlayerObjectData) data;
    }

    @Override
    public void handleInput(MotionEvent event, GameState gameState, HUD hud) {
        if (!gameState.gameOver()) {
            int i = event.getActionIndex();
            int x = (int) event.getX(i);
            int y = (int) event.getY(i);

            Button leftButton = hud.getButton(Button.Id.LEFT);
            Button rightButton = hud.getButton(Button.Id.RIGHT);
            Button jumpButton = hud.getButton(Button.Id.JUMP);

            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    if (leftButton.contains(x, y) || rightButton.contains(x, y)) {
                        mPlayerData.stopHorizontal();
                    }
                    break;
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    if (leftButton.contains(x, y)) {
                        mPlayerData.headLeft();
                    } else if (rightButton.contains(x, y)) {
                        mPlayerData.headRight();
                    } else if (jumpButton.contains(x, y)) {
                        mPlayerData.jump();
                    }
                    break;
            }
        }
    }
}
