package com.example.kyle.solarsystemeducationalapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.SoundPool;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Kyle on 10/05/2017.
 */

public class SoundManager {

    private Context context;
    private SoundPool pool;
    private boolean isOnOff;


    public SoundManager(Context context) {
        SharedPreferences prefs;
//        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
//        isOnOff = prefs.getBoolean("prefsound", true);

        this.context = context;
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(10);
        pool = builder.build();

    }


    public int addSound(int resourceID) {

        return pool.load(context, resourceID, 1);
    }

    public void play(int soundID) {

        pool.play(soundID, 1, 1, 1, 0, 1);
    }
}
