package com.example.ben.metronome;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static int MIN_TEMPO = 40;

    public PlaySound play;

    public void resume(int tempo) {
        if (play != null) {
            play.stop();
        }
        play = new PlaySound(getBaseContext());
        play.reset(tempo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView tempoText = (TextView)findViewById(R.id.tempoText);
        final SeekBar tempoBar = (SeekBar)findViewById(R.id.tempoBar);
        final Button playToggle = (Button)findViewById(R.id.playToggle);
        final Button addOne = (Button)findViewById(R.id.addOne);
        final Button subOne = (Button)findViewById(R.id.subOne);

        final Button selectionScreen = (Button)findViewById(R.id.selectionScreen);

        play = new PlaySound(getBaseContext());

        if (tempoBar != null && tempoText != null && playToggle != null && addOne != null && subOne != null && selectionScreen != null) {

            playToggle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (!play.isPlayState()) {
                        playToggle.setBackgroundResource(R.drawable.ic_pause_black_24dp);
                        resume(tempoBar.getProgress() + MIN_TEMPO);
                    }
                    else {
                        playToggle.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
                        play.stop();
                    }
                }
            });

            addOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tempoBar.setProgress(tempoBar.getProgress() + 1);
                    if (play.isPlayState()) {
                        resume(tempoBar.getProgress() + MIN_TEMPO);
                    }
                }
            });

            subOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tempoBar.setProgress(tempoBar.getProgress() - 1);
                    if (play.isPlayState()) {
                        resume(tempoBar.getProgress() + MIN_TEMPO);
                    }
                }
            });
            selectionScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), SelectionActivity.class);
                    startActivity(intent);
                }
            });



            tempoBar.setMax(212);
            tempoBar.setProgress(100 - MIN_TEMPO);

            tempoBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    tempoText.setText(String.valueOf(seekBar.getProgress() + MIN_TEMPO));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (play.isPlayState()) {
                        resume(tempoBar.getProgress() + MIN_TEMPO);
                    }
                }
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        play.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()) {

            case R.id.action_help:
                intent = new Intent(this, HelpActivity.class);
                this.startActivity(intent);
                return true;
            case R.id.action_about:
                intent = new Intent(this, AboutActivity.class);
                this.startActivity(intent);
                return true;
            case R.id.action_settings:
                Toast.makeText(getBaseContext(), "Settings not yet available.", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
