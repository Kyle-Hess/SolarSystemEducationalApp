package com.solarlearning.kyle.solarsystemeducationalapp;

import android.content.Context;
import android.media.SoundPool;

/**
  Created by Kyle on 10/05/2017.
 */

/*
 sound effects:
  https://www.freesound.org/people/zzwerty/sounds/315878/
  https://www.freesound.org/people/ERH/sounds/30306/
 */

public class SoundManager {

    private Context context;
    private SoundPool pool;

    public SoundManager(Context context) {
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
