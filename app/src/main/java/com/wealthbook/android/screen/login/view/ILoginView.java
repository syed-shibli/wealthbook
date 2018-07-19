package com.wealthbook.android.screen.login.view;

import android.support.annotation.StringRes;

import com.wealthbook.android.common.ILoader;
import com.wealthbook.android.screen.login.model.LoginTroubleRes;

public interface ILoginView extends ILoader {

    /**
     * To show email invalid message
     */
    void emailInvalid();

    /**
     * To show password invalid message
     */
    void passwordInvalid();

    /**
     * To redirect to client home screen if login success
     */
    void redirectToClientHomeScreen();

    /**
     * To redirect to adviser home screen if login success
     */
    void redirectToAdviserHomeScreen();

    /**
     * To show error message
     * @param errorMessage Error message
     */
    void showError(@StringRes int errorMessage);

    /**
     * Redirect user to Gmail app for send email
     * @param data Email data
     */
    void redirectToGmailApp(LoginTroubleRes data);
}
