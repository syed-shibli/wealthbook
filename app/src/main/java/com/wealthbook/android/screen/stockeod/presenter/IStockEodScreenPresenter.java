package com.wealthbook.android.screen.stockeod.presenter;

public interface IStockEodScreenPresenter {

    /**
     * Create req to get specific stock eod value
     * @param id     Stock id
     * @param ticker Stock ticker value
     */
    void getSelectedStockEod(int id, String ticker);

    /**
     * Disconnect all api call when view destroy
     */
    void disconnect();
}
