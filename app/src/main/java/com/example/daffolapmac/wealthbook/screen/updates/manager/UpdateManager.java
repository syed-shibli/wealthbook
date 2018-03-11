package com.example.daffolapmac.wealthbook.screen.updates.manager;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.api.ResponseCallback;
import com.example.daffolapmac.wealthbook.api.ResponseWrapper;
import com.example.daffolapmac.wealthbook.api.RetrofitClient;
import com.example.daffolapmac.wealthbook.screen.updates.model.UpdateRes;
import com.example.daffolapmac.wealthbook.screen.updates.presenter.IUpdateResponseReceiver;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;

import java.util.List;

import retrofit2.Call;

public class UpdateManager {

    private IUpdateResponseReceiver mResponseReceiver;
    private Call<List<UpdateRes>> mUpdateListCall;

    /**
     * Send request to fetch all update list
     * @param receiver Response receiver
     */
    public void reqUpdateList(IUpdateResponseReceiver receiver) {
        this.mResponseReceiver = receiver;
        String token = SessionManager.getNewInstance().readSession().getmToken();
        mUpdateListCall = RetrofitClient.getApiService().updateList(token);
        mUpdateListCall.enqueue(new ResponseWrapper<List<UpdateRes>>(mUpdateListCallback));
    }

    private ResponseCallback<List<UpdateRes>> mUpdateListCallback = new ResponseCallback<List<UpdateRes>>() {
        @Override
        public void onSuccess(@NonNull List<UpdateRes> data) {
            mResponseReceiver.onSuccess(data);
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
        if (mUpdateListCall != null && mUpdateListCall.isExecuted()) {
            mUpdateListCall.cancel();
        }
    }
}
