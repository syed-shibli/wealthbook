package com.example.daffolapmac.wealthbook.common;

import android.support.annotation.StringRes;

public interface IFrgagmentBase {
    /**
     * Show progress bar
     */
    void showProgress();

    /**
     * Hide progress bar
     */
    void hideProgress();

    /**
     * Method to show Toast stringRes message
     *
     * @param message
     */
    void showSnackBar(@StringRes int message);

    /**
     * Method to show Toast string message
     *
     * @param message
     */
    void showSnackBar(String message);
}
