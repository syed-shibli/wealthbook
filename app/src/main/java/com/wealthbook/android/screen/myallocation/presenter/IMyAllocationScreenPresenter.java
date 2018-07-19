package com.wealthbook.android.screen.myallocation.presenter;

public interface IMyAllocationScreenPresenter {

    /**
     * Request for all allocation
     */
    void reqAllAllocation();

    /**
     * Disconnect all on going request
     */
    void disconnect();
}
