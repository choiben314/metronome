package com.example.ben.metronome;

/**
 * Created by mljustinli on 9/17/16.
 */
public class Default {
    private int meterTop;
    private int meterBot;
    private int tempo; //BPM lol

    public Exception(int _meterTop, int _meterBot, int _tempo) {
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
