package com.buaa.a.myapplication;


import org.feezu.liuli.timeselector.TimeSelector;

import android.app.DialogFragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;


import android.app.AlertDialog;

import android.app.Dialog;

import android.app.DialogFragment;

import android.content.DialogInterface;

import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ViewDialogFragment extends DialogFragment {
    private TextView entry_money;
    private TextView entry_sort;
    private TextView entry_date;
    private TextView entry_remark;

    public interface LoginInputListener {
        void onLoginInputComplete(String entry_money, String entry_remark, String entry_date);
    }


    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog, null);
        entry_money = (TextView) view.findViewById(R.id.textView4);
        entry_sort = (TextView) view.findViewById(R.id.textView5);
        entry_date = (TextView) view.findViewById(R.id.textView6);
        entry_remark = (TextView) view.findViewById(R.id.textView7);
        entry_money.setText("金额:" + ListviewFragment.now_entry.getMONEY());
        entry_sort.setText("种类:" + ListviewFragment.now_entry.getTYPE());
        entry_date.setText("日期:" + ListviewFragment.now_entry.getDATE());
        entry_remark.setText("备注:" + ListviewFragment.now_entry.getREMARKS().substring(1));

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("删除",
                        new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String[] args = {};
                                Uri uri_ = Uri.parse(EntryProvider.URL + "/" + String.valueOf(ListviewFragment.now_entry.get_ID()));
                                int delete_num = getContext().getContentResolver().delete(uri_
                                        , "", args);
                                //ListviewFragment.entryList.remove(ListviewFragment.entry_index);
                                // ListviewFragment.adapter.notifyDataSetChanged();
                                Toast.makeText(getActivity(), "delete " + delete_num, Toast.LENGTH_SHORT).show();

                            }
                        }).setNegativeButton("取消", null);
        return builder.create();
    }


}









