package com.example.daffolapmac.wealthbook.screen.notificationalert.presenter;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.notificationalert.model.LatestPortfolioReviewRes;

public interface IPendingNotificationResponseReceiver {

    /**
     * Call when server get success
     */
    void onSuccess();

    /**
     * Call when server get error
     * @param errorResponse Error response
     */
    void onFailure(ErrorResponse errorResponse);

    /**
     * Call when pending portfolio review success
     * @param data Latest portfolio alert review model
     */
    void onLatestPortfolioReviewSuccess(LatestPortfolioReviewRes data);
}
