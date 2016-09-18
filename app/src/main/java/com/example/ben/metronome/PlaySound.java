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
    private int currentMeasure;

    public PlaySound (Context context) {
        mPlayer = MediaPlayer.create(context, R.raw.woodsound);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        handler = new Handler();
        playState = false;
        currentMeasure = 1;
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
                double delay = 60000.0/(double)tempo;
                handler.postDelayed(this, (long) delay);
            }
        };
        handler.post(runnable);
    }

    public void playPiece (final Piece piece, final int start) {
        this.currentMeasure = start;

        runnable = new Runnable() {
            int beat = 0;
            int beatsPerMeasure;

            @Override
            public void run() {
                playState = true;
                double delay;
                PlayActivity.pieceMeasure.setText("Measure: " + getCurrentMeasure());
                if (checkException(piece, currentMeasure) == -1) {
                    playBeat();
                    delay = 60000.0/((double) (piece.getDflt().getTempo() / ((double)(4.0 / piece.getDflt().getMeterBot()))));
                    beatsPerMeasure = piece.getDflt().getMeterTop();
                    PlayActivity.pieceMeter.setText(beatsPerMeasure + "/" + piece.getDflt().getMeterBot());
                    PlayActivity.pieceTempo.setText(String.valueOf(piece.getDflt().getTempo()));
                }
                else {
                    int index = checkException(piece, currentMeasure);
                    playBeat();
                    delay = 60000.0/((double) (piece.getExcpt().get(index).getTempo() / ((double)(4.0 / piece.getExcpt().get(index).getMeterBot()))));
                    beatsPerMeasure = piece.getExcpt().get(index).getMeterTop();
                    PlayActivity.pieceMeter.setText(beatsPerMeasure + "/" + piece.getExcpt().get(index).getMeterBot());
                    PlayActivity.pieceTempo.setText(String.valueOf(piece.getExcpt().get(index).getTempo()));
                }
                beat++;

                if (beat % beatsPerMeasure == 0) {
                    beat = 0;
                    currentMeasure++;
                }

                if (currentMeasure == piece.getEnd()) {
                    currentMeasure = 1;
                    stop();
                }
                handler.postDelayed(this, (long) delay);
            }
        };
        handler.post(runnable);
    }

    public boolean isPlayState() {
        return playState;
    }

    public int checkException(Piece piece, int currentMeasure) {
        for (Exception ex : piece.getExcpt()) {
            if (ex.getStart() <= currentMeasure && currentMeasure <= ex.getEnd()) {
                return piece.getExcpt().indexOf(ex);
            }
        }
        return -1;
    }

    public int getCurrentMeasure() {
        return currentMeasure;
    }

}
