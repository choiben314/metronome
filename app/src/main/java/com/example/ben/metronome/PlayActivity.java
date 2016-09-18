package com.example.ben.metronome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {

    private PlaySound play;
    private Piece piece;
    private int holdMeasure;

    public static TextView pieceTempo;
    public static TextView pieceMeter;
    public static TextView pieceMeasure;

    public void resume (int start) {
        if (play != null) {
            play.stop();
        }
        play = new PlaySound(getBaseContext());
        play.playPiece(piece, start);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new Toast(this).makeText(this, "Hello", Toast.LENGTH_LONG);

        holdMeasure = 1;
        final Button playToggle = (Button) findViewById(R.id.piecePlayToggle);
        final Button editButton = (Button) findViewById(R.id.editButton);

        pieceTempo = (TextView) findViewById(R.id.pieceTempo);
        pieceMeter = (TextView) findViewById(R.id.pieceMeter);
        pieceMeasure = (TextView) findViewById(R.id.pieceMeasure);

        Intent intent = getIntent();
        piece = (Piece)intent.getSerializableExtra("Piece");
        play = new PlaySound(getBaseContext());

        if (playToggle != null) {
            playToggle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (!play.isPlayState()) {
                        playToggle.setBackgroundResource(R.drawable.ic_pause_black_24dp);
                        resume(holdMeasure);
                    } else {
                        playToggle.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
                        holdMeasure = play.getCurrentMeasure();
                        play.stop();
                    }
                }
            });
        }

        if (editButton != null) {
            editButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(PlayActivity.this, EditActivity.class);
                    intent.putExtra("Piece", piece);
                    startActivity(intent);
                }
            });
        }

        getSupportActionBar().setTitle(piece.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
