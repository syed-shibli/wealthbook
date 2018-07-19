package com.wealthbook.android.screen.myallocation.presenter;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.screen.myallocation.model.MyAllocationRes;


public interface IMyAllocationResponseReceiver {

    /**
     * Call when api success
     *
     * @param data News response data
     */
    void onSuccess(MyAllocationRes data);

    /**
     * Call when api get failure
     *
     * @param errorResponse Server error response
     */
    void onFailure(@NonNull ErrorResponse errorResponse);
}
