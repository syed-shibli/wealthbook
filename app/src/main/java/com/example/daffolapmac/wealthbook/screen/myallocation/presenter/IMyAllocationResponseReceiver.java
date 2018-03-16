package com.example.daffolapmac.wealthbook.screen.myallocation.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.myallocation.model.MyAllocationRes;

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
