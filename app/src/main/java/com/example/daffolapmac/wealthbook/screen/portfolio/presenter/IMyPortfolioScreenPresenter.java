package com.example.daffolapmac.wealthbook.screen.portfolio.presenter;

public interface IMyPortfolioScreenPresenter {

    /**
     * Req for get all portfolio data
     */
    void reqAllPortfolio();

    /**
     * Disconnect all on going request
     */
    void disconnect();
}
