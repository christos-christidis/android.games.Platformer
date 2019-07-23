package com.gamecodeschool.platformer;

class InanimateBlockUpdateComponent extends UpdateComponent {

    private boolean mColliderNotSet = true;

    InanimateBlockUpdateComponent(GameObjectData data) {
        super(data);
    }

    // This object does not move (like decorative blocks) so the method normally would be empty.
    // Except we need to call updateCollider() at some point, which we would normally do in a spawn
    // component (the collider of this object will remain the same since it doesn't move through the world;
    // the collider is expressed in world coordinates.
    // TODO: couldn't we just call updateCollider in the GameObject's constructor?
    @Override
    public void update(long fps, GameObjectData playerData) {
        if (mColliderNotSet) {
            getGameObjectData().updateCollider();
            mColliderNotSet = false;
        }
    }
}
