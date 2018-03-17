package com.example.daffolapmac.wealthbook.screen.portfolio.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.portfolio.model.AllPortfolioRes;

public interface IMyPortfolioResponseReceiver {

    /**
     * Call when api success
     * @param data Response data
     */
    void onSuccess(AllPortfolioRes data);

    /**
     * Call when api get failure
     * @param errorResponse Server error response
     */
    void onFailure(@NonNull ErrorResponse errorResponse);

}
