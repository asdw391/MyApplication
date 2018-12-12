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
import android.widget.ImageView;
import android.widget.TextView;
//import cn.uprogrammer.contentprovider.R;

import com.heima.tabview.library.TabView;
import com.heima.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    /*test push*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<TabViewChild> tabViewChildList=new ArrayList<>();
        TabViewChild tabViewChild01=new TabViewChild(R.mipmap.icon,R.mipmap.icon,"明细",  ListviewFragment.newInstance("这里是具体条目","123"));
        TabViewChild tabViewChild02=new TabViewChild(R.mipmap.icon,R.mipmap.icon,"图表",  testFragment.newInstance("这里是统计表格"));
        TabViewChild tabViewChild03=new TabViewChild(R.mipmap.icon,R.mipmap.icon,"记账",  add_Fragment.newInstance("添加"));
        //TabViewChild tabViewChild04=new TabViewChild(R.mipmap.icon,R.mipmap.icon,"发现",testFragment.newInstance("发现"));
        TabViewChild tabViewChild05=new TabViewChild(R.mipmap.icon,R.mipmap.icon,"我们",  testFragment.newInstance("关于我们"));
        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabViewChildList.add(tabViewChild03);
        //tabViewChildList.add(tabViewChild04);
        tabViewChildList.add(tabViewChild05);

        TabView tabView= (TabView) findViewById(R.id.tabView);
        tabView.setTabViewChild(tabViewChildList,getSupportFragmentManager());
        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int  position, ImageView currentImageIcon, TextView currentTextView) {
                //Toast.makeText(getApplicationContext(),"position:"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*public void onClickAddName(View view) {
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
        Intent intent = new Intent(this, add_Activity.class);
        startActivity(intent);
    }*/






}
