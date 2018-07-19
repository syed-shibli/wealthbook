package com.wealthbook.android.screen.myallocation.manager;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.api.ResponseCallback;
import com.wealthbook.android.api.ResponseWrapper;
import com.wealthbook.android.api.RetrofitClient;
import com.wealthbook.android.screen.myallocation.model.MyAllocationRes;
import com.wealthbook.android.screen.myallocation.presenter.IMyAllocationResponseReceiver;
import com.wealthbook.android.usersession.SessionManager;

import retrofit2.Call;

public class MyAllocationManager {

    private Call<MyAllocationRes> mAllAllocationCall;
    private IMyAllocationResponseReceiver mAllocationReceiver;

    /**
     * Send req for get all allocation data
     * @param receiver Response receiver
     */
    public void getAllAllocation(IMyAllocationResponseReceiver receiver) {
        this.mAllocationReceiver = receiver;
        String token = SessionManager.getNewInstance().readSession().getmToken();
        mAllAllocationCall = RetrofitClient.getApiService().allAllocation(token);
        mAllAllocationCall.enqueue(new ResponseWrapper<MyAllocationRes>(mAllAllocationCallback));
    }

    private ResponseCallback<MyAllocationRes> mAllAllocationCallback = new ResponseCallback<MyAllocationRes>() {
        @Override
        public void onSuccess(@NonNull MyAllocationRes data) {
            mAllocationReceiver.onSuccess(data);
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mAllocationReceiver.onFailure(errorResponse);
        }
    };

    /**
     * Cancel all ongoing request if view destroy
     */
    public void cancel() {
        if (mAllAllocationCall != null && mAllAllocationCall.isExecuted()) {
            mAllAllocationCall.cancel();
        }
    }
}
