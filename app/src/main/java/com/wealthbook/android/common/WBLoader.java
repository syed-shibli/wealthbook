package com.wealthbook.android.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.wealthbook.android.R;

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
