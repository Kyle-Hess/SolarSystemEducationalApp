package com.example.kyle.solarsystemeducationalapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.SoundPool;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchMusic;
    private RadioGroup difficulty;

    private SoundPool pool;
    private Context context;

    SharedPreferences prefs;
    private int idxDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        switchMusic = (Switch) findViewById(R.id.switchMusic);
        difficulty = (RadioGroup) findViewById(R.id.difficulty);

        difficulty.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                updateDifficulty();
            }
        });
    }

    private void updateDifficulty() {
        int radioButtonID = difficulty.getCheckedRadioButtonId();
        View radioButtonG = difficulty.findViewById(radioButtonID);
       idxDifficulty = difficulty.indexOfChild(radioButtonG);

        System.out.println(idxDifficulty);
        prefs.edit().putInt("prefRadio", idxDifficulty).apply();
    }


//    public SettingsActivity(Context context){
//        this.context = context;
//        SoundPool.Builder builder = new SoundPool.Builder();
//        builder. setMaxStreams(10);
//        pool = builder.build();
//    }

    public int addSound(int resourceID){
        return pool.load(context, resourceID,1);
    }

    public void play(int soundID){
        pool.play(soundID, 1, 1, 1, 0, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent mIntent = new Intent(this, MainActivity.class);
                startActivity(mIntent);
                Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_game:
                Intent gIntent = new Intent(this, GameActivity.class);
                startActivity(gIntent);
                Toast.makeText(this, "Game selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_high_scores:
                Intent hsIntent = new Intent(this, HighScoresActivity.class);
                startActivity(hsIntent);
                Toast.makeText(this, "High Scores selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Intent sIntent = new Intent(this, SettingsActivity.class);
                startActivity(sIntent);
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            idxDifficulty = prefs.getInt("prefRadio", idxDifficulty);

            switch (idxDifficulty) {
                case 0:
                    difficulty.check(R.id.difficultyEasy);
                    break;
                case 1:
                    difficulty.check(R.id.difficultyRegular);
                    break;
                case 2:
                    difficulty.check(R.id.difficultyHard);
                    break;
            }
        } catch (Exception e) {
            difficulty.check(R.id.difficultyRegular);
        }
    }

    //when the app is closed, the unit named is saved
    @Override
    protected void onStop() {
        super.onStop();
        prefs.edit().putInt("prefRadio", idxDifficulty).apply();
    }
}
