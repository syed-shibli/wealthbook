package com.example.daffolapmac.wealthbook.screen.forgotpassword.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.common.BaseActivityImpl;
import com.example.daffolapmac.wealthbook.screen.forgotpassword.manager.ForgotPasswordManager;
import com.example.daffolapmac.wealthbook.screen.forgotpassword.presenter.ForgotPasswordPresenter;
import com.example.daffolapmac.wealthbook.screen.forgotpassword.presenter.IForgotPasswordScreenPresenter;
import com.example.daffolapmac.wealthbook.screen.login.view.LoginActivity;
import com.example.daffolapmac.wealthbook.widget.wbedittext.WBEditTextWithRounded;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends BaseActivityImpl implements IForgotPasswordView {

    @BindView(R.id.edt_email)
    WBEditTextWithRounded mEdtEmail;

    @BindView(R.id.edt_user_password)
    WBEditTextWithRounded mEdtUserPassword;

    @BindView(R.id.edt_user_confirm_password)
    WBEditTextWithRounded mEdtUserConfirmPassword;

    @BindView(R.id.edt_otp)
    WBEditTextWithRounded mEdtOTP;

    @BindView(R.id.btn_submit)
    Button mBtnChangePassword;

    private boolean isChangePasswordView = false;
    private IForgotPasswordScreenPresenter mForgotPasswordPresenter;
    private String emial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        setListener();
        mForgotPasswordPresenter = new ForgotPasswordPresenter(this, new ForgotPasswordManager());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mForgotPasswordPresenter.disconnect();
    }

    /**
     * Set listener for all field
     */
    private void setListener() {
        mEdtEmail.registerKeyListener();
        mEdtUserPassword.registerKeyListener();
        mEdtUserConfirmPassword.registerKeyListener();
        mEdtOTP.registerKeyListener();
    }

    @OnClick(R.id.btn_submit)
    public void btnSubmit() {
        if (!isChangePasswordView) {
            mForgotPasswordPresenter.sendEmailOTP(mEdtEmail.getValue());
            return;
        }
        mForgotPasswordPresenter.performChangePassword(emial, mEdtUserPassword.getValue(),
                mEdtUserConfirmPassword.getValue(), mEdtOTP.getValue());
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
    public void redirectToLogin() {
        launchActivity(this, LoginActivity.class);
        finish();
    }

    @Override
    public void redirectToChangePasswordView() {
        emial = mEdtEmail.getValue();
        showChnagePasswordview();
    }

    private void showChnagePasswordview() {
        isChangePasswordView = true;
        mBtnChangePassword.setText(R.string.txt_change_password);
        mEdtEmail.setVisibility(View.GONE);
        mEdtUserPassword.setVisibility(View.VISIBLE);
        mEdtUserConfirmPassword.setVisibility(View.VISIBLE);
        mEdtOTP.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(int error) {
        showSnackBar(error, this);
    }

    @Override
    public void emailInvalid(int error) {
        mEdtEmail.setError(getString(error));
        mEdtEmail.setErrorVisibility(true);
    }

    @Override
    public void passwordInvalid(int error) {
        mEdtUserPassword.setError(getString(error));
        mEdtUserPassword.setErrorVisibility(true);
    }

    @Override
    public void conformPasswordInvalid(int error) {
        mEdtUserConfirmPassword.setError(getString(error));
        mEdtUserConfirmPassword.setErrorVisibility(true);
    }

    @Override
    public void otpInvalid(int error) {
        mEdtOTP.setError(getString(error));
        mEdtOTP.setErrorVisibility(true);
    }
}
