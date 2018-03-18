package com.example.daffolapmac.wealthbook.screen.pendingalert.manager;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.pendingalert.model.PendingAlertRes;

public interface IPendingAlertResponseReceiver {

    /**
     * Call when server get success
     * @param data
     */
    void onSuccess(PendingAlertRes data);

    /**
     * Call when server get error
     * @param errorResponse Error response
     */
    void onFailure(ErrorResponse errorResponse);
}
