package com.example.daffolapmac.wealthbook.screen.notificationalert.presenter;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.notificationalert.manager.PendingNotificationManager;
import com.example.daffolapmac.wealthbook.screen.notificationalert.model.LatestPortfolioReviewRes;
import com.example.daffolapmac.wealthbook.screen.notificationalert.view.INotificationAlertView;

public class PendingNotificationPresenter implements IPendingNotificationScreenPresenter, IPendingNotificationResponseReceiver {
    private INotificationAlertView mViewListener;
    private PendingNotificationManager mPendingNotificationManager;

    public PendingNotificationPresenter(INotificationAlertView mViewListener, PendingNotificationManager mPendingNotificationManager) {
        this.mViewListener = mViewListener;
        this.mPendingNotificationManager = mPendingNotificationManager;
    }

    @Override
    public void reqPendingNotification(int id, int statusID) {
        mViewListener.showLoader();
        mPendingNotificationManager.getPendingNotification(this, id, statusID);
    }

    @Override
    public void disconnect() {
        mPendingNotificationManager.cancel();
    }

    @Override
    public void reqLatestPortfolioReview() {
        mViewListener.showLoader();
        mPendingNotificationManager.getLatestPortfolioReviewData(this);
    }

    @Override
    public void onSuccess() {
        mViewListener.hideLoader();
        mViewListener.bindViewModel();
    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        mViewListener.hideLoader();
        mViewListener.onError(errorResponse.getErrorMessage());
    }

    @Override
    public void onLatestPortfolioReviewSuccess(LatestPortfolioReviewRes data) {
        mViewListener.hideLoader();
        mViewListener.bindLatestPortfolioReviewViewModel(data);
    }
}
