package com.example.ben.metronome;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mljustinli on 9/17/16.
 */
public class Default implements Serializable{
    private int meterTop;
    private int meterBot;
    private int tempo; //BPM lol

    public Default(int _meterTop, int _meterBot, int _tempo) {
        meterTop = _meterTop;
        meterBot = _meterBot;
        tempo = _tempo;
    }


    public int getMeterTop() {
        return meterTop;
    }

    public void setMeterTop(int meterTop) {
        this.meterTop = meterTop;
    }

    public int getMeterBot() {
        return meterBot;
    }

    public void setMeterBot(int meterBot) {
        this.meterBot = meterBot;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
