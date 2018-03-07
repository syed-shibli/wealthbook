package com.example.daffolapmac.wealthbook.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BaseActivityImpl extends AppCompatActivity {

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
}
