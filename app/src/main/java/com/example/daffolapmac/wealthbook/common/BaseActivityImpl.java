package com.example.daffolapmac.wealthbook.common;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BaseActivityImpl extends AppCompatActivity implements UIBase{

    private WBLoader mLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    /**
     * Start activity
     * @param type Type of activity class
     */
    protected void startActivity(Class type) {
        Intent activity = new Intent(this, type);
        startActivity(activity);
    }

    @Override
    public void showProgress() {
        if (mLoader == null) {
            mLoader = new WBLoader(this);
            mLoader.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mLoader.setCancelable(false);
        }

        if (mLoader != null && !mLoader.isShowing()) {
            mLoader.show();
        }
    }

    @Override
    public void hideProgress() {
        if (mLoader != null && mLoader.isShowing()) {
            mLoader.dismiss();
            mLoader = null;
        }
    }
}
