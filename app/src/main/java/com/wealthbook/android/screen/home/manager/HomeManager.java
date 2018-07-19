package com.wealthbook.android.screen.home.manager;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.api.ResponseCallback;
import com.wealthbook.android.api.ResponseWrapper;
import com.wealthbook.android.api.RetrofitClient;
import com.wealthbook.android.screen.home.model.PendingAlertCountRes;
import com.wealthbook.android.screen.home.presenter.IHomeResponseReceiver;
import com.wealthbook.android.usersession.SessionManager;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class HomeManager {
    private IHomeResponseReceiver mPendingAlertReceiver;
    private Call<PendingAlertCountRes> mPendingAlertCountCall;

    /**
     * Get all pending alert count
     * @param receiver Response receiver
     */
    public void getPendingAlert(IHomeResponseReceiver receiver) {
        this.mPendingAlertReceiver = receiver;
        String token = SessionManager.getNewInstance().readSession().getmToken();
        mPendingAlertCountCall = RetrofitClient.getApiService().pendingAlertCount(token);
        mPendingAlertCountCall.enqueue(new ResponseWrapper<>(mPendingAlertCountCallback));
    }

    private ResponseCallback<PendingAlertCountRes> mPendingAlertCountCallback = new ResponseCallback<PendingAlertCountRes>() {
        @Override
        public void onSuccess(@NonNull PendingAlertCountRes data) {
            mPendingAlertReceiver.onSuccess(data.getCount());
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mPendingAlertReceiver.onFailure(errorResponse);
        }
    };

    /**
     * Cancel all request which are under process
     */
    public void cancel() {
        if (mPendingAlertCountCall != null && mPendingAlertCountCall.isExecuted()) {
            mPendingAlertCountCall.cancel();
        }
    }
}
