package com.example.ben.metronome;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final PlaySound play = new PlaySound();
        play.mPlayer = MediaPlayer.create(this, R.raw.woodblock);
        play.play_button = (Button)this.findViewById(R.id.button);

        play.play_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                play.mPlayer.start();
            }
        });
        play.mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        //new Timer().scheduleAtFixedRate(play, 0, 1000);

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
