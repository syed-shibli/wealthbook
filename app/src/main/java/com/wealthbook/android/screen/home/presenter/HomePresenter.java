package com.wealthbook.android.screen.home.presenter;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.screen.home.manager.HomeManager;
import com.wealthbook.android.screen.home.view.IViewListener;

public class HomePresenter implements IHomeScreenPresenter, IHomeResponseReceiver {
    private IViewListener mViewListener;
    private HomeManager mHomeManager;

    public HomePresenter(IViewListener mViewListener, HomeManager mHomeManager) {
        this.mViewListener = mViewListener;
        this.mHomeManager = mHomeManager;
    }

    @Override
    public void reqAlertCount() {
        mViewListener.showLoader();
        mHomeManager.getPendingAlert(this);
    }

    @Override
    public void disconnect() {
        mHomeManager.cancel();
    }

    @Override
    public void onSuccess(int count) {
        mViewListener.hideLoader();
        mViewListener.bindPendingAlertCountToView(count);
    }

    @Override
    public void onFailure(@NonNull ErrorResponse errorResponse) {
        mViewListener.hideLoader();
        mViewListener.onError(errorResponse.getErrorMessage());
    }
}
