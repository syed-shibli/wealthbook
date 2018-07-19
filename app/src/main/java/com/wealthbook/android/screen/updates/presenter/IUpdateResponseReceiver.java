package com.wealthbook.android.screen.updates.presenter;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.screen.updates.model.UpdateRes;

import java.util.List;

public interface IUpdateResponseReceiver {

    /**
     * Call when api success
     * @param data News response data
     */
    void onSuccess(List<UpdateRes> data);

    /**
     * Call when api get failure
     * @param errorResponse Server error response
     */
    void onFailure(@NonNull ErrorResponse errorResponse);

    /**
     * Call when details success
     * @param data Details model
     */
    void onGetDetailSuccess(UpdateRes data);
}
