package com.example.nitishsingh.colourdemo;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nitishsingh.colourdemo.Database.DBHelper;
import com.example.nitishsingh.colourdemo.Model.ColorData;
import com.example.nitishsingh.colourdemo.utiles.CommonUtilities;
import com.example.nitishsingh.colourdemo.utiles.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ColorData> dataList;
    DBHelper dbHelper;

    String[] color_names = {"White","yellow","red","purple"," green"};



    String[] color_code ={"#FFFFFF","#FFFF00"," #FF0000","#800080","#008000"};

    ListView list;
    ArrayAdapter<String> myAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = CommonUtilities.getDBObject(this);
        list = (ListView) findViewById(R.id.list);

        int count = dbHelper.getFullCount(Constants.COLOR_LIST, null);
        if (count == 0) {
            insertCountryList();
        }


        dataList = (List<ColorData>) dbHelper.getAllColors();

        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < dataList.size(); i++) {
            listTitle.add(i, dataList.get(i).getColorName());
        }
        myAdaptor = new ArrayAdapter<String>(this, R.layout.row, R.id.listText, listTitle);
        myAdaptor.notifyDataSetChanged();
        list.setAdapter(myAdaptor);
    }

    public void insertCountryList() {

        for (int i = 0; i < color_names.length; i++) {
            ContentValues cv = new ContentValues();
            cv.put(Constants.COLOR_CODE, color_code[i]);
            cv.put(Constants.COLOR_NAME, color_names[i]);

            dbHelper.insertContentVals(Constants.COLOR_LIST, cv);
        }
    }
}

