package com.wealthbook.android.screen.news.view;

import android.support.annotation.StringRes;

import com.wealthbook.android.common.ILoader;
import com.wealthbook.android.screen.news.model.NewsRes;

public interface INewsViewListener extends ILoader {

    /**
     * List of news which is getting from server bind to view
     *
     * @param data News response data
     */
    void bindNewsListToView(NewsRes data);

    /**
     * Show error
     *
     * @param error Error message
     */
    void showError(@StringRes int error);
}
