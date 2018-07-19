package com.wealthbook.android.screen.stockeod.view;

import android.support.annotation.StringRes;

import com.wealthbook.android.common.ILoader;

import java.util.List;

public interface IStockEodView extends ILoader {

    /**
     * Bind stock data to view
     * @param data Stock data model
     */
    void bindStockEodValueToView(List data);

    /**
     * To show error message to user which is coming from server
     * @param error Error message
     */
    void onError(@StringRes int error);
}
