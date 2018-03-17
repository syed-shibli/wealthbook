package com.example.daffolapmac.wealthbook.screen.portfolio.view;

import android.support.annotation.StringRes;

import com.example.daffolapmac.wealthbook.common.ILoader;
import com.example.daffolapmac.wealthbook.screen.portfolio.model.AllPortfolioRes;

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
