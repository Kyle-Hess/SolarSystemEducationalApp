package com.example.kyle.solarsystemeducationalapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kyle on 16/05/2017.
 */

public class ScoresDAOHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private Context context;

    ScoresDAOHelper(Context context) {
        super(context, "scores.db", null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(context.getString(R.string.create_table, "scores", "value"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(context.getString(R.string.delete_table, "scores"));
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
