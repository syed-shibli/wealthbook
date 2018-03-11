package com.example.daffolapmac.wealthbook.screen.updates.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.news.model.NewsRes;
import com.example.daffolapmac.wealthbook.screen.updates.model.UpdateRes;

import java.util.List;

public interface IUpdateResponseReceiver {

    /**
     * Call when api success
     *
     * @param data News response data
     */
    void onSuccess(List<UpdateRes> data);

    /**
     * Call when api get failure
     *
     * @param errorResponse Server error response
     */
    void onFailure(@NonNull ErrorResponse errorResponse);

}
