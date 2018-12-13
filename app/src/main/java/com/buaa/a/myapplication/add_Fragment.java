package com.buaa.a.myapplication;
import android.content.ContentValues;
import android.content.*;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import org.feezu.liuli.timeselector.TimeSelector;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.feezu.liuli.timeselector.TimeSelector;

public class add_Fragment extends Fragment {
    View my_veiw;
    private  String  date;
    private Button date_button;
    private Spinner spinner;
    private Switch switch1;
    private String now_choose="i";
    public static add_Fragment newInstance(String text){
        add_Fragment addFragment=new add_Fragment();
        Bundle bundle=new Bundle();
        bundle.putString("text",text);
        addFragment.setArguments(bundle);
        return addFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_,container,false);
        my_veiw=view;

        date_button = (Button) view.findViewById(R.id.date_button);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = new Date(System.currentTimeMillis());
        if(date_button!=null) {
            date_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TimeSelector timeSelector = new TimeSelector(getActivity(), new TimeSelector.ResultHandler() {
                        @Override
                        public void handle(String time) {//处理返回时间
                            //Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
                            handle_time(time);
                        }
                    }, "2015-11-22 17:34", simpleDateFormat.format(date)+ " 15:20");

                    timeSelector.setMode(TimeSelector.MODE.YMD);
                    timeSelector.show();
                }
            });
        }


        date_button.setText(simpleDateFormat.format(date));
        //////////////////////////////
        Button comfirm_button=(Button) view.findViewById(R.id.confirmbutton);
        comfirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values = new ContentValues();


                values.put(EntryProvider.DATE,
                        ((Button)my_veiw.findViewById(R.id.date_button)).getText().toString());


                values.put(EntryProvider.MONEY,
                        ((EditText)my_veiw.findViewById(R.id.金额)).getText().toString());


                values.put(EntryProvider.REMARKS,
                        now_choose+((EditText)my_veiw.findViewById(R.id.备注)).getText().toString());

                values.put(EntryProvider.TYPE,
                        ((Spinner)my_veiw.findViewById(R.id.spinner)).getSelectedItem().toString());

                //((Spinner)my_veiw.findViewById(R.id.spinner)).getPrompt().toString()
                Uri uri = getContext().getContentResolver().insert(
                        EntryProvider.CONTENT_URI, values);
            }
        });

        spinner = (Spinner) view.findViewById(R.id.spinner);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                          getActivity(), android.R.layout.simple_spinner_item,
                              getData());
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
             public void onItemSelected(AdapterView<?> parent, View view,
                     int position, long id) {
                                 // 在选中之后触发
                                 /*Toast.makeText(getActivity(),
                                                parent.getItemAtPosition(position).toString(),
                                                 Toast.LENGTH_SHORT).show();*/
                             }
              @Override
             public void onNothingSelected(AdapterView<?> parent) {

                             }
         });

        switch1=(Switch) view.findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getActivity(),
                            "支出",
                            Toast.LENGTH_SHORT).show();
                    now_choose="o";
                }else {
                    Toast.makeText(getActivity(),
                            "收入",
                            Toast.LENGTH_SHORT).show();
                    now_choose="i";
                }
            }
        });

        Log.i("create","line 60");

        //textView= (TextView) view.findViewById(R.id.textView4);
        //textView.setText(getArguments().getString("text"));
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), add_entry.class);
                startActivity(intent);
            }
        });*/
    }

    public void _onClick_show_time(View view){

        TimeSelector timeSelector = new TimeSelector(getActivity(), new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {//处理返回时间
                //Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
                handle_time(time);
            }
        }, "2015-11-22 17:34", "2019-12-1 15:20");

        timeSelector.setMode(TimeSelector.MODE.YMD);
        timeSelector.show();
    }

    public void handle_time(String time)
    {
        //Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
        //Context context = getApplicationContext();
        //TextView tv = (TextView)findViewById(R.id.textView);
        time=time.split(" ")[0];
        if(time==null)
            ;
        else
            date_button.setText(time);
        //tv.setText(time.toCharArray(),1,4);

    }


    public void onClickAddName(View view) {
        // Add a new student record

        ContentValues values = new ContentValues();


        values.put(EntryProvider.DATE,
                ((Button)my_veiw.findViewById(R.id.date_button)).getText().toString());


        values.put(EntryProvider.MONEY,
                ((EditText)my_veiw.findViewById(R.id.金额)).getText().toString());


        values.put(EntryProvider.REMARKS,
                ((EditText)my_veiw.findViewById(R.id.备注)).getText().toString());

        values.put(EntryProvider.TYPE,
                "er");

        //((Spinner)my_veiw.findViewById(R.id.spinner)).getPrompt().toString()
        Uri uri = getContext().getContentResolver().insert(
                EntryProvider.CONTENT_URI, values);

        //Toast.makeText(getBaseContext(),
                //uri.toString(), Toast.LENGTH_LONG).show();
    }
    private List<String> getData() {

             List<String> dataList = new ArrayList<String>();
             dataList.add("吃饭");
             dataList.add("睡觉");
             dataList.add("打");
             dataList.add("pfz");
             return dataList;
         }

}
