package com.example.daffolapmac.wealthbook.screen.news.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.news.model.NewsRes;

public interface INewsResponseReceiver {

    /**
     * Call when api success
     *
     * @param data News response data
     */
    void onSuccess(NewsRes data);

    /**
     * Call when api get failure
     *
     * @param errorResponse Server error response
     */
    void onFailure(@NonNull ErrorResponse errorResponse);

}
