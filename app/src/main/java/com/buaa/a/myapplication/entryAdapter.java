package com.buaa.a.myapplication;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
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
        convertView=view;
        TextView entry_content = (TextView) view.findViewById(R.id.entry_content);
        ImageView imageView=(ImageView)view.findViewById(R.id.imageView);

        entry_content.setText(now_entry.get_content());//为文本视图设置文本内容
       if(now_entry.getREMARKS().substring(0,1).equals("i")){//设置为黄色
            convertView.setBackgroundColor(Color.parseColor("#66CDAA"));
        }
        else{//设置为红色
            convertView.setBackgroundColor(Color.parseColor("#D4221A"));
        }
        String type = now_entry.getTYPE();
       if(type.equals("化妆")){
           imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.huazhuang));
       }else if(type.equals("工作")){
           imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),
                   R.drawable.jianpan));
       }else if(type.equals("零食")){
           imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),
                   R.drawable.lingshi));
       }else if(type.equals("衣服")){
           imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),
                   R.drawable.yifu));
       }else if(type.equals("父母")){
           imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),
                   R.drawable.jiazhang));
       }else if(type.equals("饮品")){
           imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),
                   R.drawable.yinpin));
       }else if(type.equals("游戏")){
           imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),
                   R.drawable.youxi));
       }else if(type.equals("娱乐")){
           imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),
                   R.drawable.yule));
       }else{
           imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),
                   R.drawable.jianpan));
       }
        return view;
    }
    /**

     * 删除按钮的监听接口

     */

    public interface onItemDeleteListener {

        void onDeleteClick(int i);

    }
    private onItemDeleteListener mOnItemDeleteListener;
    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {

        this.mOnItemDeleteListener = mOnItemDeleteListener;

    }
}
