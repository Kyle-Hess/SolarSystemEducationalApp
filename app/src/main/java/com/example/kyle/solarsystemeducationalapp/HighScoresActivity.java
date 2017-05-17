package com.example.kyle.solarsystemeducationalapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HighScoresActivity extends AppCompatActivity {

    private SQLiteOpenHelper scoresDAO;

    public static ArrayList<String> scoreArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_high_scores);

        scoresDAO = new ScoresDAOHelper(this);

        //scoreArray.clear();

        //testUpdateScore();
        updateScore();

        //GridView gridView = (GridView)findViewById(R.id.gridView);
        //gridView.setAdapter(adapter);

    }
//// TODO: 16/05/2017 Show the top 5 scores. add date of high score
    private void updateScore() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        adapter.clear();

        Cursor cursor = scoresDAO.getReadableDatabase()
                .rawQuery("select * from scoretable", null);
        StringBuilder builder = new StringBuilder();

        builder.append("Scores: " + "\n");
        while (cursor.moveToNext()) {
            System.out.println("id: " + cursor.getString(0)
                    + " value: " + cursor.getString(1) +cursor.getString(2));
            builder.append(cursor.getString(1)).append(", ");
            builder.append(cursor.getString(2)).append(", ");
            builder.append(cursor.getString(3)).append(" \n ");

            String scoreList = cursor.getString(1) +", "+ cursor.getString(2)+", "+ cursor.getString(3)+"\n";
            adapter.add(scoreList);
        }
        cursor.close();

        TextView scoreView = (TextView) findViewById(R.id.scoreView);
        //scoreView.setText(builder.toString().trim());

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    private void testUpdateScore() {
        Cursor cursor = scoresDAO.getReadableDatabase()
                .rawQuery("select * from scoretable", null);
        if (cursor.moveToFirst()) {
            do {

                String scoreList = cursor.getString(1) +", "+ cursor.getString(2)+", "+ cursor.getString(3)+"\n";
                scoreArray.add(scoreList);
                // Adding contact to list
            } while (cursor.moveToNext());
        }cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, scoreArray);

        ListView listView = (ListView) findViewById(R.id.listView);
        //listView.setAdapter(adapter);


        TextView scoreView = (TextView) findViewById(R.id.scoreView);
        scoreView.setText(adapter.getItem(0));

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
