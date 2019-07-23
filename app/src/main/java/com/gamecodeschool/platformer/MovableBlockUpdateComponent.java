package com.gamecodeschool.platformer;

import android.graphics.PointF;

class MovableBlockUpdateComponent extends UpdateComponent {

    MovableBlockUpdateComponent(GameObjectData data) {
        super(data);
    }

    @Override
    void update(long fps, GameObjectData playerData) {
        GameObjectData data = getGameObjectData();
        PointF location = data.getLocation();
        float speed = data.getSpeed();

        if (data.headingUp()) {
            location.y -= speed / fps;
        } else if (data.headingDown()) {
            location.y += speed / fps;
        } else {
            // must be first update in game so start w going down
            data.headDown();
        }

        if (data.headingUp() && location.y <= data.getStartingPosition().y) {
            data.headDown();
        } else if (data.headingDown() && location.y >= (data.getStartingPosition().y + data.getObjectHeight() * 10)) {
            data.headUp();
        }

        data.updateCollider();
    }
}
