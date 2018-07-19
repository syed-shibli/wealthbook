package com.wealthbook.android.screen.portfolio.view;

import android.support.annotation.StringRes;

import com.wealthbook.android.common.ILoader;
import com.wealthbook.android.screen.portfolio.model.AllPortfolioRes;

public interface IPortfolioFragmentView extends ILoader {

    /**
     * Portfolio data model
     * @param data Response model
     */
    void bindDataToView(AllPortfolioRes data);

    /**
     * To show error message which is coming from server
     * @param error Error message
     */
    void onError(@StringRes int error);
}
