package com.example.daffolapmac.wealthbook.common;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.enums.SnackbarType;

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

    @Override
    public void showSnackBar(int message, AppCompatActivity context) {
        Snackbar.with(getApplicationContext()).type(SnackbarType.MULTI_LINE).text(message).show(context);
    }

    @Override
    public void showSnackBar(String message, AppCompatActivity context) {
        Snackbar.with(getApplicationContext()).type(SnackbarType.MULTI_LINE).text(message).show(context);
    }

    @Override
    public <T> void launchActivity(Activity _context, Class<T> cls) {
        if (_context != null) {
            Intent intent = new Intent(_context, cls);
            startActivity(intent);
        }
    }

}
