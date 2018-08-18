package com.wealthbook.android.screen.forgotpassword.view;

import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wealthbook.android.R;
import com.wealthbook.android.common.BaseActivityImpl;
import com.wealthbook.android.common.ConformationRes;
import com.wealthbook.android.screen.forgotpassword.manager.ForgotPasswordManager;
import com.wealthbook.android.screen.forgotpassword.presenter.ForgotPasswordPresenter;
import com.wealthbook.android.screen.forgotpassword.presenter.IForgotPasswordScreenPresenter;
import com.wealthbook.android.screen.login.view.LoginActivity;
import com.wealthbook.android.widget.wbedittext.WBEditTextWithRounded;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends BaseActivityImpl implements IForgotPasswordView {

    private static final long DELAY_TIME = 3000;
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

    @BindView(R.id.txv_otp_sent)
    TextView txvLinkSent;

    private boolean isChangePasswordView = false;
    private IForgotPasswordScreenPresenter mForgotPasswordPresenter;
    private String emial;
    private Handler handler = new Handler();

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
        mEdtUserPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mEdtUserPassword.setError(getString(R.string.error_password));
        mEdtUserConfirmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mEdtUserConfirmPassword.setError(getString(R.string.error_password));
        mEdtOTP.setError(getString(R.string.error_otp));
        mEdtEmail.setError(getString(R.string.error_user_email));
        mEdtOTP.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
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
    public void redirectToLogin(ConformationRes data) {
        showSnackBar(data.getMessage(), this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                launchActivity(ForgotPasswordActivity.this, LoginActivity.class);
                finish();
            }
        }, DELAY_TIME);
    }

    @Override
    public void redirectToChangePasswordView(ConformationRes data) {
        emial = mEdtEmail.getValue();
        showError(data.getMessage());
        showChangePasswordView(data.getMessage());
    }

    private void showChangePasswordView(String title) {
        isChangePasswordView = true;
        txvLinkSent.setVisibility(View.VISIBLE);
        txvLinkSent.setText(title);
//        txvLinkSent.setText(getString(R.string.txt_forgot_link_sent, emial));
        mBtnChangePassword.setText(R.string.txt_change_password);
        mEdtEmail.setVisibility(View.GONE);
        mEdtUserPassword.setVisibility(View.VISIBLE);
        mEdtUserConfirmPassword.setVisibility(View.VISIBLE);
        mEdtOTP.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String error) {
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
