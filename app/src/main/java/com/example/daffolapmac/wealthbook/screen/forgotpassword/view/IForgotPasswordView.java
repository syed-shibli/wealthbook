package com.example.daffolapmac.wealthbook.screen.forgotpassword.view;

import android.support.annotation.StringRes;

import com.example.daffolapmac.wealthbook.common.ILoader;

public interface IForgotPasswordView extends ILoader {

    /**
     * If password has been changed successful then
     * redirect user to login screen
     */
    void redirectToLogin();

    /**
     * If user email is exist and OTP send to that email
     * then redirect to user to change their password using OTP verification
     */
    void redirectToChangePasswordView();

    /**
     * To show server error
     *
     * @param error Error message
     */
    void showError(@StringRes int error);

    /**
     * To show email invalid message
     *
     * @param error Error message
     */
    void emailInvalid(@StringRes int error);

    /**
     * To show password invalid message
     * * @param error Error message
     */
    void passwordInvalid(@StringRes int error);

    /**
     * To show confirm password does not math to password
     * * @param error Error message
     */
    void conformPasswordInvalid(@StringRes int error);

    /**
     * To show OTP related error
     * * @param error Error message
     */
    void otpInvalid(@StringRes int error);
}
