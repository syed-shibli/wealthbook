package com.example.daffolapmac.wealthbook.screen.advisoragreement.view;

import android.support.annotation.StringRes;

import com.example.daffolapmac.wealthbook.common.ILoader;

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
    void onError(@StringRes int error);
}
