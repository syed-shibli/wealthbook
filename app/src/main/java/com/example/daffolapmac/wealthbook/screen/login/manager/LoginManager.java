package com.example.daffolapmac.wealthbook.screen.login.manager;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.api.ResponseCallback;
import com.example.daffolapmac.wealthbook.api.ResponseWrapper;
import com.example.daffolapmac.wealthbook.api.RetrofitClient;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginRequest;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginRes;
import com.example.daffolapmac.wealthbook.screen.login.presenter.ILoginResponseReceiver;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;
import com.example.daffolapmac.wealthbook.usersession.UserSessionData;
import com.google.gson.Gson;

import retrofit2.Call;

public class LoginManager {

    private ILoginResponseReceiver mLoginReceiver;
    private Call<LoginRes> mLoginReqCall;

    /**
     * To send login req to server
     *
     * @param receiver Response receiver
     * @param request  Request body
     */
    public void sendLoginReq(ILoginResponseReceiver receiver, LoginRequest request) {
        this.mLoginReceiver = receiver;
        mLoginReqCall = RetrofitClient.getApiService().login(request);
        mLoginReqCall.enqueue(new ResponseWrapper<LoginRes>(mLoginReqCallback));
    }

    private ResponseCallback<LoginRes> mLoginReqCallback = new ResponseCallback<LoginRes>() {
        @Override
        public void onSuccess(@NonNull LoginRes data) {
            SessionManager.getNewInstance().saveSession(new Gson().fromJson(new Gson().toJson(data), UserSessionData.class));
            mLoginReceiver.onSuccess(data);
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mLoginReceiver.onFailure(errorResponse);
        }
    };

    /**
     * Cancel the ongoing request
     */
    public void cancel() {
        if (mLoginReqCall != null && mLoginReqCall.isExecuted()) {
            mLoginReqCall.cancel();
        }
    }
}
