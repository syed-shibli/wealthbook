package com.wealthbook.android.screen.forgotpassword.presenter;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.common.ConformationRes;

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
