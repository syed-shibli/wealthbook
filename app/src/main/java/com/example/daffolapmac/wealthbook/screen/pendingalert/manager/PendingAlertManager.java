package com.example.daffolapmac.wealthbook.screen.pendingalert.manager;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.api.ResponseCallback;
import com.example.daffolapmac.wealthbook.api.ResponseWrapper;
import com.example.daffolapmac.wealthbook.api.RetrofitClient;
import com.example.daffolapmac.wealthbook.screen.pendingalert.model.PendingAlertRes;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;

import retrofit2.Call;

public class PendingAlertManager {

    private IPendingAlertResponseReceiver mPendingAlertReceiver;
    private Call<PendingAlertRes> mPendingAlertCall;

    /**
     * Send req to get all pending alert
     * @param responseReceiver Response receiver
     */
    public void getPendingAlertList(IPendingAlertResponseReceiver responseReceiver) {
        this.mPendingAlertReceiver = responseReceiver;
        String token = SessionManager.getNewInstance().readSession().getmToken();
        mPendingAlertCall = RetrofitClient.getApiService().getPendingAlert(token);
        mPendingAlertCall.enqueue(new ResponseWrapper<PendingAlertRes>(mPendingAlertCallback));
    }

    private ResponseCallback<PendingAlertRes> mPendingAlertCallback = new ResponseCallback<PendingAlertRes>() {
        @Override
        public void onSuccess(@NonNull PendingAlertRes data) {
            mPendingAlertReceiver.onSuccess(data);
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mPendingAlertReceiver.onFailure(errorResponse);
        }
    };

    /**
     * Cancel all on going request
     */
    public void cancel() {
        if (mPendingAlertCall != null && mPendingAlertCall.isExecuted()) {
            mPendingAlertCall.cancel();
        }
    }
}
