package com.wealthbook.android.screen.splash.view;

import android.os.Bundle;
import android.view.WindowManager;

import com.wealthbook.android.R;
import com.wealthbook.android.common.BaseActivityImpl;
import com.wealthbook.android.screen.advisoragreement.view.UserAgreementActivity;
import com.wealthbook.android.screen.home.view.HomeActivity;
import com.wealthbook.android.screen.login.view.LoginActivity;
import com.wealthbook.android.screen.splash.presenter.SplashPresenter;

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
    public void redirectToHomeScreen() {
        if (!isFinishing() && !isDestroyed()) {
            launchActivity(this, HomeActivity.class);
            finish();
        }
    }

    @Override
    public void redirectToAdviserAgreementScreen() {
        if (!isFinishing() && !isDestroyed()) {
            launchActivity(this, UserAgreementActivity.class);
            finish();
        }
    }

    @Override
    public void redirectToLoginScreen() {
        if (!isFinishing() && !isDestroyed()) {
            launchActivity(this, LoginActivity.class);
            finish();
        }
    }
}
