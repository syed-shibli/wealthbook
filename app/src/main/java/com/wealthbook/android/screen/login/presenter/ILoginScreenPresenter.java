package com.wealthbook.android.screen.login.presenter;

public interface ILoginScreenPresenter {

    /**
     * To perform login
     * @param email Email string
     * @param pass  Pass string
     */
    void performLogin(String email, String pass);

    /**
     * Disconnect api call
     */
    void disconnect();

    /**
     * Get help when user in login trouble mode
     */
    void loginTroubleHelp();
}
