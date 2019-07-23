package com.gamecodeschool.platformer;

import android.graphics.PointF;
import android.util.Log;

class PlayerUpdateComponent extends UpdateComponent {

    private boolean mInTheMiddleOfJump = false;
    private long mJumpStartTime;

    PlayerUpdateComponent(GameObjectData data) {
        super(data);
    }

    @Override
    void update(long fps, GameObjectData data) {
        PlayerObjectData playerData = (PlayerObjectData) data;

        PointF location = playerData.getLocation();
        float speed = playerData.getSpeed();

        if (playerData.headingLeft()) {
            location.x -= speed / fps;
        } else if (playerData.headingRight()) {
            location.x += speed / fps;
        }

        if (playerData.bumpedHead()) {
            mInTheMiddleOfJump = false;
            playerData.bumpedHeadAcknowledged();
        }

        if (playerData.wantsToJump() && playerData.isGrounded() && !mInTheMiddleOfJump) {
            SoundEngine.playJump();
            mInTheMiddleOfJump = true;
            playerData.jumpAcknowledged();
            mJumpStartTime = System.currentTimeMillis();

        }

        final long MAX_JUMP_TIME = 400;
        final float GRAVITY = 6;    // the down speed due to gravity

        if (!mInTheMiddleOfJump) {
            location.y += GRAVITY / fps;
        } else {
            playerData.setGrounded(false);

            long time = System.currentTimeMillis();
            if (time < mJumpStartTime + MAX_JUMP_TIME * 2 / 3) {
                Log.i("WTF", String.format("time = %d, upwards", time));
                Log.i("WTF", "before: location.y = " + playerData.getLocation().y);
                location.y -= (GRAVITY * 1.8) / fps;    // 1st phase of jump; going upwards
                Log.i("WTF", "after: location.y = " + playerData.getLocation().y);
            } else if (time < mJumpStartTime + MAX_JUMP_TIME) {
                location.y += GRAVITY / fps;    // in 2nd downward phase of jump
            } else if (time > mJumpStartTime + MAX_JUMP_TIME) {
                mInTheMiddleOfJump = false;     // time to end the jump
            }
        }

        playerData.updateColliders();
    }
}
