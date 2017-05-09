package com.example.kyle.solarsystemeducationalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchMusic;
    private RadioGroup difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        switchMusic = (Switch) findViewById(R.id.switchMusic);
        difficulty = (RadioGroup) findViewById(R.id.difficulty);

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
}
