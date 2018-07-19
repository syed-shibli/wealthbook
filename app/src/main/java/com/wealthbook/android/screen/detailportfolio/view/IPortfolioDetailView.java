package com.wealthbook.android.screen.detailportfolio.view;

import android.support.annotation.StringRes;

import com.wealthbook.android.common.ILoader;
import com.wealthbook.android.screen.detailportfolio.model.PortfolioDetailRes;

public interface IPortfolioDetailView extends ILoader {

    /**
     * Bind data to view
     * @param data
     */
    void bindDataToView(PortfolioDetailRes data);

    /**
     * To show error
     * @param error Error message
     */
    void onError(@StringRes int error);
}
