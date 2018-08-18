package com.wealthbook.android.screen.forgotpassword.presenter;

import android.support.annotation.NonNull;

import com.wealthbook.android.R;
import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.common.ConformationRes;
import com.wealthbook.android.screen.forgotpassword.manager.ForgotPasswordManager;
import com.wealthbook.android.screen.forgotpassword.model.ForgotPasswordReq;
import com.wealthbook.android.screen.forgotpassword.view.IForgotPasswordView;
import com.wealthbook.android.utils.Utility;

public class ForgotPasswordPresenter implements IForgotPasswordScreenPresenter, IForgotPasswordResponseReceiver {
    private IForgotPasswordView mIForgotPasswordView;
    private ForgotPasswordManager forgotPasswordManager;

    public ForgotPasswordPresenter(IForgotPasswordView passwordView, ForgotPasswordManager forgotPasswordManager) {
        this.mIForgotPasswordView = passwordView;
        this.forgotPasswordManager = forgotPasswordManager;
    }

    @Override
    public void sendEmailOTP(String email) {
        if (isEmailValid(email)) {
            mIForgotPasswordView.showLoader();
            forgotPasswordManager.reqEmailOTP(this, email);
        }
    }

    @Override
    public void performChangePassword(String email, String password, String confirmPassword, String otp) {
        if (isValid(password, confirmPassword, otp)) {
            mIForgotPasswordView.showLoader();
            ForgotPasswordReq req = new ForgotPasswordReq(email, otp, password);
            forgotPasswordManager.reqPasswordChange(this, req);
        }
    }

    @Override
    public void disconnect() {
        forgotPasswordManager.cancel();
    }

    @Override
    public void onOTPSendSuccess(ConformationRes data) {
        mIForgotPasswordView.hideLoader();
        mIForgotPasswordView.redirectToChangePasswordView(data);
    }

    @Override
    public void onChangePasswordSuccess(ConformationRes data) {
        mIForgotPasswordView.hideLoader();
        mIForgotPasswordView.redirectToLogin(data);
    }

    @Override
    public void onFailure(@NonNull ErrorResponse errorResponse) {
        mIForgotPasswordView.hideLoader();
        mIForgotPasswordView.showError(errorResponse.getErrorMessage());
    }

    /**
     * To verify entered email is valid or not
     * @param email Email string
     * @return Return true/false
     */
    private boolean isEmailValid(String email) {
        if (!Utility.isEmailValid(email)) {
            mIForgotPasswordView.emailInvalid(R.string.error_user_email);
            return false;
        }
        return true;
    }

    /**
     * To check email/password is valid or not
     * @param pass        Email string
     * @param confirmPass Password string
     * @param otp         OTP string
     * @return True/false
     */
    private boolean isValid(String pass, String confirmPass, String otp) {
        if (!Utility.isPasswordValid(pass)) {
            mIForgotPasswordView.passwordInvalid(R.string.error_password);
            return false;
        }
        if (!Utility.isPasswordValid(confirmPass) || !pass.contentEquals(confirmPass)) {
            mIForgotPasswordView.conformPasswordInvalid(R.string.error_confirm_password);
            return false;
        }
        if (otp.isEmpty()) {
            mIForgotPasswordView.passwordInvalid(R.string.error_empty_top);
            return false;
        }
        return true;
    }
}
