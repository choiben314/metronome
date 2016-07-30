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

        play = new PlaySound(getBaseContext());

        final SeekBar tempoBar = (SeekBar)findViewById(R.id.tempoBar);
        final TextView tempoText = (TextView)findViewById(R.id.tempoText);;
        final Button playToggle = (Button)findViewById(R.id.playToggle);

        if (tempoBar != null && tempoText != null && playToggle != null) {

            playToggle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (!play.isPlayState()) {
                        playToggle.setBackgroundResource(R.drawable.ic_pause_black_24dp);
                        resume(Integer.parseInt(tempoText.getText().toString()));
                    }
                    else {
                        playToggle.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
                        play.stop();
                    }
                }
            });

            tempoBar.setMax(212);
            tempoBar.setProgress(60);

            tempoBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    tempoText.setText(String.valueOf(seekBar.getProgress() + 40));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (play.isPlayState()) {
                        resume(Integer.parseInt(tempoText.getText().toString()));
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
