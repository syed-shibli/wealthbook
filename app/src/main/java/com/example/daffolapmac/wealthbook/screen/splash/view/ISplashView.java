package com.example.daffolapmac.wealthbook.screen.splash.view;

public interface ISplashView {

    /**
     * Called by presenter when user logged in
     */
    void redirectToHomeScreen();

    /**
     * Called by presenter when user not logged in
     */
    void redirectToLoginScreen();
}
