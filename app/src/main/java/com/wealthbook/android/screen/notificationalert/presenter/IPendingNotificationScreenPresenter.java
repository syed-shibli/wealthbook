package com.wealthbook.android.screen.notificationalert.presenter;

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
     * @param id
     */
    void reqLatestPortfolioReview(int id);
}
