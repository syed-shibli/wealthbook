package com.example.daffolapmac.wealthbook.screen.myallocation.view;

import android.support.annotation.StringRes;

import com.example.daffolapmac.wealthbook.common.ILoader;
import com.example.daffolapmac.wealthbook.screen.myallocation.model.MyAllocationRes;

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
    void onError(@StringRes int error);
}
