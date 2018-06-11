package com.example.daffolapmac.wealthbook.screen.home.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;

public interface IHomeResponseReceiver {
    /**
     * Success result send back to presenter
     * @param count
     */
    void onSuccess(int count);

    /**
     * Send error back to presenter
     * @param errorResponse Error response
     */
    void onFailure(@NonNull ErrorResponse errorResponse);
}
