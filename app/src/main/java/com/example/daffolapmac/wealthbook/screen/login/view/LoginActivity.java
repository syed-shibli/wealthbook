package com.example.daffolapmac.wealthbook.screen.login.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.common.BaseActivityImpl;
import com.example.daffolapmac.wealthbook.screen.advisoragreement.view.UserAgreementActivity;
import com.example.daffolapmac.wealthbook.screen.forgotpassword.view.ForgotPasswordActivity;
import com.example.daffolapmac.wealthbook.screen.home.view.HomeActivity;
import com.example.daffolapmac.wealthbook.screen.login.manager.LoginManager;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginTroubleRes;
import com.example.daffolapmac.wealthbook.screen.login.presenter.ILoginScreenPresenter;
import com.example.daffolapmac.wealthbook.screen.login.presenter.LoginPresenter;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;
import com.example.daffolapmac.wealthbook.usersession.UserSessionData;
import com.example.daffolapmac.wealthbook.widget.wbedittext.WBEditTextWithRounded;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivityImpl implements ILoginView {

    @BindView(R.id.edt_username)
    WBEditTextWithRounded mEdtEmailName;

    @BindView(R.id.edt_user_password)
    WBEditTextWithRounded mEdtUserPassword;

    private ILoginScreenPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setListener();
        mLoginPresenter = new LoginPresenter(this, new LoginManager());
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
        mEdtEmailName.registerKeyListener();
        mEdtUserPassword.registerKeyListener();
        mEdtEmailName.setError(getString(R.string.error_user_email));
        mEdtUserPassword.setError(getString(R.string.error_password));
    }

    @OnClick(R.id.btn_login)
    public void doLogin() {
        mLoginPresenter.performLogin(mEdtEmailName.getValue(), mEdtUserPassword.getValue());
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
        mEdtEmailName.setErrorVisibility(true);
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
    public void showError(int errorMessage) {
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
            if(sendIntent.resolveActivity(getPackageManager()) != null) {
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
}
