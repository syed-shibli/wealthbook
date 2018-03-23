package com.example.daffolapmac.wealthbook.screen.notificationalert.view;

import android.support.annotation.StringRes;

import com.example.daffolapmac.wealthbook.common.ILoader;
import com.example.daffolapmac.wealthbook.screen.notificationalert.model.LatestPortfolioReviewRes;

public interface INotificationAlertView extends ILoader {

    /**
     * Bind data to view
     */
    void bindViewModel();

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
