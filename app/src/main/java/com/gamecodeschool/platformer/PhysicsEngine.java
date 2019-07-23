package com.gamecodeschool.platformer;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import java.util.List;

class PhysicsEngine {

    void update(long fps, List<GameObject> objects, GameState gameState) {
        GameObjectData playerData = objects.get(LevelManager.PLAYER_INDEX).getData();

        for (GameObject object : objects) {
            object.update(fps, playerData);
        }

        detectCollisions(objects, gameState);
    }

    private void detectCollisions(List<GameObject> objects, GameState gameState) {
        boolean collisionOccurred = false;

        // NOTE: added 1 suffix to be able to reuse playerData name
        GameObjectData playerData1 = objects.get(LevelManager.PLAYER_INDEX).getData();
        PlayerObjectData playerData = (PlayerObjectData) playerData1;

        PointF playerLocation = playerData.getLocation();
        RectF[] playerColliders = playerData.getColliders();

        for (GameObject object : objects) {
            if (object.isSpawned()) {
                RectF objectCollider = object.getData().getCollider();

                if (RectF.intersects(objectCollider, playerData.getCollider())) {
                    collisionOccurred = true;
                }

                GameObjectData objectData = object.getData();
                PointF objectLocation = objectData.getLocation();

                if (objects.indexOf(object) != LevelManager.PLAYER_INDEX) {
                    // where was player hit?
                    for (int i = 0; i < playerColliders.length; i++) {
                        if (RectF.intersects(objectData.getCollider(), playerColliders[i])) {
                            switch (object.getTag() + " with " + playerData.getColliderTag(i)) {
                                case "Movable Platform with Feet":
                                    playerData.setGrounded(true);
                                    playerLocation.y = objectLocation.y - playerData.getObjectHeight();
                                    break;
                                case "Death with Feet":
                                    Log.i("WTF", "death with feet");
                                    Log.i("WTF", "width = " + object.getData().getObjectWidth());
                                    gameState.death();
                                    break;
                                case "Objective Tile with Feet":
                                    SoundEngine.playReachObjective();
                                    gameState.objectiveReached();
                                    break;
                                case "Inert Tile with Feet":
                                    Log.i("WTF", "touching inert");
                                    playerData.setGrounded(true);
                                    playerLocation.y = objectLocation.y - playerData.getObjectHeight();
                                    break;
                                case "Inert Tile with Head":
                                    playerLocation.y = objectLocation.y + objectData.getObjectHeight();
                                    playerData.bumpHead();
                                    break;
                                case "Inert Tile with Left Side":
                                    playerData.stopMovingLeft();
                                    playerLocation.x = objectLocation.x + objectData.getObjectWidth();
                                    break;
                                case "Inert Tile with Right Side":
                                    playerData.stopMovingRight();
                                    playerLocation.x = objectLocation.x - playerData.getObjectWidth();
                                    break;
                                case "Collectible with Feet":
                                case "Collectible with Head":
                                case "Collectible with Left Side":
                                case "Collectible with Right Side":
                                    SoundEngine.playCoinPickup();
                                    object.unspawn();
                                    gameState.coinCollected();
                                    break;
                                default:
                                    // do nothing in all other cases
                                    break;
                            }
                        }

                    }
                }

            }
        }

        if (!collisionOccurred) {
            playerData.setGrounded(false);
        }
    }
}
