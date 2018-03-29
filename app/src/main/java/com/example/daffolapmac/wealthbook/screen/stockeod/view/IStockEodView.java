package com.example.daffolapmac.wealthbook.screen.stockeod.view;

import android.support.annotation.StringRes;

import com.example.daffolapmac.wealthbook.common.ILoader;
import com.example.daffolapmac.wealthbook.screen.stockeod.model.StockEodRes;

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
