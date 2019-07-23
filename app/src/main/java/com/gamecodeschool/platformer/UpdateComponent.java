package com.gamecodeschool.platformer;

abstract class UpdateComponent {

    private final GameObjectData mData;

    UpdateComponent(GameObjectData data) {
        mData = data;
    }

    abstract void update(long fps, GameObjectData playerData);

    GameObjectData getGameObjectData() {
        return mData;
    }
}
