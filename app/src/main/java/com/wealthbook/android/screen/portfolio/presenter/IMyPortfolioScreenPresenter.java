package com.wealthbook.android.screen.portfolio.presenter;

public interface IMyPortfolioScreenPresenter {

    /**
     * Req for get all portfolio data
     * @param isAppLoader Want to show app loader
     */
    void reqAllPortfolio(boolean isAppLoader);

    /**
     * Disconnect all on going request
     */
    void disconnect();
}
