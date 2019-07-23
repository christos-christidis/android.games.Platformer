package com.gamecodeschool.platformer;

class BackgroundUpdateComponent extends UpdateComponent {

    BackgroundUpdateComponent(GameObjectData data) {
        super(data);
    }

    @Override
    void update(long fps, GameObjectData playerData) {
        BackgroundObjectData backgroundData = (BackgroundObjectData) getGameObjectData();

        float currentXClip = backgroundData.getXClip();

        if (playerData.headingRight()) {
            currentXClip -= backgroundData.getSpeed() / fps;
            backgroundData.setXClip(currentXClip);
        } else if (playerData.headingLeft()) {
            currentXClip += backgroundData.getSpeed() / fps;
            backgroundData.setXClip(currentXClip);
        }

        if (currentXClip >= backgroundData.getObjectWidth()) {
            backgroundData.setXClip(0);
            backgroundData.flipReversedFirst();
        } else if (currentXClip <= 0) {
            backgroundData.setXClip(backgroundData.getObjectWidth());
            backgroundData.flipReversedFirst();
        }
    }
}
