package com.example.daffolapmac.wealthbook.screen.splash.view;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.daffolapmac.wealthbook.MainActivity;
import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.common.BaseActivityImpl;
import com.example.daffolapmac.wealthbook.screen.home.view.HomeActivity;
import com.example.daffolapmac.wealthbook.screen.splash.presenter.SplashPresenter;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivityImpl implements ISplashView {

    private SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mSplashPresenter = new SplashPresenter(this);
        mSplashPresenter.onSplashVisible();
    }

    @Override
    public void onSplashTimeOut() {
        if (!isFinishing() && !isDestroyed()) {
            // TODO Check user session for a;lready login
            startActivity(MainActivity.class);
            finish();
        }
    }
}
