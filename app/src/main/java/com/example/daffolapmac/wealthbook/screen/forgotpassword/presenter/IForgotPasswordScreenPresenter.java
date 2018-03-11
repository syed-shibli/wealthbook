package com.example.daffolapmac.wealthbook.screen.forgotpassword.presenter;

public interface IForgotPasswordScreenPresenter {

    /**
     * Create request for sending OTP to given email
     * @param email Email string
     */
    void sendEmailOTP(String email);

    /**
     * After OTP received then perform to change password process
     * with password and OTP
     * @param email           Email string
     * @param password        Password string
     * @param confirmPassword Confirm password string
     * @param otp             OTP string
     */
    void performChangePassword(String email, String password, String confirmPassword, String otp);

    /**
     * Disconnect all api call
     */
    void disconnect();
}
