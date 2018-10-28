package com.buaa.a.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
//import cn.uprogrammer.contentprovider.R;

public class MainActivity extends AppCompatActivity {
    /*test push*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();

        values.put(EntryProvider.DATE,
                ((EditText)findViewById(R.id.editText2)).getText().toString());

        values.put(EntryProvider.MONEY,
                ((EditText)findViewById(R.id.editText3)).getText().toString());

        values.put(EntryProvider.REMARKS,
                ((EditText)findViewById(R.id.editText5)).getText().toString());

        values.put(EntryProvider.TYPE,
                ((EditText)findViewById(R.id.editText4)).getText().toString());


        Uri uri = getContentResolver().insert(
                EntryProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {

        // Retrieve student records
        String URL = "content://com.buaa.a.myapplication/entry";

        Uri entry = Uri.parse(URL);
        Cursor c = getContentResolver().query(entry, null, null, null, "date");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(EntryProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( EntryProvider.DATE)) +
                                ", " + c.getString(c.getColumnIndex( EntryProvider.MONEY)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }

    public void onClick_goto_search(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
