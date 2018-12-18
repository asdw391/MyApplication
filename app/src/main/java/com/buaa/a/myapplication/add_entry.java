package com.buaa.a.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.feezu.liuli.timeselector.TimeSelector;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.String.*;

public class add_entry extends AppCompatActivity {
    private String date;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        //tv = (TextView)findViewById(R.id.textView2);
    }


    public void onClick_show_time(View view) {

        TimeSelector timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {//处理返回时间
                //Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
                handle_time(time);
            }
        }, "2015-11-22 17:34", "2019-12-1 15:20");

        timeSelector.setMode(TimeSelector.MODE.YMD);
        timeSelector.show();
    }

    public void handle_time(String time) {
        //Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
        //Context context = getApplicationContext();
        //TextView tv = (TextView)findViewById(R.id.textView);
        if (time == null)
            ;
        else
            tv.setText(time);
        //tv.setText(time.toCharArray(),1,4);
        date = time;
    }


}
