package com.example.ben.metronome;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ben on 7/23/2016.
 */

public class PlaySound extends TimerTask{

    private static MediaPlayer mPlayer;
    private Timer mTimer;
    private boolean playState;

    public PlaySound (Context context) {
        mPlayer = MediaPlayer.create(context, R.raw.woodsound);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mTimer = new Timer();
        playState = false;
    }

    public void run() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            mPlayer.seekTo(0);
        }
        mPlayer.start();
    }

    public void stop() {
        mPlayer.release();
        mTimer.cancel();
        mTimer.purge();
        this.cancel();
        this.playState = false;

    }

    public void reset(int tempo) {
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(this, 0, 60000 / tempo);
        this.playState = true;
    }

    public boolean isPlayState() {
        return playState;
    }
}
