package com.example.daffolapmac.wealthbook.screen.forgotpassword.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.common.ConformationRes;

public interface IForgotPasswordResponseReceiver {

    /**
     * Call when api success
     * @param data Response data
     */
    void onOTPSendSuccess(ConformationRes data);

    /**
     * Call when api change password success
     * @param data Confirmation model response
     */
    void onChangePasswordSuccess(ConformationRes data);

    /**
     * Call when api get failure
     * @param errorResponse Server error response
     */
    void onFailure(@NonNull ErrorResponse errorResponse);

}
