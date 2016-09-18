package com.example.ben.metronome;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Created by benap on 9/17/2016.
 */
public class Piece implements Serializable{
    private String title;
    private Default dflt;
    private ArrayList<Exception> excpt;
    private int end;

    public Piece(String _title, Default _dflt, ArrayList<Exception> _excpt) {
        title = _title;
        dflt = _dflt;
        excpt = _excpt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Default getDflt() {
        return dflt;
    }

    public void setDflt(Default dflt) {
        this.dflt = dflt;
    }

    public ArrayList<Exception> getExcpt() {
        return excpt;
    }

    public void setExcpt(ArrayList<Exception> excpt) {
        this.excpt = excpt;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
