package com.example.ben.metronome;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ben on 7/23/2016.
 */

public class PlaySound {

    private static MediaPlayer mPlayer;
    private Runnable runnable;
    private Handler handler;
    private boolean playState;

    public PlaySound (Context context) {
        mPlayer = MediaPlayer.create(context, R.raw.woodsound);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        handler = new Handler();
        playState = false;
    }

    public void playBeat() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            mPlayer.seekTo(0);
        }
        mPlayer.start();
    }

    public void stop() {
        mPlayer.release();
        this.playState = false;
        handler.removeCallbacks(runnable);

    }

    public void reset(final int tempo) {
        runnable = new Runnable () {
            @Override
            public void run() {
                playState = true;
                playBeat();
                handler.postDelayed(this, 60000 / tempo);
            }
        };
        handler.post(runnable);
    }

    public boolean isPlayState() {
        return playState;
    }
}
