package com.example.eddie.drivelog;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by eddie on 9/16/2015.
 * Plays noise of car engine reving up
 */
public class AudioPlayer {
    public void stop(){
        if (mPlayer != null){
            mPlayer.release();
            mPlayer = null;
        }
    }
    public void play(Context c){
        stop();
        mPlayer = MediaPlayer.create(c,R.raw.car_engine );
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mp){
                stop();
            }
        });
        mPlayer.start();
    }

    private MediaPlayer mPlayer;
}
