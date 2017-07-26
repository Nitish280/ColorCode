package com.example.nitishsingh.colourdemo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nitishsingh.colourdemo.utiles.Constants;

/**
 * Created by Nitish Singh on 26-07-2017.
 */

public class TableClass extends SQLiteOpenHelper {

    Context context;

    public TableClass(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        // in here passing database_name and database_version in the constructor
        super(context, Constants.DATABASE_NAME, factory, Constants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE " + Constants.COLOR_LIST + " (" +
                Constants.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Constants.COLOR_CODE + " TEXT," +
                Constants.COLOR_NAME + " TEXT,);";

        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        context.deleteDatabase(Constants.DATABASE_NAME);
        onCreate(db);
    }
}
