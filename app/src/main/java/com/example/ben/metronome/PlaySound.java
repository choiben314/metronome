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
    private Button play_button;
    private Timer mTimer;

    public PlaySound (Context context, Button button) {
        mPlayer = MediaPlayer.create(context, R.raw.woodblock);
        play_button = button;

        play_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPlayer.start();
            }
        });

        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mTimer = new Timer();
    }

    public void run() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            mPlayer.seekTo(0);
        }
        mPlayer.start();
    }

    public void pause() {
        mPlayer.release();
        mTimer.cancel();
        this.cancel();
    }

    public void resume(int tempo) {
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(this, 0, 60000 / tempo);
    }
}
