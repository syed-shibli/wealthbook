package com.wealthbook.android.screen.login.manager;

import android.support.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.gson.Gson;
import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.api.ResponseCallback;
import com.wealthbook.android.api.ResponseWrapper;
import com.wealthbook.android.api.RetrofitClient;
import com.wealthbook.android.deviceregistration.manager.DeviceRegistrationManager;
import com.wealthbook.android.notification.MyFirebaseMessagingService;
import com.wealthbook.android.screen.login.model.LoginRequest;
import com.wealthbook.android.screen.login.model.LoginRes;
import com.wealthbook.android.screen.login.model.LoginTroubleRes;
import com.wealthbook.android.screen.login.presenter.ILoginResponseReceiver;
import com.wealthbook.android.usersession.SessionManager;
import com.wealthbook.android.usersession.UserSessionData;

import retrofit2.Call;

public class LoginManager {

    private ILoginResponseReceiver mResponseReceiver;
    private Call<LoginRes> mLoginReqCall;
    private Call<LoginTroubleRes> mLoginTroubleCall;

    /**
     * To send login req to server
     * @param receiver Response receiver
     * @param request  Request body
     */
    public void sendLoginReq(ILoginResponseReceiver receiver, LoginRequest request) {
        this.mResponseReceiver = receiver;
        mLoginReqCall = RetrofitClient.getApiService().login(request);
        mLoginReqCall.enqueue(new ResponseWrapper<LoginRes>(mLoginReqCallback));
    }

    private ResponseCallback<LoginRes> mLoginReqCallback = new ResponseCallback<LoginRes>() {
        @Override
        public void onSuccess(@NonNull LoginRes data) {
            SessionManager.getNewInstance().saveSession(new Gson().fromJson(new Gson().toJson(data), UserSessionData.class));
            SessionManager.getNewInstance().saveKeyValue(SessionManager.USER_EMAIL_KEY, data.getEmail());
            mResponseReceiver.onSuccess(data);
            DeviceRegistrationManager manager = new DeviceRegistrationManager();
            manager.reqDeviceRegister(data.getToken(), FirebaseInstanceId.getInstance().getToken());
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mResponseReceiver.onFailure(errorResponse);
        }
    };

    public void reqLoginTrouble(ILoginResponseReceiver receiver) {
        this.mResponseReceiver = receiver;
        mLoginTroubleCall = RetrofitClient.getApiService().loginTrouble();
        mLoginTroubleCall.enqueue(new ResponseWrapper<LoginTroubleRes>(mLoginTroubleCallback));
    }

    private ResponseCallback<LoginTroubleRes> mLoginTroubleCallback = new ResponseCallback<LoginTroubleRes>() {
        @Override
        public void onSuccess(@NonNull LoginTroubleRes data) {
            mResponseReceiver.onLoginTroubleSuccess(data);
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mResponseReceiver.onFailure(errorResponse);
        }
    };

    /**
     * Cancel the ongoing request
     */
    public void cancel() {
        if (mLoginReqCall != null && mLoginReqCall.isExecuted()) {
            mLoginReqCall.cancel();
        }
        if (mLoginTroubleCall != null && mLoginTroubleCall.isExecuted()) {
            mLoginTroubleCall.cancel();
        }
    }
}
