package com.example.ben.metronome;

import java.io.Serializable;

/**
 * Created by mljustinli on 9/17/16.
 */
public class Exception extends Default implements Serializable {
    //measures of exception range
    private int start;
    private int end;

    public Exception(int _meterTop, int _meterBot, int _tempo, int _start, int _end) {
        super(_meterTop, _meterBot, _tempo);
        start = _start;
        end = _end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}