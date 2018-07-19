package com.wealthbook.android.screen.notificationalert.presenter;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.screen.notificationalert.manager.PendingNotificationManager;
import com.wealthbook.android.screen.notificationalert.model.LatestPortfolioReviewRes;
import com.wealthbook.android.screen.notificationalert.model.UpdatePortfolioReq;
import com.wealthbook.android.screen.notificationalert.view.INotificationAlertView;

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
        UpdatePortfolioReq req = new UpdatePortfolioReq();
        req.setId(id);
        req.setStatusID(statusID);
        mPendingNotificationManager.getPendingNotification(this, req);
    }

    @Override
    public void disconnect() {
        mPendingNotificationManager.cancel();
    }

    @Override
    public void reqLatestPortfolioReview(int id) {
        mViewListener.showLoader();
        mPendingNotificationManager.getLatestPortfolioReviewData(this, id);
    }

    @Override
    public void onSuccess() {
        mViewListener.hideLoader();
        mViewListener.bindAcceptDeclineViewModel();
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
