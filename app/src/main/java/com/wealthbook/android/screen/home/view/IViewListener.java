package com.wealthbook.android.screen.home.view;

import android.support.annotation.StringRes;

import com.wealthbook.android.common.ILoader;

public interface IViewListener extends ILoader {

    /**
     * To bind count data to view
     * @param count
     */
    void bindPendingAlertCountToView(int count);

    /**
     * To show error response
     * @param error Error
     */
    void onError(String error);
}
