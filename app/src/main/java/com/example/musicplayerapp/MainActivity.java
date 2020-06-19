package com.example.musicplayerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.io.InputStream;
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView anName, anArtist, chName, chArtist, dName, dArtist, prName, prArtist, tekName, tekArtist, boxName, boxArtist;
    private ImageView anAlbum, anPlay, chAlbum, chPlay, dAlbum, dPlay, prAlbum, prPlay, tekAlbum, tekPlay, stop, boxAlbum, boxPlay, rewind, forward, pause;
    private SeekBar seekBar;
    private Switch aSwitch;
    private MediaPlayer mediaPlayer;
    private Runnable runnable;
    private Handler handler;
    private boolean flag= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anAlbum= (ImageView) findViewById(R.id.anything_album);
        anPlay= (ImageView) findViewById(R.id.anything_play);
        anArtist= (TextView) findViewById(R.id.anything_songArtist);
        anName= (TextView) findViewById(R.id.anything_songName);

        chAlbum= (ImageView) findViewById(R.id.chargie_album);
        chPlay= (ImageView) findViewById(R.id.chargie_play);
        chArtist= (TextView) findViewById(R.id.chargie_songArtist);
        chName= (TextView) findViewById(R.id.chargie_songName);

        dAlbum= (ImageView) findViewById(R.id.dela_album);
        dPlay= (ImageView) findViewById(R.id.dela_play);
        dArtist= (TextView) findViewById(R.id.dela_songArtist);
        dName= (TextView) findViewById(R.id.dela_songName);

        prAlbum= (ImageView) findViewById(R.id.private_album);
        prPlay= (ImageView) findViewById(R.id.private_play);
        prArtist= (TextView) findViewById(R.id.private_songArtist);
        prName= (TextView) findViewById(R.id.private_songName);

        tekAlbum= (ImageView) findViewById(R.id.tek_album);
        tekPlay= (ImageView) findViewById(R.id.tek_play);
        tekArtist= (TextView) findViewById(R.id.tek_songArtist);
        tekName= (TextView) findViewById(R.id.tek_songName);

        boxAlbum= (ImageView) findViewById(R.id.box_album);
        boxPlay= (ImageView) findViewById(R.id.box_play);
        boxArtist= (TextView) findViewById(R.id.box_songArtist);
        boxName= (TextView) findViewById(R.id.box_songName);

        rewind= (ImageView) findViewById(R.id.rewind);
        forward=(ImageView) findViewById(R.id.forward);
        stop= (ImageView) findViewById(R.id.stopMusic);
        pause= (ImageView) findViewById(R.id.pauseMusic);

        seekBar= (SeekBar) findViewById(R.id.seekbar);

        aSwitch= (Switch) findViewById(R.id.theme_switch);

        handler= new Handler();

        //mediaPlayer = MediaPlayer.create(this, R.raw.anything);


        anPlay.setOnClickListener(this);
        chPlay.setOnClickListener(this);
        dPlay.setOnClickListener(this);
        prPlay.setOnClickListener(this);
        tekPlay.setOnClickListener(this);
        boxPlay.setOnClickListener(this);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            aSwitch.setChecked(true);
        }

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    //restartApp();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    //restartApp();
                }
            }
        });



        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                anPlay.setImageResource(R.drawable.ic_play);
                anPlay.setClickable(true);
                chPlay.setImageResource(R.drawable.ic_play);
                chPlay.setClickable(true);
                dPlay.setImageResource(R.drawable.ic_play);
                dPlay.setClickable(true);
                prPlay.setImageResource(R.drawable.ic_play);
                prPlay.setClickable(true);
                tekPlay.setImageResource(R.drawable.ic_play);
                tekPlay.setClickable(true);
                boxPlay.setImageResource(R.drawable.ic_play);
                boxPlay.setClickable(true);
                changeSeekbar();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                anPlay.setImageResource(R.drawable.ic_play);
                anPlay.setClickable(true);
                chPlay.setImageResource(R.drawable.ic_play);
                chPlay.setClickable(true);
                dPlay.setImageResource(R.drawable.ic_play);
                dPlay.setClickable(true);
                prPlay.setImageResource(R.drawable.ic_play);
                prPlay.setClickable(true);
                tekPlay.setImageResource(R.drawable.ic_play);
                tekPlay.setClickable(true);
                boxPlay.setImageResource(R.drawable.ic_play);
                boxPlay.setClickable(true);
            }
        });

       rewind.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
           }
       });
       forward.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
           }
       });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.anything_play:
                mediaPlayer= MediaPlayer.create(this, R.raw.anything);
                mediaPlayer.start();
                anPlay.setImageResource(R.drawable.ic_pause);
                anPlay.setClickable(false);
                mediaPlayer.setNextMediaPlayer(MediaPlayer.create(this, R.raw.chargie));
                break;
            case R.id.chargie_play:
                mediaPlayer= MediaPlayer.create(this, R.raw.chargie);
                mediaPlayer.start();
                chPlay.setImageResource(R.drawable.ic_pause);
                chPlay.setClickable(false);
                break;
            case R.id.dela_play:
                mediaPlayer= MediaPlayer.create(this, R.raw.dela_move);
                mediaPlayer.start();
                dPlay.setImageResource(R.drawable.ic_pause);
                dPlay.setClickable(false);
                break;
            case R.id.private_play:
                mediaPlayer= MediaPlayer.create(this, R.raw.private_zess);
                mediaPlayer.start();
                prPlay.setImageResource(R.drawable.ic_pause);
                prPlay.setClickable(false);
                break;
            case R.id.tek_play:
                mediaPlayer= MediaPlayer.create(this, R.raw.tek_away);
                mediaPlayer.start();
                tekPlay.setImageResource(R.drawable.ic_pause);
                tekPlay.setClickable(false);
                break;
            case R.id.box_play:
                mediaPlayer= MediaPlayer.create(this, R.raw.the_box);
                mediaPlayer.start();
                boxPlay.setImageResource(R.drawable.ic_pause);
                boxPlay.setClickable(false);
                break;
        }

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                seekBar.setMax(mediaPlayer.getDuration());
                mediaPlayer.start();
                changeSeekbar();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b){
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void changeSeekbar(){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        if (mediaPlayer.isPlaying()){
            runnable= new Runnable() {
                @Override
                public void run() {
                    changeSeekbar();
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }

    private void restartApp() {
        Intent intent= new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
