package com.wealthbook.android.screen.pendingalert.manager;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.screen.pendingalert.model.PendingAlertViewModel;

import java.util.ArrayList;

public interface IPendingAlertResponseReceiver {

    /**
     * Call when server get success
     * @param data View model
     */
    void onSuccess(ArrayList<PendingAlertViewModel> data);

    /**
     * Call when server get error
     * @param errorResponse Error response
     */
    void onFailure(ErrorResponse errorResponse);
}
