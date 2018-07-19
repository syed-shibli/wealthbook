package com.wealthbook.android.screen.login.presenter;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.screen.login.manager.LoginManager;
import com.wealthbook.android.screen.login.model.LoginRequest;
import com.wealthbook.android.screen.login.model.LoginRes;
import com.wealthbook.android.screen.login.model.LoginTroubleRes;
import com.wealthbook.android.screen.login.view.ILoginView;
import com.wealthbook.android.usersession.SessionManager;
import com.wealthbook.android.utils.AppConstant;
import com.wealthbook.android.utils.Utility;

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

    @Override
    public void disconnect() {
        mLoginManager.cancel();
    }

    @Override
    public void loginTroubleHelp() {
        mLoginView.showLoader();
        mLoginManager.reqLoginTrouble(this);
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
        if(SessionManager.getNewInstance().readSession().getUserType() == AppConstant.USER_TYPE_CLIENT) {
            mLoginView.redirectToClientHomeScreen();
        }else{
            mLoginView.redirectToAdviserHomeScreen();
        }
    }

    @Override
    public void onFailure(@NonNull ErrorResponse errorResponse) {
        mLoginView.hideLoader();
        mLoginView.showError(errorResponse.getErrorMessage());
    }

    @Override
    public void onLoginTroubleSuccess(LoginTroubleRes data) {
        mLoginView.hideLoader();
        mLoginView.redirectToGmailApp(data);
    }
}
