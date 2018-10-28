package com.buaa.a.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
public class entryAdapter extends ArrayAdapter{
    private final int resourceId;
    public entryAdapter(Context context, int textViewResourceId, List<entry> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        entry now_entry=(entry)getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView entry_content = (TextView) view.findViewById(R.id.entry_content);
        entry_content.setText(now_entry.get_content());//为文本视图设置文本内容
        return view;
    }

}
