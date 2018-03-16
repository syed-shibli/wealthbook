package com.example.daffolapmac.wealthbook.screen.myallocation.presenter;

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
