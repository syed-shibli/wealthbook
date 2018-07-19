package com.wealthbook.android.screen.notificationalert.presenter;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.screen.notificationalert.model.LatestPortfolioReviewRes;

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
