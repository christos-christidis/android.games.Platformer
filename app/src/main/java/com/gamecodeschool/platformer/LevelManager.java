package com.gamecodeschool.platformer;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

import com.gamecodeschool.platformer.gameobjectspec.*;
import com.gamecodeschool.platformer.level.Level;
import com.gamecodeschool.platformer.level.LevelCity;
import com.gamecodeschool.platformer.level.LevelMountains;
import com.gamecodeschool.platformer.level.LevelUnderground;

import java.util.ArrayList;
import java.util.List;

final class LevelManager {

    static int PLAYER_INDEX = 0;
    private final List<GameObject> mObjects;
    private final GameObjectFactory mFactory;

    LevelManager(Context context, GameEngine gameEngine, int pixelsPerMeter) {
        mObjects = new ArrayList<>();
        mFactory = new GameObjectFactory(context, gameEngine, pixelsPerMeter);
    }

    void setUpNewLevel(Level.Choice levelChoice, GameState gameState) {
        gameState.resetCoins();
        mObjects.clear();

        Level level;
        switch (levelChoice) {
            case UNDERGROUND:
                level = new LevelUnderground();
                break;
            case MOUNTAINS:
                level = new LevelMountains();
                break;
            case CITY:
                level = new LevelCity();
                break;
            default:
                throw new RuntimeException("some level is not covered by the switch!");
        }

        // Backgrounds 1, 2, 3 (City, Underground, Mountains...)
        // . = empty space
        // a = lAmpost
        // b = Brick tile
        // c = mine Cart
        // d = Dead tree
        // e = snowy trEe
        // g = Grass tile
        // i = stalagmIte
        // l = coaL
        // m = Movable platform
        // n = coNcrete
        // o = Objective
        // p = Player
        // r = scoRched tile
        // s = Stone pile
        // t = stalacTite
        // w = snoW tile
        // x = Collectable
        // y = invisible death_invisible
        // z = Fire

        List<String> levelTiles = level.getTiles();

        for (int row = 0; row < levelTiles.size(); row++) {
            for (int column = 0; column < levelTiles.get(row).length(); column++) {
                PointF coords = new PointF(column, row);

                switch (levelTiles.get(row).charAt(column)) {
                    case '1':
                        mObjects.add(mFactory.create(new BackgroundCitySpec(), coords));
                        break;
                    case '2':
                        mObjects.add(mFactory.create(new BackgroundUndergroundSpec(), coords));
                        break;
                    case '.':
                        // no tile here, just takes up space
                        break;
                    case '3':
                        mObjects.add(mFactory.create(new BackgroundMountainSpec(), coords));
                        break;
                    case 'a':
                        mObjects.add(mFactory.create(new LamppostTileSpec(), coords));
                        break;
                    case 'b':
                        mObjects.add(mFactory.create(new BrickTileSpec(), coords));
                        break;
                    case 'c':
                        mObjects.add(mFactory.create(new CartTileSpec(), coords));
                        break;
                    case 'd':
                        mObjects.add(mFactory.create(new DeadTreeTileSpec(), coords));
                        break;
                    case 'e':
                        mObjects.add(mFactory.create(new SnowyTreeTileSpec(), coords));
                        break;
                    case 'g':
                        mObjects.add(mFactory.create(new GrassTileSpec(), coords));
                        break;
                    case 'i':
                        mObjects.add(mFactory.create(new StalagmiteTileSpec(), coords));
                        break;
                    case 'l':
                        mObjects.add(mFactory.create(new CoalTileSpec(), coords));
                        break;
                    case 'm':
                        mObjects.add(mFactory.create(new MovablePlatformSpec(), coords));
                        break;
                    case 'n':
                        mObjects.add(mFactory.create(new ConcreteTileSpec(), coords));
                        break;
                    case 'o':
                        mObjects.add(mFactory.create(new ObjectiveTileSpec(), coords));
                        break;
                    case 'p':
                        mObjects.add(mFactory.create(new PlayerSpec(), coords));
                        // Remember the location of the player
                        PLAYER_INDEX = mObjects.size() - 1;
                        break;
                    case 'r':
                        mObjects.add(mFactory.create(new ScorchedTileSpec(), coords));
                        break;
                    case 's':
                        mObjects.add(mFactory.create(new StonePileTileSpec(), coords));
                        break;
                    case 't':
                        mObjects.add(mFactory.create(new StalactiteTileSpec(), coords));
                        break;
                    case 'w':
                        mObjects.add(mFactory.create(new SnowTileSpec(), coords));
                        break;
                    case 'x':
                        mObjects.add(mFactory.create(new CollectibleObjectSpec(), coords));
                        gameState.coinAddedToLevel();
                        break;
                    case 'y':
                        mObjects.add(mFactory.create(new InvisibleDeath10x10Spec(), coords));
                        break;
                    case 'z':
                        mObjects.add(mFactory.create(new FireTileSpec(), coords));
                        break;
                    default:
                        Log.e("Unhandled item in level", "row:" + row + " column:" + column);
                        break;
                }
            }
        }
    }

    List<GameObject> getGameObjects() {
        return mObjects;
    }
}
