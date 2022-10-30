package com.onivv.andrewtate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {
    Switch sw;
    ImageView andrew;
    SeekBar sb;
    MediaPlayer mp;
    AudioManager am;
    TextView bugatti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        andrew=findViewById(R.id.imageView);
        bugatti=findViewById(R.id.textView);
        sw = findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(this);
        sb =findViewById(R.id.seekBar);
        sb.setProgress(100);
        sb.setOnSeekBarChangeListener(this);
        mp=MediaPlayer.create(this,R.raw.andrewsong);

        am= (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        sb.setMax(max);
        sb.setProgress(max/2);
        am.setStreamVolume(AudioManager.STREAM_MUSIC,max/2,0);
        sb.setOnSeekBarChangeListener(this);

    }
    @Override
    protected void onPause(){
        super.onPause();
        mp.release();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b) {
            andrew.setVisibility(View.VISIBLE);
            bugatti.setVisibility(View.VISIBLE);

            mp.start();
        }
        else {
            andrew.setVisibility(View.GONE);
           mp.pause();
        }


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        float alpha=(float)progress/10;
        andrew.setAlpha(alpha);
        am.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}