package com.example.daffolapmac.wealthbook.screen.pendingalert.manager;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.pendingalert.model.PendingAlertViewModel;

import java.util.ArrayList;
import java.util.List;

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
