package com.wealthbook.android.screen.splash.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.wealthbook.android.R;
import com.wealthbook.android.common.BaseActivityImpl;
import com.wealthbook.android.notification.PushDataPayload;
import com.wealthbook.android.screen.advisoragreement.view.UserAgreementActivity;
import com.wealthbook.android.screen.home.view.HomeActivity;
import com.wealthbook.android.screen.login.view.LoginActivity;
import com.wealthbook.android.screen.splash.presenter.SplashPresenter;
import com.wealthbook.android.utils.AppConstant;

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
            Intent intent = new Intent(this, HomeActivity.class);
            if (getIntent().hasExtra(AppConstant.PAYLOAD)) {
                String payload = getIntent().getStringExtra(AppConstant.PAYLOAD);
                Gson gson = new Gson();
                PushDataPayload pushPayload = gson.fromJson(payload, PushDataPayload.class);
                switch (pushPayload.getNotification().getAlert().getNotificationType()) {
                    case "1":
                        intent.putExtra(AppConstant.ID, 223);
                        break;
                }
            }
            launchActivity(intent);
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
