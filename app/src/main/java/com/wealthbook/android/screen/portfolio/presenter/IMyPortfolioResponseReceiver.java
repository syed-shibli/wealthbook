package com.wealthbook.android.screen.portfolio.presenter;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.screen.portfolio.model.AllPortfolioRes;

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
