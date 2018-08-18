package com.wealthbook.android.screen.updates.view;

import android.support.annotation.StringRes;

import com.wealthbook.android.common.ILoader;
import com.wealthbook.android.screen.updates.model.UpdateRes;

import java.util.List;

public interface IUpdateView extends ILoader {

    /**
     * List of news which is getting from server bind to view
     *
     * @param data News response data
     */
    void refreshUpdateListView(List<UpdateRes> data);

    /**
     * Show error
     *
     * @param error Error message
     */
    void showError(String error);
}
