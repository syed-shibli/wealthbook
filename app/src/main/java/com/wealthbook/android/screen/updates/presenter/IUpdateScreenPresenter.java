package com.wealthbook.android.screen.updates.presenter;

public interface IUpdateScreenPresenter {

    /**
     * Create get request for getting all update item list
     * @param isShowAppProgress Want to show app progress or not
     */
    void fetchUpdateItemList(boolean isShowAppProgress);

    /**
     * Disconnect all going request
     */
    void disconnect();

    /**
     * Create request for get update details of specific details
     * @param updateDetailsID ID
     */
    void getUpdateDetails(String updateDetailsID);
}
