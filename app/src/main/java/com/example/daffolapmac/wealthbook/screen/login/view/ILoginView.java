package com.example.daffolapmac.wealthbook.screen.login.view;

import android.support.annotation.StringRes;

import com.example.daffolapmac.wealthbook.common.ILoader;

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
     * To redirect to home screen if login success
     */
    void redirectToHomeScreen();

    /**
     * To show error message
     *
     * @param errorMessage Error message
     */
    void showError(@StringRes int errorMessage);
}
