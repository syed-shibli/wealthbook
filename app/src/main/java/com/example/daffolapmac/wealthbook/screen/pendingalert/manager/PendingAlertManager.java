package com.example.daffolapmac.wealthbook.screen.pendingalert.manager;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.api.ResponseCallback;
import com.example.daffolapmac.wealthbook.api.ResponseWrapper;
import com.example.daffolapmac.wealthbook.api.RetrofitClient;
import com.example.daffolapmac.wealthbook.screen.pendingalert.model.PendingAlertAttribute;
import com.example.daffolapmac.wealthbook.screen.pendingalert.model.PendingAlertItem;
import com.example.daffolapmac.wealthbook.screen.pendingalert.model.PendingAlertRes;
import com.example.daffolapmac.wealthbook.screen.pendingalert.model.PendingAlertViewModel;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class PendingAlertManager {

    private IPendingAlertResponseReceiver mPendingAlertReceiver;
    private Call<PendingAlertRes> mPendingAlertCall;

    /**
     * Send req to get all pending alert
     * @param responseReceiver Response receiver
     */
    public void getPendingAlertList(IPendingAlertResponseReceiver responseReceiver) {
        this.mPendingAlertReceiver = responseReceiver;
        String token = SessionManager.getNewInstance().readSession().getmToken();
        mPendingAlertCall = RetrofitClient.getApiService().getPendingAlert(token);
        mPendingAlertCall.enqueue(new ResponseWrapper<PendingAlertRes>(mPendingAlertCallback));
    }

    private ResponseCallback<PendingAlertRes> mPendingAlertCallback = new ResponseCallback<PendingAlertRes>() {
        @Override
        public void onSuccess(@NonNull PendingAlertRes data) {
            ArrayList<PendingAlertViewModel> viewModel = mapViewModel(data);
            mPendingAlertReceiver.onSuccess(viewModel);
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mPendingAlertReceiver.onFailure(errorResponse);
        }
    };

    /**
     * Map data model to view model
     * @param data Data model
     * @return Return view model
     */
    private ArrayList<PendingAlertViewModel> mapViewModel(PendingAlertRes data) {
        ArrayList<PendingAlertViewModel> list = new ArrayList<>();
        for (PendingAlertItem item : data.getPendingAlertList()) {
            PendingAlertViewModel model = new PendingAlertViewModel();
            if (item.getAttribute() != null && item.getAttribute().size() != 0) {
                model.setId(item.getPendingAlertId());
                for (PendingAlertAttribute attribute : item.getAttribute()) {
                    if (attribute.getLabel() == null) {
                        return list;
                    }
                    if (attribute.getLabel().equalsIgnoreCase("Account Title:")) {
                        model.setTitle(attribute.getValue());
                    }
                    if (attribute.getLabel().equalsIgnoreCase("Account No:")) {
                        model.setAccountNumber(attribute.getValue());
                    }
                    if (attribute.getLabel().equalsIgnoreCase("Advisor Name:")) {
                        model.setAdviserName(attribute.getValue());
                    }
                    if (attribute.getLabel().equalsIgnoreCase("Advisor Contact:")) {
                        model.setAdviserContact(attribute.getValue());
                    }
                    if (attribute.getLabel().equalsIgnoreCase("Alert:")) {
                        model.setAlert(attribute.getValue());
                    }
                }
            }
            list.add(model);
        }
        return list;
    }

    /**
     * Cancel all on going request
     */
    public void cancel() {
        if (mPendingAlertCall != null && mPendingAlertCall.isExecuted()) {
            mPendingAlertCall.cancel();
        }
    }
}
