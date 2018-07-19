package com.wealthbook.android.screen.detailportfolio.presenter;

public interface IPortfolioDetailScreenPresenter {

    /**
     * Create req for get selected detail
     * @param id Selected ID
     * @param isAppLoader Want to show app loader
     */
    void reqToGetPortfolioDetail(int id, boolean isAppLoader);

    /**
     * Disconnect all on going req
     */
    void disconnect();
}
