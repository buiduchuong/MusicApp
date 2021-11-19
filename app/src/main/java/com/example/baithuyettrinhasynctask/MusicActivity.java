package com.example.baithuyettrinhasynctask;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

public class MusicActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Animation animation;
    ImageView imageView_pause, imageView_dv;
    TextView textView_trai, textView_phai, textView_tenBH;
    SeekBar seekBar_music;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        anhxa();
        mediaPlayer = MediaPlayer.create(this, R.raw.tron_tim);
        mediaPlayer.start();

        seekBarMusic();
    }

    private void anhxa() {
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        imageView_dv = findViewById(R.id.imageView_dv);
        textView_tenBH = findViewById(R.id.textView_tenBaiHat);
        imageView_pause = findViewById(R.id.imageView_pause);
        seekBar_music = findViewById(R.id.seekBar_music);
        textView_phai = findViewById(R.id.textView_phai);
        textView_trai = findViewById(R.id.textView_trai);
    }

    private void seekBarMusic() {
        int tgBaiHat = mediaPlayer.getDuration();
        textView_phai.setText(new SimpleDateFormat("mm:ss").format(tgBaiHat));
        seekBar_music.setMax(tgBaiHat);
        new AsyncTask<Integer, Integer, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                textView_tenBH.setText("Trốn tìm");
                imageView_dv.setAnimation(animation);
                Toast.makeText(MusicActivity.this, "Start", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(MusicActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                textView_trai.setText(new SimpleDateFormat("mm:ss").format(values[0]));
                seekBar_music.setProgress(values[0]);
            }

            @Override
            protected String doInBackground(Integer... integers) {
                int i = 0;
               /* if((int e = 0) <= 2){

                }*/
                while (i <= tgBaiHat) {
                    i = mediaPlayer.getCurrentPosition();
                    publishProgress(i);
                }
                return "End";
            }
        }.execute(tgBaiHat);
    }

    private void stop() {
        mediaPlayer.stop();
    }

    private void pause() {
        if (mediaPlayer.isPlaying()) {
            imageView_pause.setImageResource(R.drawable.icon_play);
            mediaPlayer.pause();
           // animation.cancel();
        } else {
            mediaPlayer.start();
            imageView_pause.setImageResource(R.drawable.ic_pause);
          //  animation.start();
        }
    }

    public void click_music(View view) {
        switch (view.getId()) {
            case R.id.imageView_pause:
                pause();
                break;
            case R.id.imageView_stop:
                stop();
                break;
        }

    }
}