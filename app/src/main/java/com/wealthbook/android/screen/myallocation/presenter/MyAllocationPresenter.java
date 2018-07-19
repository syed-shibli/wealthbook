package com.wealthbook.android.screen.myallocation.presenter;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.screen.myallocation.manager.MyAllocationManager;
import com.wealthbook.android.screen.myallocation.model.MyAllocationRes;
import com.wealthbook.android.screen.myallocation.view.IMyAllocationView;

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
