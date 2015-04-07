package com.joshmiller656.jazzmaster;

import android.os.Handler;

/**
 * Created by Josh on 4/7/2015.
 */
public class KeyThread extends Thread {
    private int[]chord;
    private Handler handler;
    public KeyThread(int[] chord, Handler handler){
        this.chord= chord;
        this.handler=handler;
    }

    @Override
    public void run() {
        super.run();
        handler.obtainMessage(0).sendToTarget();
        for(int i = 0; i<chord.length;i++){
            handler.obtainMessage(1,i,i).sendToTarget();
        }
    }
}
