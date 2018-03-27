package com.example.daffolapmac.wealthbook.screen.advisoragreement.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.advisoragreement.mode.UserAgreementRes;

public interface IUserAgreementResponseReceiver {

    /**
     * Send success back to presenter
     * @param data Data res
     */
    void onSuccess(UserAgreementRes data);

    /**
     * Send error back to presenter
     * @param errorResponse Error response
     */
    void onFailure(@NonNull ErrorResponse errorResponse);
}
