package com.example.user.hospitaltestwithimage;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageView profileBarImageView;
    private ImageView micImageView;
    private ImageView profileImageView;
    private TextView profileTextView;
    private int frame = 0;
    private TextToSpeech textToSpeech;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileBarImageView = (ImageView) findViewById(R.id.profile_bar_imageView);
        profileTextView = (TextView) findViewById(R.id.profile_textView);
        profileImageView = (ImageView) findViewById(R.id.profile_imageView);
        micImageView = (ImageView) findViewById(R.id.mic_imageView);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });

        new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long l) {
                switch ((int) (l/1000)) {
                    case 17:
                        profileBarImageView.setVisibility(View.VISIBLE);
                        profileTextView.setText("How can i help you");
                        profileImageView.setImageResource(R.drawable.nurse_reception);
                        textToSpeech.speak("How can i help you?", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 12:
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.male_see_doc);
                        profileTextView.setText("I would like to see a doctor");
                        profileImageView.setImageResource(R.drawable.man_hospital);
                        mediaPlayer.start();
                        break;
                    case 7:
                        profileTextView.setText("Which doctor?");
                        profileImageView.setImageResource(R.drawable.nurse_reception);
                        textToSpeech.speak("Which doctor?", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();

        micImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        micImageView.setImageResource(R.drawable.mic_busy);
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        micImageView.setImageResource(R.drawable.mic);
                        return true;
                }
                return false;
            }
        });
    }
}
