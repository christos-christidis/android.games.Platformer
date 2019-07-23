package com.gamecodeschool.platformer;

import com.gamecodeschool.platformer.level.Level;

interface LevelStarter {
    void startNewLevel(Level.Choice levelChoice);
}
