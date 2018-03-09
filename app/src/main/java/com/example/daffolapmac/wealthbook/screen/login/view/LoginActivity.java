package com.example.daffolapmac.wealthbook.screen.login.view;

import android.os.Bundle;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.common.BaseActivityImpl;
import com.example.daffolapmac.wealthbook.screen.home.view.HomeActivity;
import com.example.daffolapmac.wealthbook.screen.login.manager.LoginManager;
import com.example.daffolapmac.wealthbook.screen.login.presenter.ILoginScreenPresenter;
import com.example.daffolapmac.wealthbook.screen.login.presenter.LoginPresenter;
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

    /**
     * Set listener for all field
     */
    private void setListener() {
        mEdtEmailName.registerKeyListener();
        mEdtUserPassword.registerKeyListener();
        mEdtEmailName.setError(getString(R.string.error_user_name));
        mEdtUserPassword.setError(getString(R.string.error_password));
    }

    @OnClick(R.id.btn_login)
    public void doLogin() {
        mLoginPresenter.performLogin(mEdtEmailName.getValue(), mEdtUserPassword.getValue());
    }

    @OnClick(R.id.btn_reset_password)
    public void doResetPassword() {
        showSnackBar(R.string.txt_reset_password, this);
    }

    @OnClick(R.id.btn_trouble_login)
    public void doLoginTrouble() {
        showSnackBar(R.string.txt_trouble_logging_in, this);
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
    public void redirectToHomeScreen() {
        launchActivity(this, HomeActivity.class);
        finish();
    }

    @Override
    public void showError(int errorMessage) {
        showSnackBar(errorMessage, this);
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
