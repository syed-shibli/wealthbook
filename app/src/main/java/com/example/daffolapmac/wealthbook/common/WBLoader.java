package com.example.daffolapmac.wealthbook.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.example.daffolapmac.wealthbook.R;

public class WBLoader extends AlertDialog {

    WBLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    public void show() {
        super.show();
        setContentView(R.layout.fragment_loader);
    }
}
