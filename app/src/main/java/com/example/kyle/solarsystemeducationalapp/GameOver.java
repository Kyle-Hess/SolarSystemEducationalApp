package com.example.kyle.solarsystemeducationalapp;

import android.content.Intent;
import android.hardware.SensorEvent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    private int hScore;
    private Button highScore;
    private Button retry;
    private TextView finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);


        highScore = (Button) findViewById(R.id.highScore);
        retry = (Button) findViewById(R.id.retry);
        finalScore = (TextView) findViewById(R.id.finalScore);

        Intent intent = getIntent();
        hScore = intent.getIntExtra("score", 0);

        finalScore.setText("Score: " + String.valueOf(hScore));

        highScore.setOnClickListener(onClickListener);
        retry.setOnClickListener(onClickListener);

    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.highScore:
                    Intent hIntent = new Intent(GameOver.this, HighScoresActivity.class);
                    startActivity(hIntent);
                    break;
                case R.id.retry:
                    Intent rIntent = new Intent(GameOver.this, GameActivity.class);
                    startActivity(rIntent);
                    break;
                default:
                    break;


            }

        }
    };

}
