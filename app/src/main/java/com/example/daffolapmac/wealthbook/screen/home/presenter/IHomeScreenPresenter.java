package com.example.daffolapmac.wealthbook.screen.home.presenter;

public interface IHomeScreenPresenter {

    /**
     * Create req to get alert count
     */
    void reqAlertCount();

    /**
     * Disconnect api req when view destroy
     */
    void disconnect();
}
