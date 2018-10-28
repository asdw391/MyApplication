package com.buaa.a.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.database.Cursor;


public class SearchActivity extends AppCompatActivity {

    private List<entry> entryList = new ArrayList<entry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //初始化条目
        initEntry();
        entryAdapter adapter=new entryAdapter(SearchActivity.this,R.layout.entry_item,entryList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    private  void initEntry(){
        String URL = "content://com.buaa.a.myapplication/entry";

        Uri entry = Uri.parse(URL);
        Cursor c = getContentResolver().query(entry, null, null, null, "date");

        if (c.moveToFirst()) {
            do{

                entry temp_entry=new entry(c.getString(c.getColumnIndex(EntryProvider._ID)),
                        c.getString(c.getColumnIndex( EntryProvider.DATE)),
                        c.getString(c.getColumnIndex( EntryProvider.MONEY)),
                        c.getString(c.getColumnIndex( EntryProvider.REMARKS)),
                        c.getString(c.getColumnIndex( EntryProvider.TYPE)));
                entryList.add(temp_entry);


            } while (c.moveToNext());
        }
    }
}
