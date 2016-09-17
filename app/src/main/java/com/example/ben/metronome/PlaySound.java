package com.example.ben.metronome;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;

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
                double delay = 60000/(double)tempo;
                handler.postDelayed(this, (long) delay);
            }
        };
        handler.post(runnable);
    }

    public boolean isPlayState() {
        return playState;
    }
}
