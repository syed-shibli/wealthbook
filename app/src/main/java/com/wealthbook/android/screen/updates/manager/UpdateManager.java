package com.wealthbook.android.screen.updates.manager;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.api.ResponseCallback;
import com.wealthbook.android.api.ResponseWrapper;
import com.wealthbook.android.api.RetrofitClient;
import com.wealthbook.android.screen.updates.model.UpdateRes;
import com.wealthbook.android.screen.updates.presenter.IUpdateResponseReceiver;
import com.wealthbook.android.usersession.SessionManager;

import java.util.List;

import retrofit2.Call;

public class UpdateManager {

    private IUpdateResponseReceiver mResponseReceiver;
    private Call<List<UpdateRes>> mUpdateListCall;
    private Call<UpdateRes> mSetectedUpdateDetailsCall;

    /**
     * Send request to fetch all update list
     * @param receiver Response receiver
     */
    public void reqUpdateList(IUpdateResponseReceiver receiver) {
        this.mResponseReceiver = receiver;
        String token = SessionManager.getNewInstance().readSession().getToken();
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
     * Send req for get selected update detail
     * @param receiver Response receiver
     * @param id       ID
     */
    public void reqGetUpdateDetails(IUpdateResponseReceiver receiver, String id) {
        this.mResponseReceiver = receiver;
        String token = SessionManager.getNewInstance().readSession().getToken();
        mSetectedUpdateDetailsCall = RetrofitClient.getApiService().getSelectedUpdateDetails(id, token);
        mSetectedUpdateDetailsCall.enqueue(new ResponseWrapper<UpdateRes>(mSelectedUpdateDetailsCallback));
    }

    private ResponseCallback<UpdateRes> mSelectedUpdateDetailsCallback = new ResponseCallback<UpdateRes>() {
        @Override
        public void onSuccess(@NonNull UpdateRes data) {
            mResponseReceiver.onGetDetailSuccess(data);
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
        if (mSetectedUpdateDetailsCall != null && mSetectedUpdateDetailsCall.isExecuted()) {
            mSetectedUpdateDetailsCall.cancel();
        }
    }
}
