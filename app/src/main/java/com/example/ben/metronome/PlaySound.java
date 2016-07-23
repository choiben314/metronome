package com.example.ben.metronome;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;

import java.util.TimerTask;

/**
 * Created by Ben on 7/23/2016.
 */

public class PlaySound extends TimerTask{

    public MediaPlayer mPlayer;
    public Button play_button;

    public void run() {
        mPlayer.start();
    }
}
