package com.wealthbook.android.screen.advisoragreement.manager;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.api.ResponseCallback;
import com.wealthbook.android.api.ResponseWrapper;
import com.wealthbook.android.api.RetrofitClient;
import com.wealthbook.android.screen.advisoragreement.mode.UserAgreementReq;
import com.wealthbook.android.screen.advisoragreement.mode.UserAgreementRes;
import com.wealthbook.android.screen.advisoragreement.presenter.IUserAgreementResponseReceiver;
import com.wealthbook.android.usersession.SessionManager;
import com.wealthbook.android.usersession.UserAgreement;
import com.wealthbook.android.usersession.UserSessionData;

import retrofit2.Call;

public class UserAgreementManager {

    private IUserAgreementResponseReceiver mResponseReceiver;
    private Call<UserAgreementRes> mUserAgreementCall;

    public void sendReqToUpdateAgreement(IUserAgreementResponseReceiver receiver, UserAgreementReq req) {
        this.mResponseReceiver = receiver;
        String token = SessionManager.getNewInstance().readSession().getmToken();
        mUserAgreementCall = RetrofitClient.getApiService().updateUserAgreementStatus(token, req);
        mUserAgreementCall.enqueue(new ResponseWrapper<UserAgreementRes>(mUserAgreementCallback));
    }

    private ResponseCallback<UserAgreementRes> mUserAgreementCallback = new ResponseCallback<UserAgreementRes>() {
        @Override
        public void onSuccess(@NonNull UserAgreementRes data) {
            UserSessionData session = SessionManager.getNewInstance().readSession();
            if (session.getUserAgreement() != null) {
                UserAgreement agreement = session.getUserAgreement();
                agreement.setAgreementStatus(data.getAgreementStatus());
                session.setUserAgreement(agreement);
            }
            SessionManager.getNewInstance().saveSession(session);
            mResponseReceiver.onSuccess(data);
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mResponseReceiver.onFailure(errorResponse);
        }
    };

    /**
     * Cancel all ongoing req
     */
    public void cancel() {
        if (mUserAgreementCall != null && mUserAgreementCall.isExecuted()) {
            mUserAgreementCall.cancel();
        }
    }
}
