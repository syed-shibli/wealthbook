package com.example.daffolapmac.wealthbook.screen.myallocation.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.myallocation.manager.MyAllocationManager;
import com.example.daffolapmac.wealthbook.screen.myallocation.model.MyAllocationRes;
import com.example.daffolapmac.wealthbook.screen.myallocation.view.IMyAllocationView;

public class MyAllocationPresenter implements IMyAllocationScreenPresenter, IMyAllocationResponseReceiver {
    private IMyAllocationView mMyAllocationView;
    private MyAllocationManager mMyAllocationManager;

    public MyAllocationPresenter(IMyAllocationView allocationView, MyAllocationManager manager) {
        this.mMyAllocationView = allocationView;
        this.mMyAllocationManager = manager;
    }

    @Override
    public void reqAllAllocation() {
        mMyAllocationView.showLoader();
        mMyAllocationManager.getAllAllocation(this);
    }

    @Override
    public void disconnect() {
        mMyAllocationManager.cancel();
    }

    @Override
    public void onSuccess(MyAllocationRes data) {
        mMyAllocationView.hideLoader();
        mMyAllocationView.bindView(data);
    }

    @Override
    public void onFailure(@NonNull ErrorResponse errorResponse) {
        mMyAllocationView.hideLoader();
        mMyAllocationView.onError(errorResponse.getErrorMessage());
    }
}
