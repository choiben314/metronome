package com.example.ben.metronome;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by benap on 9/17/2016.
 */
public class Piece implements Serializable {
    private String title;
    private Default dflt;
    private ArrayList<Exception> excpt;
    private int end;

    public Piece(String _title, Default _dflt, ArrayList<Exception> _excpt, int _end) {
        title = _title;
        dflt = _dflt;
        excpt = _excpt;
        end = _end;
    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel out, int flags) {
//        out.writeString(title);
//        out.writeParcelable(dflt, flags);
//        out.write
//    }

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

    @Override
    public String toString () {
        return title;
    }
}
