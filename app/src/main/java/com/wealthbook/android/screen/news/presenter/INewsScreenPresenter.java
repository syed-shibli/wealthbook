package com.wealthbook.android.screen.news.presenter;

public interface INewsScreenPresenter {

    /**
     * To fetch news request
     * @param isShowAppProgress
     */
    void fetchNewsReq(boolean isShowAppProgress);

    /**
     * Disconnect api call
     */
    void disconnect();
}
