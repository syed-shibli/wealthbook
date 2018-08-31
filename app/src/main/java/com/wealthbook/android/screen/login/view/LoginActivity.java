package com.wealthbook.android.screen.login.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;

import com.wealthbook.android.R;
import com.wealthbook.android.common.BaseActivityImpl;
import com.wealthbook.android.screen.advisoragreement.view.UserAgreementActivity;
import com.wealthbook.android.screen.forgotpassword.view.ForgotPasswordActivity;
import com.wealthbook.android.screen.home.view.HomeActivity;
import com.wealthbook.android.screen.login.manager.LoginManager;
import com.wealthbook.android.screen.login.model.LoginTroubleRes;
import com.wealthbook.android.screen.login.presenter.ILoginScreenPresenter;
import com.wealthbook.android.screen.login.presenter.LoginPresenter;
import com.wealthbook.android.usersession.SessionManager;
import com.wealthbook.android.usersession.UserSessionData;
import com.wealthbook.android.utils.Utility;
import com.wealthbook.android.widget.wbedittext.WBEditTextWithRounded;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.internal.Util;

public class LoginActivity extends BaseActivityImpl implements ILoginView, WBEditTextWithRounded.EditTextClickCallback {

    @BindView(R.id.edt_username)
    WBEditTextWithRounded mEdtEmail;

    @BindView(R.id.edt_user_password)
    WBEditTextWithRounded mEdtUserPassword;

    private ILoginScreenPresenter mLoginPresenter;
    private String email;
    private String maskEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setListener();
        mLoginPresenter = new LoginPresenter(this, new LoginManager());
        email = SessionManager.getNewInstance().getKeyValue(SessionManager.USER_EMAIL_KEY);
        setMask(email, true);
        mEdtEmail.setClickListener(this);
    }

    private void setMask(String email, boolean mask) {
        if (email != null) {
            if (mask) {
                try {
                    maskEmail = Utility.maskString(email, 5, email.length(), '*');
                    mEdtEmail.setValue(maskEmail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                mEdtEmail.setValue(email);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.disconnect();
    }

    /**
     * Set listener for all field
     */
    private void setListener() {
        mEdtEmail.setError(getString(R.string.error_user_email));
        mEdtUserPassword.setError(getString(R.string.error_password));
        mEdtEmail.setInputType(InputType.TYPE_CLASS_TEXT);
        mEdtUserPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mEdtEmail.registerKeyListener();
        mEdtUserPassword.registerKeyListener();
    }

    @OnClick(R.id.btn_login)
    public void doLogin() {
        if (email != null) {
            if (!mEdtEmail.getValue().equalsIgnoreCase(maskEmail)) {
                email = mEdtEmail.getValue();
            }
        } else {
            email = mEdtEmail.getValue();
        }
        mLoginPresenter.performLogin(email, mEdtUserPassword.getValue());
    }

    @OnClick(R.id.btn_change_password)
    public void doResetPassword() {
        launchActivity(this, ForgotPasswordActivity.class);
    }

    @OnClick(R.id.btn_trouble_login)
    public void doLoginTrouble() {
        mLoginPresenter.loginTroubleHelp();
    }

    @Override
    public void emailInvalid() {
        mEdtEmail.setErrorVisibility(true);
    }

    @Override
    public void passwordInvalid() {
        mEdtUserPassword.setErrorVisibility(true);
    }

    @Override
    public void redirectToClientHomeScreen() {
        launchActivity(this, HomeActivity.class);
        finish();
    }

    @Override
    public void redirectToAdviserHomeScreen() {
        UserSessionData session = SessionManager.getNewInstance().readSession();
        if (session.getUserAgreement() != null && !session.getUserAgreement().isAgreementStatus()) {
            launchActivity(this, UserAgreementActivity.class);
            finish();
        } else {
            redirectToClientHomeScreen();
        }
    }

    @Override
    public void showError(String errorMessage) {
        showSnackBar(errorMessage, this);
    }

    @Override
    public void redirectToGmailApp(LoginTroubleRes data) {
        if (data != null) {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("mailto:"));
            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{data.getSupportEmail()});
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, data.getSupportTitle());
            startActivity(sendIntent);
            if (sendIntent.resolveActivity(getPackageManager()) != null) {
            }
        }
    }

    @Override
    public void showLoader() {
        showProgress();
    }

    @Override
    public void hideLoader() {
        hideProgress();
    }

    @Override
    public void onClick() {
        setMask(email, false);
    }
}
