package com.example.nikos.sampleaudioapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button play, pause, volumeUp, volumeDown;
    private MediaPlayer mediaPlayer;
    private float left=1,right=1;
    private TextView volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //code for play/pause
        play = (Button) findViewById(R.id.button_play);
        pause = (Button) findViewById(R.id.button_pause);
        volumeUp=(Button) findViewById(R.id.button_volume_up);
        volumeDown=(Button) findViewById(R.id.button_volume_down);

        volume=(TextView) findViewById(R.id.volume_text);


        mediaPlayer = MediaPlayer.create(this, R.raw.alphaville);
        mediaPlayer.setVolume(left,right);



        //Play
        play.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(getApplicationContext(), "Playing sound", Toast.LENGTH_SHORT).show();
                                        mediaPlayer.start();
                                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                                            @Override
                                            public void onCompletion(MediaPlayer mediaPlayer){
                                                Toast.makeText(MainActivity.this,"I'm done!", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        pause.setEnabled(true);
                                        play.setEnabled(false);
                                    }

                                }
        );


        //Pause
        pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Pausing sound",Toast.LENGTH_SHORT).show();
                    mediaPlayer.pause();
                    pause.setEnabled(false);
                    play.setEnabled(true);
         }
        });


         //Volume up
        volumeUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    left+=0.05;
                    right+=0.05;
                    if (left>1 && right>1) {
                        left=1;
                        right=1;
                    }
                   // Toast.makeText(getApplicationContext(), "Volume up="+left*100,Toast.LENGTH_SHORT).show();
                    mediaPlayer.setVolume(left,right);
                    volume.setText("Volume="+Math.round(left*100));
                                    }

        });


        //Volume down
        volumeDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left-=0.05;
                right-=0.05;
                if (left<0 && right<0) {
                    left=0;
                    right=0;
                }
                //Toast.makeText(getApplicationContext(), "Volume down="+left*100,Toast.LENGTH_SHORT).show();
                mediaPlayer.setVolume(left,right);
                volume.setText("Volume="+Math.round(left*100));


            }

        });






    }

}