package com.gamecodeschool.platformer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.io.IOException;

// Turned to Singleton, so that we don't have to pass a soundEngine everywhere. TODO: BAD DESIGN?
class SoundEngine {

    private static SoundPool soundPool;
    private static int jump_ID = -1;
    private static int reach_objective_ID = -1;
    private static int coin_pickup_ID = -1;
    private static int player_burn_ID = -1;

    private static SoundEngine ourInstance;

    static void initialize(Context context) {
        if (ourInstance == null) {
            ourInstance = new SoundEngine(context);
        }
    }

    private SoundEngine(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        try {
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            descriptor = assetManager.openFd("jump.ogg");
            jump_ID = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("reach_objective.ogg");
            reach_objective_ID = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("coin_pickup.ogg");
            coin_pickup_ID = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("player_burn.ogg");
            player_burn_ID = soundPool.load(descriptor, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void playJump() {
        soundPool.play(jump_ID, 1, 1, 0, 0, 1);
    }

    static void playReachObjective() {
        soundPool.play(reach_objective_ID, 1, 1, 0, 0, 1);
    }

    static void playCoinPickup() {
        soundPool.play(coin_pickup_ID, 1, 1, 0, 0, 1);
    }

    static void playPlayerBurn() {
        soundPool.play(player_burn_ID, 1, 1, 0, 0, 1);
    }
}
