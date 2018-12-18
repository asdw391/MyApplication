package com.buaa.a.myapplication;

import com.buaa.a.myapplication.ViewDialogFragment.LoginInputListener;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class activity_dialog extends Activity implements LoginInputListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    public void showViewDialogFragment(View view) {
        ViewDialogFragment viewDialogFragment = new ViewDialogFragment();
        viewDialogFragment.show(getFragmentManager(), "EditNameDialog");
    }


    @Override
    public void onLoginInputComplete(String entry_money, String entry_remark, String entry_date)

    {
        Toast.makeText(this, "金额：" + entry_money + ",时间:" + entry_date,

                Toast.LENGTH_SHORT).show();
    }

}

