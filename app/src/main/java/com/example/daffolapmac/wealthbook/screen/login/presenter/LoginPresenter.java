package com.example.daffolapmac.wealthbook.screen.login.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.login.manager.LoginManager;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginRequest;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginRes;
import com.example.daffolapmac.wealthbook.screen.login.view.ILoginView;
import com.example.daffolapmac.wealthbook.utils.Utility;

public class LoginPresenter implements ILoginScreenPresenter, ILoginResponseReceiver {

    private ILoginView mLoginView;
    private LoginManager mLoginManager;

    public LoginPresenter(ILoginView iLoginView, LoginManager loginManager) {
        this.mLoginView = iLoginView;
        this.mLoginManager = loginManager;
    }

    @Override
    public void performLogin(String email, String pass) {
        if (isValid(email, pass)) {
            LoginRequest request = new LoginRequest(email, pass);
            mLoginView.showLoader();
            mLoginManager.sendLoginReq(this, request);
        }
    }

    /**
     * To check email/password is valid or not
     *
     * @param email Email string
     * @param pass  Password string
     * @return True/false
     */
    private boolean isValid(String email, String pass) {
        if (!Utility.isEmailValid(email)) {
            mLoginView.emailInvalid();
            return false;
        }
        if (!Utility.isPasswordValid(pass)) {
            mLoginView.passwordInvalid();
            return false;
        }
        return true;
    }

    @Override
    public void onSuccess(LoginRes data) {
        mLoginView.hideLoader();
        mLoginView.redirectToHomeScreen();
    }

    @Override
    public void onFailure(@NonNull ErrorResponse errorResponse) {
        mLoginView.hideLoader();
        mLoginView.showError(errorResponse.getErrorMessage());
    }
}
