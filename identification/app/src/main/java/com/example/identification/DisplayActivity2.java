package com.example.identification;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;


public class DisplayActivity2 extends AppCompatActivity {
    ListView listview;
    SQLiteDatabase sqLiteDatabase;
    DBHelper dbController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2);
        listview = (ListView) findViewById(R.id.lstView);
        int[] id = { R.id.nameslist, R.id.ageslist };
        String[] table = new String[] {"names", "ages"};
        dbController = new DBHelper(this);
        dbController.getReadableDatabase();
        Cursor c = dbController.getNamesAndAges();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_template, c, table, id, 0);
        listview.setAdapter(adapter);
    }
}