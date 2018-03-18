package com.example.daffolapmac.wealthbook.screen.detailportfolio.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.model.PortfolioDetailRes;

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
