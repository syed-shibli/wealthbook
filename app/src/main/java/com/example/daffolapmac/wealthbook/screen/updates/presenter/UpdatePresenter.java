package com.example.daffolapmac.wealthbook.screen.updates.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.news.model.NewsRes;
import com.example.daffolapmac.wealthbook.screen.updates.manager.UpdateManager;
import com.example.daffolapmac.wealthbook.screen.updates.model.UpdateRes;
import com.example.daffolapmac.wealthbook.screen.updates.view.IUpdateView;

import java.util.List;

public class UpdatePresenter implements IUpdateScreenPresenter, IUpdateResponseReceiver {
    private IUpdateView mIUpdateView;
    private UpdateManager mUpdateManager;

    public UpdatePresenter(IUpdateView updateView, UpdateManager updateManager) {
        this.mIUpdateView = updateView;
        this.mUpdateManager = updateManager;
    }

    @Override
    public void fetchUpdateItemList(boolean isShowAppProgress) {
        if(isShowAppProgress) {
            mIUpdateView.showLoader();
        }
        mUpdateManager.reqUpdateList(this);
    }

    @Override
    public void disconnect() {
        mUpdateManager.cancel();
    }

    @Override
    public void onSuccess(List<UpdateRes> data) {
        mIUpdateView.hideLoader();
        mIUpdateView.refreshUpdateListView(data);
    }

    @Override
    public void onFailure(@NonNull ErrorResponse errorResponse) {
        mIUpdateView.hideLoader();
        mIUpdateView.showError(errorResponse.getErrorMessage());
    }
}
