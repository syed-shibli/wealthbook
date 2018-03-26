package com.example.daffolapmac.wealthbook.screen.notificationalert.manager;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.api.ResponseCallback;
import com.example.daffolapmac.wealthbook.api.ResponseWrapper;
import com.example.daffolapmac.wealthbook.api.RetrofitClient;
import com.example.daffolapmac.wealthbook.screen.notificationalert.model.LatestPortfolioReviewRes;
import com.example.daffolapmac.wealthbook.screen.notificationalert.model.UpdatePortfolioReq;
import com.example.daffolapmac.wealthbook.screen.notificationalert.presenter.IPendingNotificationResponseReceiver;
import com.example.daffolapmac.wealthbook.screen.pendingalert.model.PendingAlertRes;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;

import retrofit2.Call;

public class PendingNotificationManager {

    private Call<PendingAlertRes> mUpdatePendingAlertCall;
    private IPendingNotificationResponseReceiver mResponseReceiver;
    private Call<LatestPortfolioReviewRes> mLatestPortfolioReviewCall;

    /**
     * Send req to update pending notification alert (Accept/Decline)
     * @param receiver Response receiver
     * @param id       ID
     * @param statusID Status id
     */
    public void getPendingNotification(IPendingNotificationResponseReceiver receiver, UpdatePortfolioReq req) {
        this.mResponseReceiver = receiver;
        String token = SessionManager.getNewInstance().readSession().getmToken();
        mUpdatePendingAlertCall = RetrofitClient.getApiService().updatePortfolioNotification(token, req);
        mUpdatePendingAlertCall.enqueue(new ResponseWrapper<PendingAlertRes>(mUpdatePendingAlertCallback));
    }

    private ResponseCallback<PendingAlertRes> mUpdatePendingAlertCallback = new ResponseCallback<PendingAlertRes>() {
        @Override
        public void onSuccess(@NonNull PendingAlertRes data) {
            mResponseReceiver.onSuccess();
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mResponseReceiver.onFailure(errorResponse);
        }
    };

    /**
     * Get latest portfolio review data
     * @param receiver Response receiver
     * @param id
     */
    public void getLatestPortfolioReviewData(IPendingNotificationResponseReceiver receiver, int id) {
        this.mResponseReceiver = receiver;
        String token = SessionManager.getNewInstance().readSession().getmToken();
        if (id == -1) {
            mLatestPortfolioReviewCall = RetrofitClient.getApiService().latestPortfolioReview(token);
        } else {
            mLatestPortfolioReviewCall = RetrofitClient.getApiService().pendingPortfolioReview(token, id);
        }
        mLatestPortfolioReviewCall.enqueue(new ResponseWrapper<LatestPortfolioReviewRes>(mLatestPortfolioReviewCallback));
    }

    private ResponseCallback<LatestPortfolioReviewRes> mLatestPortfolioReviewCallback = new ResponseCallback<LatestPortfolioReviewRes>() {
        @Override
        public void onSuccess(@NonNull LatestPortfolioReviewRes data) {
            mResponseReceiver.onLatestPortfolioReviewSuccess(data);
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mResponseReceiver.onFailure(errorResponse);
        }
    };

    /**
     * Cancel all on going request
     */
    public void cancel() {
        if (mUpdatePendingAlertCall != null && mUpdatePendingAlertCall.isExecuted()) {
            mUpdatePendingAlertCall.cancel();
        }
        if (mLatestPortfolioReviewCall != null && mLatestPortfolioReviewCall.isExecuted()) {
            mLatestPortfolioReviewCall.cancel();
        }
    }
}
