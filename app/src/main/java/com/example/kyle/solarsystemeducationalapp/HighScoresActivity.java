package com.example.kyle.solarsystemeducationalapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class HighScoresActivity extends AppCompatActivity {

    private SQLiteOpenHelper scoresDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_high_scores);

        scoresDAO = new ScoresDAOHelper(this);
        updateScore();

    }
//// TODO: 16/05/2017 Show the top 5 scores. add date of high score
    private void updateScore() {
        Cursor cursor = scoresDAO.getReadableDatabase()
                .rawQuery("select * from scores", null);
        StringBuilder builder = new StringBuilder();
        builder.append("scores: ");
        while (cursor.moveToNext()) {
            System.out.println("id: " + cursor.getString(0)
                    + " value: " + cursor.getString(1));
            builder.append(cursor.getString(1)).append(" ");
        }
        cursor.close();

        TextView scoreView = (TextView) findViewById(R.id.scoreView);
        scoreView.setText(builder.toString().trim());
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
                break;
            case R.id.action_game:
                Intent gIntent = new Intent(this, GameActivity.class);
                startActivity(gIntent);
                break;
            case R.id.action_high_scores:
                Intent hsIntent = new Intent(this, HighScoresActivity.class);
                startActivity(hsIntent);
                break;
            case R.id.action_settings:
                Intent sIntent = new Intent(this, SettingsActivity.class);
                startActivity(sIntent);
                break;
        }
        return true;
    }
}
