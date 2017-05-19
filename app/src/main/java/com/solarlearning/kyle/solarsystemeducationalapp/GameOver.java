package com.solarlearning.kyle.solarsystemeducationalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    private int hScore;
    private Button highScore, retry, shareScore;
    private TextView finalScore, finalDifficulty;
    private String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_over);

        highScore = (Button) findViewById(R.id.highScore);
        retry = (Button) findViewById(R.id.retry);
        shareScore = (Button) findViewById(R.id.share_score);
        finalScore = (TextView) findViewById(R.id.finalScore);
        finalDifficulty = (TextView) findViewById(R.id.finalDifficulty);

        Intent intent = getIntent();
        hScore = intent.getIntExtra("score", 0);
        difficulty = intent.getStringExtra("diff");

        finalScore.setText("Score: " + hScore);
        finalDifficulty.setText("Difficulty: " + difficulty);

        highScore.setOnClickListener(onClickListener);
        retry.setOnClickListener(onClickListener);
        shareScore.setOnClickListener(onClickListener);
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
                case R.id.share_score:

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Explore the Solar System. \n My Score: " + hScore + "\n Difficulty: " + difficulty);
                    sendIntent.setType("text/plain");
                    startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
                    break;
                default:
                    break;
            }
        }
    };
}
