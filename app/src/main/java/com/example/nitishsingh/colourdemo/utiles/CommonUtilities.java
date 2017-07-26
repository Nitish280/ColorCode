package com.example.nitishsingh.colourdemo.utiles;

import android.content.Context;

import com.example.nitishsingh.colourdemo.Database.DBHelper;

/**
 * Created by Nitish Singh on 26-07-2017.
 */

public class CommonUtilities {
    public static DBHelper getDBObject(Context mContext) {
        DBHelper dbHelper = DBHelper.getInstance(mContext);
        return dbHelper;
    }
}
