package com.wealthbook.android.screen.splash.view;

public interface ISplashView {

    /**
     * Called by presenter when user logged in
     */
    void redirectToHomeScreen();

    /**
     * Redirect user to agreement screen
     */
    void redirectToAdviserAgreementScreen();

    /**
     * Called by presenter when user not logged in
     */
    void redirectToLoginScreen();
}
