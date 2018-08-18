package com.wealthbook.android.screen.advisoragreement.view;

import android.support.annotation.StringRes;

import com.wealthbook.android.common.ILoader;

public interface IUserAgreementView extends ILoader {

    /**
     * Get user agreement status from api
     * And redirect to user according to status`
     */
    void agreementStatus();

    /**
     * to show error
     * @param error Error message
     */
    void onError(String error);
}
