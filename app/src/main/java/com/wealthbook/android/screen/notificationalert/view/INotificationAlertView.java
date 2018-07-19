package com.wealthbook.android.screen.notificationalert.view;

import android.support.annotation.StringRes;

import com.wealthbook.android.common.ILoader;
import com.wealthbook.android.screen.notificationalert.model.LatestPortfolioReviewRes;

public interface INotificationAlertView extends ILoader {

    /**
     * Bind data to view
     */
    void bindAcceptDeclineViewModel();

    /**
     * To show error
     * @param error Error message
     */
    void onError(@StringRes int error);

    /**
     * Bind latest portfolio review view model
     * @param data View model
     */
    void bindLatestPortfolioReviewViewModel(LatestPortfolioReviewRes data);
}
