package com.example.nitishsingh.colourdemo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;

import com.example.nitishsingh.colourdemo.Model.ColorData;
import com.example.nitishsingh.colourdemo.utiles.Constants;

import java.util.LinkedList;
import java.util.List;

import static android.R.attr.country;

/**
 * Created by Nitish Singh on 26-07-2017.
 */

public class DBHelper {
    private SQLiteDatabase db;
    private final Context context;
    private final TableClass dbHelper;

    public static DBHelper db_helper = null;

    public DBHelper(Context context) {
        this.context = context;
        dbHelper = new TableClass(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    public static DBHelper getInstance(Context context) {
        try {
            if (db_helper == null) {
                db_helper = new DBHelper(context);
                db_helper.open();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        return db_helper;
    }

    public void open() {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            Log.i("open database error", e.toString());
            db = dbHelper.getReadableDatabase();

        }
    }

    public void close() {
        if (db.isOpen()) {
            db.close();
        }
    }

    public boolean dbOpenCheck() {
        return db.isOpen();
    }

    public long insertContentVals(String tabName, ContentValues content) {

        long id = 0;

        try {
            db.beginTransaction();
            id = db.insert(tabName, null, content);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        return id;
    }


    public Cursor getTableRecords(String tableName, String[] coulmn, String where, String orderBy) {
        Cursor cursor = db.query(false, tableName, coulmn, where, null, null, null, orderBy, null);

        return cursor;
    }

    public int getFullCount(String table, String where) {
        Cursor cursor = db.query(false, table, null, where, null, null, null, null, null);

        int no = 0;
        try {
            if (cursor != null) {

                cursor.moveToFirst();
                no = cursor.getCount();
                cursor.close();
            }
        } finally {
            cursor.close();
        }

        return no;
    }

    public ColorData getAllColors() {
        List<ColorData> Color = new LinkedList<ColorData>();



        String query = "select * from " + Constants.COLOR_LIST;

        Cursor cursor = db.rawQuery(query, null);

        ColorData color = null;
        if (cursor.moveToFirst()) {
            do {
                color = new ColorData();
                color.setId((cursor.getString(0)).toString());
                color.setColorCode(cursor.getString(1));
                color.setColorName(cursor.getString(2));
                Color.add(color);

            } while (cursor.moveToNext());
        }
        return color;
    }
}

