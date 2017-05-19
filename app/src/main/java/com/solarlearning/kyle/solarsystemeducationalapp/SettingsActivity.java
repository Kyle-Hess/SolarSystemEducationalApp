package com.solarlearning.kyle.solarsystemeducationalapp;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchMusic;
    private RadioGroup difficulty;
    private Button deleteDb;

    SharedPreferences prefs;
    private boolean soundCheck = true;
    private ScoresDAOHelper scoresDAO;
    private String diffSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        scoresDAO = new ScoresDAOHelper(this);

        switchMusic = (Switch) findViewById(R.id.switchMusic);
        difficulty = (RadioGroup) findViewById(R.id.difficulty);
        deleteDb = (Button) findViewById(R.id.delete_db);

        deleteDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAll();
            }
        });

        difficulty.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                updateDifficulty();
            }
        });

//// TODO: 11/05/2017 Have it so only app sounds are mutes not phone system sounds
        switchMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    soundCheck = true;
                    prefs.edit().putBoolean("prefAudio", soundCheck).apply();

                } else {
                    soundCheck = false;
                    prefs.edit().putBoolean("prefAudio", soundCheck).apply();

                }
            }
        });
    }

    public void removeAll() {
        SQLiteDatabase db = scoresDAO.getWritableDatabase();
        db.execSQL("DELETE FROM scoretable");
        db.close();
    }

    private void updateDifficulty() {
        int radioButtonID = difficulty.getCheckedRadioButtonId();
        View radioButtonG = difficulty.findViewById(radioButtonID);
        RadioButton r = (RadioButton) radioButtonG;
        diffSelected = r.getText().toString();
        prefs.edit().putString("prefRadio", diffSelected).apply();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_home:
//                Intent mIntent = new Intent(this, MainActivity.class);
//                startActivity(mIntent);
//                break;
//            case R.id.action_game:
//                Intent gIntent = new Intent(this, GameActivity.class);
//                startActivity(gIntent);
//                break;
//            case R.id.action_high_scores:
//                Intent hsIntent = new Intent(this, HighScoresActivity.class);
//                startActivity(hsIntent);
//                break;
//            case R.id.action_settings:
//                Intent sIntent = new Intent(this, SettingsActivity.class);
//                startActivity(sIntent);
//                break;
//            default:
//                break;
//        }
//        return true;
//    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            diffSelected = prefs.getString("prefRadio", diffSelected);

            switch (diffSelected) {
                case "Easy":
                    difficulty.check(R.id.difficultyEasy);
                    break;
                case "Regular":
                    difficulty.check(R.id.difficultyRegular);
                    break;
                case "Hard":
                    difficulty.check(R.id.difficultyHard);
                    break;
            }
        } catch (Exception e) {
            difficulty.check(R.id.difficultyRegular);
        }

        try {
            soundCheck = prefs.getBoolean("prefAudio", soundCheck);

            if (soundCheck) {
                switchMusic.setChecked(true);
            } else {
                switchMusic.setChecked(false);
            }

        } catch (Exception e) {
            difficulty.check(R.id.difficultyRegular);
        }
    }

    //when the app is closed, the unit named is saved
    @Override
    protected void onStop() {
        super.onStop();
        prefs.edit().putString("prefRadio", diffSelected).apply();
        prefs.edit().putBoolean("prefAudio", soundCheck).apply();
    }
}
