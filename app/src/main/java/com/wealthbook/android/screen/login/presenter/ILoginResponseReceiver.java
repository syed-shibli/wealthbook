package com.wealthbook.android.screen.login.presenter;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.screen.login.model.LoginRes;
import com.wealthbook.android.screen.login.model.LoginTroubleRes;

public interface ILoginResponseReceiver {

    /**
     * Call when api success
     * @param data Response data
     */
    void onSuccess(LoginRes data);

    /**
     * Call when api get failure
     * @param errorResponse Server error response
     */
    void onFailure(@NonNull ErrorResponse errorResponse);

    /**
     * Call when login in trouble api get success
     * @param data Login trouble response
     */
    void onLoginTroubleSuccess(LoginTroubleRes data);
}
