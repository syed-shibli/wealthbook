package com.example.daffolapmac.wealthbook.screen.updates.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.updates.manager.UpdateManager;
import com.example.daffolapmac.wealthbook.screen.updates.model.UpdateRes;
import com.example.daffolapmac.wealthbook.screen.updates.view.IUpdateDetailsView;
import com.example.daffolapmac.wealthbook.screen.updates.view.IUpdateView;

import java.util.List;

public class UpdatePresenter implements IUpdateScreenPresenter, IUpdateResponseReceiver {
    private IUpdateDetailsView mUpdateDetailsView;
    private IUpdateView mIUpdateView;
    private UpdateManager mUpdateManager;

    public UpdatePresenter(IUpdateView updateView, UpdateManager updateManager) {
        this.mIUpdateView = updateView;
        this.mUpdateManager = updateManager;
    }

    public UpdatePresenter(IUpdateDetailsView detailsView, UpdateManager updateManager) {
        this.mUpdateDetailsView = detailsView;
        this.mUpdateManager = updateManager;
    }

    @Override
    public void fetchUpdateItemList(boolean isShowAppProgress) {
        if (isShowAppProgress) {
            mIUpdateView.showLoader();
        }
        mUpdateManager.reqUpdateList(this);
    }

    @Override
    public void disconnect() {
        mUpdateManager.cancel();
    }

    @Override
    public void getUpdateDetails(String updateDetailsID) {
        mUpdateDetailsView.showLoader();
        mUpdateManager.reqGetUpdateDetails(this, updateDetailsID);
    }

    @Override
    public void onSuccess(List<UpdateRes> data) {
        mIUpdateView.hideLoader();
        mIUpdateView.refreshUpdateListView(data);
    }

    @Override
    public void onFailure(@NonNull ErrorResponse errorResponse) {
        if (mUpdateDetailsView != null) {
            mUpdateDetailsView.hideLoader();
            mUpdateDetailsView.showError(errorResponse.getErrorMessage());
            return;
        }
        if (mIUpdateView != null) {
            mIUpdateView.hideLoader();
            mIUpdateView.showError(errorResponse.getErrorMessage());
        }
    }

    @Override
    public void onGetDetailSuccess(UpdateRes data) {
        mUpdateDetailsView.hideLoader();
        mUpdateDetailsView.refreshUpdateDetailsListView(data);
    }
}
