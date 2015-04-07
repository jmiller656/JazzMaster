package com.joshmiller656.jazzmaster;

import android.media.MediaPlayer;

/**
 * Created by Josh on 4/4/2015.
 */
public class MusicThread extends Thread{
    private MediaPlayer m;
    public MusicThread(MediaPlayer m)
    {
        this.m = m;
    }
    public void run(){
        m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        m.start();

    }
}
