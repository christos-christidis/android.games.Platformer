package com.gamecodeschool.platformer;

import android.content.Context;
import android.graphics.PointF;

import com.gamecodeschool.platformer.gameobjectspec.GameObjectSpec;

class GameObjectFactory {

    private final Context mContext;
    private final GameEngine mGameEngine;
    private final int mPixelsPerMeter;

    GameObjectFactory(Context context, GameEngine gameEngine, int pixelsPerMeter) {
        mContext = context;
        mGameEngine = gameEngine;
        mPixelsPerMeter = pixelsPerMeter;
    }

    GameObject create(GameObjectSpec spec, PointF location) {
        GameObject object = new GameObject(spec.getTag());

        float speed = spec.getSpeed();
        PointF objectSize = spec.getObjectSize();

        GameObjectData objectData;    // initialized in switch

        switch (object.getTag()) {
            case "Background":
                objectData = new BackgroundObjectData(location, speed, objectSize);
                break;
            case "Player":
                objectData = new PlayerObjectData(location, speed, objectSize);
                break;
            default:
                // "plain" objectData
                objectData = new GameObjectData(location, speed, objectSize);
        }

        object.setData(objectData);

        int numComponents = spec.getComponents().length;
        for (int i = 0; i < numComponents; i++) {
            switch (spec.getComponents()[i]) {
                case AnimatedGraphics:
                    object.setGraphicsComponent(new AnimatedGraphicsComponent(objectData, spec, mPixelsPerMeter, mContext));
                    break;
                case BackgroundGraphics:
                    object.setGraphicsComponent(new BackgroundGraphicsComponent(objectData, spec, mPixelsPerMeter, mContext));
                    break;
                case InanimateBlockGraphics:
                    object.setGraphicsComponent(new InanimateBlockGraphicsComponent(object.getData(), spec, mPixelsPerMeter, mContext));
                    break;
                case BackgroundUpdate:
                    object.setUpdateComponent(new BackgroundUpdateComponent(objectData));
                    break;
                case DecorativeBlockUpdate:
                    object.setUpdateComponent(new DecorativeBlockUpdateComponent(object.getData()));
                    break;
                case InanimateBlockUpdate:
                    object.setUpdateComponent(new InanimateBlockUpdateComponent(object.getData()));
                    break;
                case MovableBlockUpdate:
                    object.setUpdateComponent(new MovableBlockUpdateComponent(objectData));
                    break;
                case PlayerUpdate:
                    object.setUpdateComponent(new PlayerUpdateComponent(objectData));
                    break;
                case PlayerInput:
                    new PlayerInputComponent(mGameEngine, objectData);
                    break;
                default:
                    throw new RuntimeException("component not covered by switch!");
            }
        }

        return object;
    }
}
