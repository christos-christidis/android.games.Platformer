package com.gamecodeschool.platformer;

class DecorativeBlockUpdateComponent extends UpdateComponent {

    DecorativeBlockUpdateComponent(GameObjectData data) {
        super(data);
    }

    @Override
    public void update(long fps, GameObjectData playerData) {
        // This is empty since the object doesn't move
    }
}
