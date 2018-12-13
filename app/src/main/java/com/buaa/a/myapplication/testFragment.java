package com.buaa.a.myapplication;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.lixs.charts.LineChartView;
import com.lixs.charts.PieChartView;
import com.lixs.charts.RadarChartView;

import java.util.ArrayList;
import java.util.List;


public class testFragment extends Fragment {
    private int yellowColor = Color.argb(255, 253, 197, 53);
    private int greenColor = Color.argb(255, 27, 147, 76);
    private int redColor = Color.argb(255, 211, 57, 53);
    private int blueColor = Color.argb(255, 76, 139, 245);
    private int blueColor2=Color.argb(255,0,40,245);
    View view;
    private RadarChartView radarChartView;
    private LineChartView lineChartView;
    private PieChartView pieChartView;
    public static testFragment newInstance(String text){
        testFragment fragmentCommon=new testFragment();
        Bundle bundle=new Bundle();
        bundle.putString("text",text);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }
    @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_test2,container,false);
        //radarChartView=(RadarChartView)view.findViewById(R.id.radarView);
        //lineChartView=(LineChartView)view.findViewById(R.id.lineView);
        pieChartView=(PieChartView)view.findViewById(R.id.pieView);
        //initlineView();
        initPieDatas();
        //initRadarDatas();
        //textView= (TextView) view.findViewById(R.id.textView);
        //textView.setText(getArguments().getString("text"));
        return view;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        initPieDatas();
        super.onHiddenChanged(hidden);

    }
    private void initlineView(){
        List<Double> datas = new ArrayList<>();
        datas.add(100d);
        datas.add(20d);
//        datas.add(40d);
//        datas.add(50d);
//        datas.add(50d);
//        datas.add(60d);
//        datas.add(60d);
//        datas.add(80d);
//        datas.add(80d);

        List<String> description = new ArrayList<>();
        description.add("one");
        description.add("two");
//        description.add("three");
//        description.add("four");
//        description.add("five");
//        description.add("six");
//        description.add("six");
//        description.add("six");
//        description.add("six");

        lineChartView.setDatas(datas, description);
    }
    private void initPieDatas() {

        List<Float> mRatios = new ArrayList<>();

        List<String> mDescription = new ArrayList<>();

        List<Integer> mArcColors = new ArrayList<>();

        String URL = "content://com.buaa.a.myapplication/entry";
        float comein_num=0f,food_num=0f,clothes_num=0f,game_num=0f,others_num=0f;

        List<entry> entryList = new ArrayList<entry>();
        Uri entry = Uri.parse(URL);
        Cursor c = getActivity().getContentResolver().query(entry, null, null, null, "date");
        if (c.moveToFirst()) {
            do{

                entry temp_entry=new entry(c.getString(c.getColumnIndex(EntryProvider._ID)),
                        c.getString(c.getColumnIndex( EntryProvider.DATE)),
                        c.getString(c.getColumnIndex( EntryProvider.MONEY)),
                        c.getString(c.getColumnIndex( EntryProvider.REMARKS)),
                        c.getString(c.getColumnIndex( EntryProvider.TYPE)));
                entryList.add(temp_entry);
                String type = temp_entry.getTYPE();
                if(type.equals("化妆")){
                    clothes_num+=Float.parseFloat(temp_entry.getMONEY()) ;
                }else if(type.equals("工作")){
                    comein_num+=Float.parseFloat(temp_entry.getMONEY());
                }else if(type.equals("零食")){
                    food_num+=Float.parseFloat(temp_entry.getMONEY());
                }else if(type.equals("衣服")){
                    clothes_num+=Float.parseFloat(temp_entry.getMONEY());
                }else if(type.equals("父母")){
                    comein_num+=Float.parseFloat(temp_entry.getMONEY());
                }else if(type.equals("饮品")){
                    food_num+=Float.parseFloat(temp_entry.getMONEY());
                }else if(type.equals("游戏")){
                    game_num+=Float.parseFloat(temp_entry.getMONEY());
                }else if(type.equals("娱乐")){
                    game_num+=Float.parseFloat(temp_entry.getMONEY());
                }else{
                    others_num+=Float.parseFloat(temp_entry.getMONEY());
                }

            } while (c.moveToNext());
        }
        float sum=comein_num+food_num+clothes_num+game_num+others_num;
        if(sum!=0f){
            if(comein_num>0.009) {
                mRatios.add(comein_num / sum);
                mArcColors.add(blueColor);
                mDescription.add("收入");
            }
            if(food_num>0.009) {
                mRatios.add(food_num / sum);
                mArcColors.add(redColor);
                mDescription.add("食物");
            }
            if(game_num>0.009){
                mRatios.add(game_num/sum);
                mArcColors.add(yellowColor);
                mDescription.add("娱乐");
            }
            if(clothes_num>0.009) {
                mRatios.add(clothes_num / sum);
                mArcColors.add(greenColor);
                mDescription.add("装扮");
            }
            if(1-comein_num/sum-food_num/sum-game_num/sum-clothes_num/sum>0.009) {


                mRatios.add(1 - comein_num / sum - food_num / sum - game_num / sum - clothes_num / sum);
                mArcColors.add(blueColor2);
                mDescription.add("其他");
            }

        }
        else{
            mRatios.add(1f);
            mDescription.add("没有记录");
        }
        //点击动画开启
        pieChartView.setCanClickAnimation(true);

        pieChartView.setDatas(mRatios, mArcColors, mDescription);

    }
    private  void initEntry(){
        String URL = "content://com.buaa.a.myapplication/entry";

        Uri entry = Uri.parse(URL);
        Cursor c = getActivity().getContentResolver().query(entry, null, null, null, "date");
        //entryList.clear();
        if (c.moveToFirst()) {
            do{

                entry temp_entry=new entry(c.getString(c.getColumnIndex(EntryProvider._ID)),
                        c.getString(c.getColumnIndex( EntryProvider.DATE)),
                        c.getString(c.getColumnIndex( EntryProvider.MONEY)),
                        c.getString(c.getColumnIndex( EntryProvider.REMARKS)),
                        c.getString(c.getColumnIndex( EntryProvider.TYPE)));
                //entryList.add(temp_entry);


            } while (c.moveToNext());
        }
    }
    /*private void initRadarDatas() {
        List<Float> datas = new ArrayList<>();
        List<String> description = new ArrayList<>();

        datas.add(0.5f);
        datas.add(0.3f);
        datas.add(0.3f);
        datas.add(0.8f);
        datas.add(1f);
        datas.add(0.4f);

        description.add("one");
        description.add("two");
        description.add("three");
        description.add("four");
        description.add("five");
        description.add("six");

        //点击动画开启
        radarChartView.setCanClickAnimation(true);
        radarChartView.setDatas(datas);
        radarChartView.setPolygonNumbers(6);
        radarChartView.setDescriptions(description);
    }*/
}