package com.example.daffolapmac.wealthbook.screen.notificationalert.presenter;

public interface IPendingNotificationScreenPresenter {

    /**
     * Create req for pending notification
     */
    void reqPendingNotification(int id, int statusID);

    /**
     * Disconnect all req
     */
    void disconnect();

    /**
     * Create req for getting latest portfolio review data
     */
    void reqLatestPortfolioReview();
}
