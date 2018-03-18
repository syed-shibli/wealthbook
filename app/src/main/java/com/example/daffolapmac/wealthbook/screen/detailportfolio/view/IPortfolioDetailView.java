package com.example.daffolapmac.wealthbook.screen.detailportfolio.view;

import android.support.annotation.StringRes;

import com.example.daffolapmac.wealthbook.common.ILoader;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.model.PortfolioDetailRes;

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
