package com.example.daffolapmac.wealthbook.screen.updates.view;

import android.support.annotation.StringRes;

import com.example.daffolapmac.wealthbook.common.ILoader;
import com.example.daffolapmac.wealthbook.screen.updates.model.UpdateRes;

import java.util.List;

public interface IUpdateDetailsView extends ILoader {
    /**
     * List of news which is getting from server bind to view
     * @param data News response data
     */
    void refreshUpdateDetailsListView(UpdateRes data);

    /**
     * Show error
     * @param error Error message
     */
    void showError(@StringRes int error);
}
