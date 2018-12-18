package com.buaa.a.myapplication;


import android.os.Bundle;

import android.support.v4.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;

import android.view.View;

public class add_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_);
    }

    public void showBottomDialog(View view) {

        FragmentManager fm = getSupportFragmentManager();

        //BottomDialogFragment editNameDialog = new BottomDialogFragment();

        //editNameDialog.show(fm, "fragment_bottom_dialog");


    }
}
