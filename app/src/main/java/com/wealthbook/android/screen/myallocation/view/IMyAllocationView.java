package com.wealthbook.android.screen.myallocation.view;

import android.support.annotation.StringRes;

import com.wealthbook.android.common.ILoader;
import com.wealthbook.android.screen.myallocation.model.MyAllocationRes;

public interface IMyAllocationView extends ILoader {

    /**
     * Bind data to view
     * @param data
     */
    void bindView(MyAllocationRes data);

    /**
     * To show Error message
     * @param error Error message
     */
    void onError(String error);
}
