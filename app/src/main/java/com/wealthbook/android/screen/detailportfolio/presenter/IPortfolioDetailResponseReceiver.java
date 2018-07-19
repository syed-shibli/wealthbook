package com.wealthbook.android.screen.detailportfolio.presenter;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.screen.detailportfolio.model.PortfolioDetailRes;

public interface IPortfolioDetailResponseReceiver {

    /**
     * Call when api success
     * @param data Response data
     */
    void onSuccess(PortfolioDetailRes data);

    /**
     * Call when api get failure
     * @param errorResponse Server error response
     */
    void onFailure(@NonNull ErrorResponse errorResponse);

}
