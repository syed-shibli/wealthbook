package com.example.daffolapmac.wealthbook.screen.login.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginRes;

public interface ILoginResponseReceiver {

    /**
     * Call when api success
     *
     * @param data Response data
     */
    void onSuccess(LoginRes data);

    /**
     * Call when api get failure
     *
     * @param errorResponse Server error response
     */
    void onFailure(@NonNull ErrorResponse errorResponse);

}
