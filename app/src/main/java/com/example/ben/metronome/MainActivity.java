package com.example.ben.metronome;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button playButton;
    public PlaySound play;

    public void resume(int tempo) {
        play = new PlaySound(getBaseContext(), playButton);
        play.resume(tempo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        playButton = (Button) findViewById(R.id.button);
        play = new PlaySound(getBaseContext(), playButton);

        final SeekBar tempoBar = (SeekBar)findViewById(R.id.tempoBar);
        final TextView tempoText = (TextView)findViewById(R.id.tempoText);;

        if (tempoBar != null) {

            tempoBar.setMax(212);
            tempoBar.setProgress(60);

            resume(100);

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
                    if (tempoText != null) {
                        play.pause();
                        resume(Integer.parseInt(tempoText.getText().toString()));
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_settings:
                Toast.makeText(getBaseContext(), "Settings not yet available.", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_help:
                Intent intent = new Intent(this, HelpActivity.class);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
